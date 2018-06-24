package com.app.tensorflow;


import org.tensorflow.*;
import org.tensorflow.types.UInt8;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper over the TensorFlow API ({@link Graph}, {@link Session}) providing a smaller API surface
 * for inference.
 *
 * <p>See tensorflow/examples/android/src/org/tensorflow/demo/TensorFlowImageClassifier.java for an
 * example usage.
 */
public class TensorFlowInferenceInterface {

    // Immutable state.
    private final String modelName;
    private final Graph g;
    private final Session sess;

    /**
     * Construct a TensorFlowInferenceInterface with provided model path.
     *
     * @param model The filepath to the GraphDef proto representing the model.
     */
    public TensorFlowInferenceInterface(String model) throws FileNotFoundException {
        System.out.println("Checking to see if TensorFlow native methods are already loaded");
        this.modelName = model;
        this.g = new Graph();
        this.sess = new Session(g);

        try {
            byte[] graphDef = Files.readAllBytes(Paths.get(model));
            loadGraph(graphDef, g);
            System.out.println("Successfully loaded model from '" + model + "'");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load model from '" + model + "'", e);
        }
    }

    /**
     * Construct a TensorFlowInferenceInterface with provided Graph
     *
     * @param g The Graph to use to construct this interface.
     */
    public TensorFlowInferenceInterface(Graph g) {
        // modelName is redundant here, here is for
        // avoiding error in initialization as modelName is marked final.
        this.modelName = "";
        this.g = g;
        this.sess = new Session(g);
    }

    private void loadGraph(InputStream is, Graph g) throws IOException {
        final long startMs = System.currentTimeMillis();

        byte[] graphDef = new byte[is.available()];
        final int numBytesRead = is.read(graphDef);
        if (numBytesRead != graphDef.length) {
            throw new IOException(
                    "read error: read only "
                            + numBytesRead
                            + " of the graph, expected to read "
                            + graphDef.length);
        }

        try {
            g.importGraphDef(graphDef);
        } catch (IllegalArgumentException e) {
            throw new IOException("Not a valid TensorFlow Graph serialization: " + e.getMessage());
        }

        final long endMs = System.currentTimeMillis();
        System.out.println(
                "Model load took " + (endMs - startMs) + "ms, TensorFlow version: " + TensorFlow.version());
    }

    private void loadGraph(byte[] graphDef, Graph g) throws IOException {
        final long startMs = System.currentTimeMillis();

        try {
            g.importGraphDef(graphDef);
        } catch (IllegalArgumentException e) {
            throw new IOException("Not a valid TensorFlow Graph serialization: " + e.getMessage());
        }

        final long endMs = System.currentTimeMillis();
        System.out.println("Model load took " + (endMs - startMs) + "ms, TensorFlow version: " + TensorFlow.version());
    }

    /**
     * Returns a reference to the Graph describing the computation run during inference.
     */
    public Graph graph() {
        return g;
    }

    public Operation graphOperation(String operationName) {
        final Operation operation = g.operation(operationName);
        if (operation == null) {
            throw new RuntimeException(
                    "Node '" + operationName + "' does not exist in model '" + modelName + "'");
        }
        return operation;
    }

    /**
     * Cleans up the state associated with this Object. initializeTensorFlow() can then be called
     * again to initialize a new session.
     */
    /**
     * Cleans up the state associated with this Object.
     *
     * <p>The TenosrFlowInferenceInterface object is no longer usable after this method returns.
     */
    public void close() {
        sess.close();
        g.close();
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    public TensorFlowInferenceInterface.Runner runner() {
        return new TensorFlowInferenceInterface.Runner(sess.runner());
    }


    /***
     * Runner
     */
    public static class Runner {
        // State reset on every call to run.
        private Session.Runner runner;
        private List<String> feedNames = new ArrayList<String>();
        private List<Tensor<?>> feedTensors = new ArrayList<Tensor<?>>();
        private List<String> fetchNames = new ArrayList<String>();
        private List<Tensor<?>> fetchTensors = new ArrayList<Tensor<?>>();

        // Mutable state.
        private RunStats runStats;

        // Only can run once.
        private boolean hasRunned = false;

        //Constructor
        public Runner(Session.Runner runner) {
            this.runner = runner;
        }

        private static class TensorId {
            String name;
            int outputIndex;

            // Parse output names into a TensorId.
            //
            // E.g., "foo" --> ("foo", 0), while "foo:1" --> ("foo", 1)
            public static TensorId parse(String name) {
                TensorId tid = new TensorId();
                int colonIndex = name.lastIndexOf(':');
                if (colonIndex < 0) {
                    tid.outputIndex = 0;
                    tid.name = name;
                    return tid;
                }
                try {
                    tid.outputIndex = Integer.parseInt(name.substring(colonIndex + 1));
                    tid.name = name.substring(0, colonIndex);
                } catch (NumberFormatException e) {
                    tid.outputIndex = 0;
                    tid.name = name;
                }
                return tid;
            }
        }

        /**
         * Runs inference between the previously registered input nodes (via feed*) and the requested
         * output nodes. Output nodes can then be queried with the fetch* methods.
         *
         * @param outputNames A list of output nodes which should be filled by the inference pass.
         */
        public void run(String[] outputNames) {
            run(outputNames, false);
        }

        public void run(String[] outputNames, boolean enableStats) {
            run(outputNames, enableStats, new String[]{});
        }

        /**
         * An overloaded version of runInference that allows supplying targetNodeNames as well
         */
        public void run(String[] outputNames, boolean enableStats, String[] targetNodeNames) {
            //注意此处不做同步，自行保证安全就好。
            if (hasRunned)
                throw new RuntimeException("One runner only can be run once.");

            hasRunned = true;

            // Add fetches.
            for (String o : outputNames) {
                fetchNames.add(o);
                TensorId tid = TensorId.parse(o);
                runner.fetch(tid.name, tid.outputIndex);
            }

            // Add targets.
            for (String t : targetNodeNames) {
                runner.addTarget(t);
            }

            // Run the session.
            try {
                if (enableStats) {
                    Session.Run r = runner.setOptions(RunStats.runOptions()).runAndFetchMetadata();
                    fetchTensors = r.outputs;

                    if (runStats == null) {
                        runStats = new RunStats();
                    }
                    runStats.add(r.metadata);
                } else {
                    fetchTensors = runner.run();
                }
            } catch (RuntimeException e) {
                // Ideally the exception would have been let through, but since this interface predates the
                // TensorFlow Java API, must return -1.
                System.out.println(
                        "Failed to run TensorFlow inference with inputs:["
                                + ", "
                                + feedNames
                                + "], outputs:["
                                + ", " + fetchNames
                                + "]");
                throw e;
            } finally {
                // Always release the feeds (to save resources) and reset the runner, this run is
                // over.
                closeFeeds();
            }
        }

        // Methods for taking a native Tensor and filling it with values from Java arrays.

        public void feed(String inputName, Tensor tensor) {
            addFeed(inputName, tensor);
        }

        public void feed(String inputName, boolean[] src, long... dims) {
            byte[] b = new byte[src.length];

            for (int i = 0; i < src.length; i++) {
                b[i] = src[i] ? (byte) 1 : (byte) 0;
            }

            addFeed(inputName, Tensor.create(Boolean.class, dims, ByteBuffer.wrap(b)));
        }

        public void feed(String inputName, float[] src, long... dims) {
            addFeed(inputName, Tensor.create(dims, FloatBuffer.wrap(src)));
        }

        public void feed(String inputName, int[] src, long... dims) {
            addFeed(inputName, Tensor.create(dims, IntBuffer.wrap(src)));
        }

        public void feed(String inputName, long[] src, long... dims) {
            addFeed(inputName, Tensor.create(dims, LongBuffer.wrap(src)));
        }

        public void feed(String inputName, double[] src, long... dims) {
            addFeed(inputName, Tensor.create(dims, DoubleBuffer.wrap(src)));
        }

        public void feed(String inputName, byte[] src, long... dims) {
            addFeed(inputName, Tensor.create(UInt8.class, dims, ByteBuffer.wrap(src)));
        }

        public void feedString(String inputName, byte[] src) {
            addFeed(inputName, Tensors.create(src));
        }

        public void feedString(String inputName, byte[][] src) {
            addFeed(inputName, Tensors.create(src));
        }

        // Methods for taking a native Tensor and filling it with src from Java native IO buffers.

        public void feed(String inputName, FloatBuffer src, long... dims) {
            addFeed(inputName, Tensor.create(dims, src));
        }

        public void feed(String inputName, IntBuffer src, long... dims) {
            addFeed(inputName, Tensor.create(dims, src));
        }

        public void feed(String inputName, LongBuffer src, long... dims) {
            addFeed(inputName, Tensor.create(dims, src));
        }

        public void feed(String inputName, DoubleBuffer src, long... dims) {
            addFeed(inputName, Tensor.create(dims, src));
        }

        public void feed(String inputName, ByteBuffer src, long... dims) {
            addFeed(inputName, Tensor.create(UInt8.class, dims, src));
        }

        private void addFeed(String inputName, Tensor t) {
            // The string format accepted by TensorFlowInferenceInterface is node_name[:output_index].
            TensorId tid = TensorId.parse(inputName);
            runner.feed(tid.name, tid.outputIndex, t);
            feedNames.add(inputName);
            feedTensors.add(t);
        }

        /***
         * 根据outputIndex返回Tensor，需要注意此tensor对应数据会在下一次run调用时候失效，因此拿到之后应该尽快用于构建其他数据结构或者拷贝到其他数据结构。
         * @param outputIndex
         */
        public Tensor fetch(int outputIndex) {
            return getTensor(outputIndex);
        }

        /***
         * 根据outputName返回Tensor，需要注意此tensor对应数据会在下一次run调用时候失效，因此拿到之后应该尽快用于构建其他数据结构或者拷贝到其他数据结构。
         * @param outputName
         */
        public Tensor fetch(String outputName) {
            return getTensor(outputName);
        }

        /**
         * Read from a Tensor named outputName and copy the contents into a Java array. dst must have
         * length greater than or equal to that of the source Tensor. This operation will not affect
         * dst's content past the source Tensor's size.
         */
        public void fetch(String outputName, float[] dst) {
            fetch(outputName, FloatBuffer.wrap(dst));
        }

        public void fetch(String outputName, int[] dst) {
            fetch(outputName, IntBuffer.wrap(dst));
        }

        public void fetch(String outputName, long[] dst) {
            fetch(outputName, LongBuffer.wrap(dst));
        }

        public void fetch(String outputName, double[] dst) {
            fetch(outputName, DoubleBuffer.wrap(dst));
        }

        public void fetch(String outputName, byte[] dst) {
            fetch(outputName, ByteBuffer.wrap(dst));
        }

        public void fetch(String outputName, FloatBuffer dst) {
            getTensor(outputName).writeTo(dst);
        }

        public void fetch(String outputName, IntBuffer dst) {
            getTensor(outputName).writeTo(dst);
        }

        public void fetch(String outputName, LongBuffer dst) {
            getTensor(outputName).writeTo(dst);
        }

        public void fetch(String outputName, DoubleBuffer dst) {
            getTensor(outputName).writeTo(dst);
        }

        public void fetch(String outputName, ByteBuffer dst) {
            getTensor(outputName).writeTo(dst);
        }

        private Tensor getTensor(int outputIndex) {
            if (outputIndex >= 0 && outputIndex < fetchTensors.size()) {
                return fetchTensors.get(outputIndex);
            }
            throw new RuntimeException(
                    "Node '" + outputIndex + "' was not provided to run(), so it cannot be read");
        }

        private Tensor getTensor(String outputName) {
            int i = 0;
            for (String n : fetchNames) {
                if (n.equals(outputName)) {
                    return fetchTensors.get(i);
                }
                ++i;
            }
            throw new RuntimeException(
                    "Node '" + outputName + "' was not provided to run(), so it cannot be read");
        }

        /**
         * Returns the last stat summary string if logging is enabled.
         */
        public String getStatString() {
            return (runStats == null) ? "" : runStats.summary();
        }

        private void closeFeeds() {
            for (Tensor t : feedTensors) {
                t.close();
            }
            feedTensors.clear();
            feedNames.clear();
        }

        private void closeFetches() {
            for (Tensor t : fetchTensors) {
                t.close();
            }
            fetchTensors.clear();
            fetchNames.clear();
        }

        /**
         * Cleans up the state associated with this Object. initializeTensorFlow() can then be called
         * again to initialize a new session.
         */
        /**
         * Cleans up the state associated with this Object.
         *
         * <p>The TenosrFlowInferenceInterface object is no longer usable after this method returns.
         */
        public void close() {
            closeFeeds();
            closeFetches();
            if (runStats != null) {
                runStats.close();
            }
            runStats = null;
        }

        @Override
        protected void finalize() throws Throwable {
            try {
                close();
            } finally {
                super.finalize();
            }
        }

    }

}
