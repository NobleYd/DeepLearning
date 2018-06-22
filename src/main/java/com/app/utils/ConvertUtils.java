package com.app.utils;

import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.Tensors;

import java.nio.*;

/***
 * Convert from mat to tensor.
 * Convert from tensor to mat.
 *
 * Convert from mat to ndarray.
 * Convert from ndarray to mat.
 *
 * Convert from ndarray to tensor.
 * Convert from tensor to ndarray.
 *
 * Convert from mat, tensor, ndarray to basic 1d array.
 *
 * Author: hinoble@gmail.com
 */

public class ConvertUtils {

    // Convert from mat, tensor, ndarray to basic 1d array.

    public static boolean[] matToBooleans(Mat m) {
        if (m.depth() == CvType.CV_8U) {
            byte[] data = new byte[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ubytes2booleans(data);
        } else if (m.depth() == CvType.CV_8S) {
            byte[] data = new byte[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return bytes2booleans(data);
        } else if (m.depth() == CvType.CV_16U) {
            short[] data = new short[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ushorts2booleans(data);
        } else if (m.depth() == CvType.CV_16S) {
            short[] data = new short[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return shorts2booleans(data);
        } else if (m.depth() == CvType.CV_32S) {
            int[] data = new int[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ints2booleans(data);
        } else if (m.depth() == CvType.CV_32F) {
            float[] data = new float[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return floats2booleans(data);
        } else if (m.depth() == CvType.CV_64F) {
            double[] data = new double[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return doubles2booleans(data);
        } else {
            throw new IllegalArgumentException("Mat type_depth should be byte, short, int, float, double.");
        }
    }

    public static byte[] matToBytes(Mat m) {
        if (m.depth() == CvType.CV_8U) {
            byte[] data = new byte[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return data;
        } else if (m.depth() == CvType.CV_8S) {
            byte[] data = new byte[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return data;
        } else if (m.depth() == CvType.CV_16U) {
            short[] data = new short[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ushorts2bytes(data);
        } else if (m.depth() == CvType.CV_16S) {
            short[] data = new short[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return shorts2bytes(data);
        } else if (m.depth() == CvType.CV_32S) {
            int[] data = new int[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ints2bytes(data);
        } else if (m.depth() == CvType.CV_32F) {
            float[] data = new float[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return floats2bytes(data);
        } else if (m.depth() == CvType.CV_64F) {
            double[] data = new double[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return doubles2bytes(data);
        } else {
            throw new IllegalArgumentException("Mat type_depth should be byte, short, int, float, double.");
        }
    }

    public static short[] matToShorts(Mat m) {
        if (m.depth() == CvType.CV_8U) {
            byte[] data = new byte[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ubytes2shorts(data);
        } else if (m.depth() == CvType.CV_8S) {
            byte[] data = new byte[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return bytes2shorts(data);
        } else if (m.depth() == CvType.CV_16U) {
            short[] data = new short[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return data;
        } else if (m.depth() == CvType.CV_16S) {
            short[] data = new short[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return data;
        } else if (m.depth() == CvType.CV_32S) {
            int[] data = new int[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ints2shorts(data);
        } else if (m.depth() == CvType.CV_32F) {
            float[] data = new float[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return floats2shorts(data);
        } else if (m.depth() == CvType.CV_64F) {
            double[] data = new double[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return doubles2shorts(data);
        } else {
            throw new IllegalArgumentException("Mat type_depth should be byte, short, int, float, double.");
        }
    }

    public static int[] matToInts(Mat m) {
        if (m.depth() == CvType.CV_8U) {
            byte[] data = new byte[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ubytes2ints(data);
        } else if (m.depth() == CvType.CV_8S) {
            byte[] data = new byte[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return bytes2ints(data);
        } else if (m.depth() == CvType.CV_16U) {
            short[] data = new short[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ushorts2ints(data);
        } else if (m.depth() == CvType.CV_16S) {
            short[] data = new short[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return shorts2ints(data);
        } else if (m.depth() == CvType.CV_32S) {
            int[] data = new int[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return data;
        } else if (m.depth() == CvType.CV_32F) {
            float[] data = new float[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return floats2ints(data);
        } else if (m.depth() == CvType.CV_64F) {
            double[] data = new double[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return doubles2ints(data);
        } else {
            throw new IllegalArgumentException("Mat type_depth should be byte, short, int, float, double.");
        }
    }

    public static long[] matToLongs(Mat m) {
        if (m.depth() == CvType.CV_8U) {
            byte[] data = new byte[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ubytes2longs(data);
        } else if (m.depth() == CvType.CV_8S) {
            byte[] data = new byte[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return bytes2longs(data);
        } else if (m.depth() == CvType.CV_16U) {
            short[] data = new short[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ushorts2longs(data);
        } else if (m.depth() == CvType.CV_16S) {
            short[] data = new short[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return shorts2longs(data);
        } else if (m.depth() == CvType.CV_32S) {
            int[] data = new int[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ints2longs(data);
        } else if (m.depth() == CvType.CV_32F) {
            float[] data = new float[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return floats2longs(data);
        } else if (m.depth() == CvType.CV_64F) {
            double[] data = new double[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return doubles2longs(data);
        } else {
            throw new IllegalArgumentException("Mat type_depth should be byte, short, int, float, double.");
        }
    }

    public static float[] matToFloats(Mat m) {
        if (m.depth() == CvType.CV_8U) {
            byte[] data = new byte[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ubytes2floats(data);
        } else if (m.depth() == CvType.CV_8S) {
            byte[] data = new byte[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return bytes2floats(data);
        } else if (m.depth() == CvType.CV_16U) {
            short[] data = new short[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ushorts2floats(data);
        } else if (m.depth() == CvType.CV_16S) {
            short[] data = new short[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return shorts2floats(data);
        } else if (m.depth() == CvType.CV_32S) {
            int[] data = new int[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ints2floats(data);
        } else if (m.depth() == CvType.CV_32F) {
            float[] data = new float[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return data;
        } else if (m.depth() == CvType.CV_64F) {
            double[] data = new double[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return doubles2floats(data);
        } else {
            throw new IllegalArgumentException("Mat type_depth should be byte, short, int, float, double.");
        }
    }

    public static double[] matToDoubles(Mat m) {
        if (m.depth() == CvType.CV_8U) {
            byte[] data = new byte[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ubytes2doubles(data);
        } else if (m.depth() == CvType.CV_8S) {
            byte[] data = new byte[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return bytes2doubles(data);
        } else if (m.depth() == CvType.CV_16U) {
            short[] data = new short[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ushorts2doubles(data);
        } else if (m.depth() == CvType.CV_16S) {
            short[] data = new short[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return shorts2doubles(data);
        } else if (m.depth() == CvType.CV_32S) {
            int[] data = new int[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return ints2doubles(data);
        } else if (m.depth() == CvType.CV_32F) {
            float[] data = new float[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return floats2doubles(data);
        } else if (m.depth() == CvType.CV_64F) {
            double[] data = new double[(int) (m.total() * m.channels())];
            m.get(0, 0, data);
            return data;
        } else {
            throw new IllegalArgumentException("Mat type_depth should be byte, short, int, float, double.");
        }
    }

    public static boolean[] tensorToBooleans(Tensor tensor) {
        return bytes2booleans(tensorToBytes(tensor));
    }

    public static byte[] tensorToBytes(Tensor tensor) {
        if (tensor.dataType() == DataType.BOOL) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(tensor.numElements());
            tensor.writeTo(byteBuffer);
            return byteBuffer.array();
        }// else if(tensor.dataType()==DataType.UINT8){}
        else if (tensor.dataType() == DataType.INT32) {
            IntBuffer intBuffer = IntBuffer.allocate(tensor.numElements());
            tensor.writeTo(intBuffer);
            return ints2bytes(intBuffer.array());
        } else if (tensor.dataType() == DataType.INT64) {
            LongBuffer longBuffer = LongBuffer.allocate(tensor.numElements());
            tensor.writeTo(longBuffer);
            return longs2bytes(longBuffer.array());
        } else if (tensor.dataType() == DataType.FLOAT) {
            FloatBuffer floatBuffer = FloatBuffer.allocate(tensor.numElements());
            tensor.writeTo(floatBuffer);
            return floats2bytes(floatBuffer.array());
        } else if (tensor.dataType() == DataType.DOUBLE) {
            DoubleBuffer doubleBuffer = DoubleBuffer.allocate(tensor.numElements());
            tensor.writeTo(doubleBuffer);
            return doubles2bytes(doubleBuffer.array());
        } else {
            throw new IllegalArgumentException("tensor type should be bool, int, long, float, double.");
        }
    }

    public static short[] tensorToShorts(Tensor tensor) {
        if (tensor.dataType() == DataType.BOOL) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(tensor.numElements());
            tensor.writeTo(byteBuffer);
            return bytes2shorts(byteBuffer.array());
        }// else if(tensor.dataType()==DataType.UINT8){}
        else if (tensor.dataType() == DataType.INT32) {
            IntBuffer intBuffer = IntBuffer.allocate(tensor.numElements());
            tensor.writeTo(intBuffer);
            return ints2shorts(intBuffer.array());
        } else if (tensor.dataType() == DataType.INT64) {
            LongBuffer longBuffer = LongBuffer.allocate(tensor.numElements());
            tensor.writeTo(longBuffer);
            return longs2shorts(longBuffer.array());
        } else if (tensor.dataType() == DataType.FLOAT) {
            FloatBuffer floatBuffer = FloatBuffer.allocate(tensor.numElements());
            tensor.writeTo(floatBuffer);
            return floats2shorts(floatBuffer.array());
        } else if (tensor.dataType() == DataType.DOUBLE) {
            DoubleBuffer doubleBuffer = DoubleBuffer.allocate(tensor.numElements());
            tensor.writeTo(doubleBuffer);
            return doubles2shorts(doubleBuffer.array());
        } else {
            throw new IllegalArgumentException("tensor type should be bool, int, long, float, double.");
        }
    }

    public static int[] tensorToInts(Tensor tensor) {
        if (tensor.dataType() == DataType.BOOL) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(tensor.numElements());
            tensor.writeTo(byteBuffer);
            return bytes2ints(byteBuffer.array());
        }// else if(tensor.dataType()==DataType.UINT8){}
        else if (tensor.dataType() == DataType.INT32) {
            IntBuffer intBuffer = IntBuffer.allocate(tensor.numElements());
            tensor.writeTo(intBuffer);
            return intBuffer.array();
        } else if (tensor.dataType() == DataType.INT64) {
            LongBuffer longBuffer = LongBuffer.allocate(tensor.numElements());
            tensor.writeTo(longBuffer);
            return longs2ints(longBuffer.array());
        } else if (tensor.dataType() == DataType.FLOAT) {
            FloatBuffer floatBuffer = FloatBuffer.allocate(tensor.numElements());
            tensor.writeTo(floatBuffer);
            return floats2ints(floatBuffer.array());
        } else if (tensor.dataType() == DataType.DOUBLE) {
            DoubleBuffer doubleBuffer = DoubleBuffer.allocate(tensor.numElements());
            tensor.writeTo(doubleBuffer);
            return doubles2ints(doubleBuffer.array());
        } else {
            throw new IllegalArgumentException("tensor type should be bool, int, long, float, double.");
        }
    }

    public static long[] tensorToLongs(Tensor tensor) {
        if (tensor.dataType() == DataType.BOOL) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(tensor.numElements());
            tensor.writeTo(byteBuffer);
            return bytes2longs(byteBuffer.array());
        }// else if(tensor.dataType()==DataType.UINT8){}
        else if (tensor.dataType() == DataType.INT32) {
            IntBuffer intBuffer = IntBuffer.allocate(tensor.numElements());
            tensor.writeTo(intBuffer);
            return ints2longs(intBuffer.array());
        } else if (tensor.dataType() == DataType.INT64) {
            LongBuffer longBuffer = LongBuffer.allocate(tensor.numElements());
            tensor.writeTo(longBuffer);
            return longBuffer.array();
        } else if (tensor.dataType() == DataType.FLOAT) {
            FloatBuffer floatBuffer = FloatBuffer.allocate(tensor.numElements());
            tensor.writeTo(floatBuffer);
            return floats2longs(floatBuffer.array());
        } else if (tensor.dataType() == DataType.DOUBLE) {
            DoubleBuffer doubleBuffer = DoubleBuffer.allocate(tensor.numElements());
            tensor.writeTo(doubleBuffer);
            return doubles2longs(doubleBuffer.array());
        } else {
            throw new IllegalArgumentException("tensor type should be bool, int, long, float, double.");
        }
    }

    public static float[] tensorToFloats(Tensor tensor) {
        if (tensor.dataType() == DataType.BOOL) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(tensor.numElements());
            tensor.writeTo(byteBuffer);
            return bytes2floats(byteBuffer.array());
        }// else if(tensor.dataType()==DataType.UINT8){}
        else if (tensor.dataType() == DataType.INT32) {
            IntBuffer intBuffer = IntBuffer.allocate(tensor.numElements());
            tensor.writeTo(intBuffer);
            return ints2floats(intBuffer.array());
        } else if (tensor.dataType() == DataType.INT64) {
            LongBuffer longBuffer = LongBuffer.allocate(tensor.numElements());
            tensor.writeTo(longBuffer);
            return longs2floats(longBuffer.array());
        } else if (tensor.dataType() == DataType.FLOAT) {
            FloatBuffer floatBuffer = FloatBuffer.allocate(tensor.numElements());
            tensor.writeTo(floatBuffer);
            return floatBuffer.array();
        } else if (tensor.dataType() == DataType.DOUBLE) {
            DoubleBuffer doubleBuffer = DoubleBuffer.allocate(tensor.numElements());
            tensor.writeTo(doubleBuffer);
            return doubles2floats(doubleBuffer.array());
        } else {
            throw new IllegalArgumentException("tensor type should be bool, int, long, float, double.");
        }
    }

    public static double[] tensorToDoubles(Tensor tensor) {
        if (tensor.dataType() == DataType.BOOL) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(tensor.numElements());
            tensor.writeTo(byteBuffer);
            return bytes2doubles(byteBuffer.array());
        }// else if(tensor.dataType()==DataType.UINT8){}
        else if (tensor.dataType() == DataType.INT32) {
            IntBuffer intBuffer = IntBuffer.allocate(tensor.numElements());
            tensor.writeTo(intBuffer);
            return ints2doubles(intBuffer.array());
        } else if (tensor.dataType() == DataType.INT64) {
            LongBuffer longBuffer = LongBuffer.allocate(tensor.numElements());
            tensor.writeTo(longBuffer);
            return longs2doubles(longBuffer.array());
        } else if (tensor.dataType() == DataType.FLOAT) {
            FloatBuffer floatBuffer = FloatBuffer.allocate(tensor.numElements());
            tensor.writeTo(floatBuffer);
            return floats2doubles(floatBuffer.array());
        } else if (tensor.dataType() == DataType.DOUBLE) {
            DoubleBuffer doubleBuffer = DoubleBuffer.allocate(tensor.numElements());
            tensor.writeTo(doubleBuffer);
            return doubleBuffer.array();
        } else {
            throw new IllegalArgumentException("tensor type should be bool, int, long, float, double.");
        }
    }

    public static boolean[] ndArrayToBooleans(INDArray ndArray) {
        return ints2booleans(ndArrayToInts(ndArray));
    }

    public static byte[] ndArrayToBytes(INDArray ndArray) {
        return ints2bytes(ndArrayToInts(ndArray));
    }

    public static short[] ndArrayToShorts(INDArray ndArray) {
        return ints2shorts(ndArrayToInts(ndArray));
    }

    public static int[] ndArrayToInts(INDArray ndArray) {
        if (ndArray.length() == 1) {
            return new int[]{ndArray.getInt(0, 0)};
        }
        return Nd4j.toFlattened(ndArray).toIntVector();
    }

    public static long[] ndArrayToLongs(INDArray ndArray) {
        return ints2longs(ndArrayToInts(ndArray));
    }

    public static float[] ndArrayToFloats(INDArray ndArray) {
        if (ndArray.length() == 1) {
            return new float[]{ndArray.getFloat(0, 0)};
        }
        return Nd4j.toFlattened(ndArray).toFloatVector();
    }

    public static double[] ndArrayToDoubles(INDArray ndArray) {
        if (ndArray.length() == 1) {
            return new double[]{ndArray.getDouble(0, 0)};
        }
        return Nd4j.toFlattened(ndArray).toDoubleVector();
    }

    // Convert from mat to tensor.

    public static Tensor<Boolean> matToBooleanTensor(Mat m) {
        return Tensors.create(matToBooleans(m));
    }

    public static Tensor<Integer> matToIntTensor(Mat m, long... shapes) {
        return Tensor.create(shapes, IntBuffer.wrap(matToInts(m)));
    }

    public static Tensor<Long> matToLongTensor(Mat m, long... shapes) {
        return Tensor.create(shapes, LongBuffer.wrap(matToLongs(m)));
    }

    public static Tensor<Float> matToFloatTensor(Mat m, long... shapes) {
        return Tensor.create(shapes, FloatBuffer.wrap(matToFloats(m)));
    }

    public static Tensor<Double> matToDoubleTensor(Mat m, long... shapes) {
        return Tensor.create(shapes, DoubleBuffer.wrap(matToDoubles(m)));
    }

    //Convert from tensor to mat.

    public static Mat tensorToMat(Tensor<?> tensor) {
        Mat m;
        int[] shapes;
        if (tensor.shape().length == 3) {
            shapes = longs2ints(tensor.shape());
        } else if (tensor.shape().length == 4 && tensor.shape()[0] == 1) {
            shapes = new int[]{(int) tensor.shape()[1], (int) tensor.shape()[2], (int) tensor.shape()[3]};
        } else {
            throw new IllegalArgumentException("tensor.shape().length should be 3, or 4(with first dimension equals to 1.).");
        }

        if (tensor.dataType() == DataType.BOOL) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(tensor.numElements());
            tensor.writeTo(byteBuffer);
            m = new Mat(shapes[0], shapes[1], CvType.CV_8UC(shapes[2]));
            m.put(0, 0, byteBuffer.array());
            return m;
        }// else if(tensor.dataType()==DataType.UINT8){}
        else if (tensor.dataType() == DataType.INT32) {
            IntBuffer intBuffer = IntBuffer.allocate(tensor.numElements());
            tensor.writeTo(intBuffer);
            m = new Mat(shapes[0], shapes[1], CvType.CV_32SC(shapes[2]));
            m.put(0, 0, intBuffer.array());
            return m;
        } else if (tensor.dataType() == DataType.INT64) {
            LongBuffer longBuffer = LongBuffer.allocate(tensor.numElements());
            tensor.writeTo(longBuffer);
            m = new Mat(shapes[0], shapes[1], CvType.CV_64FC(shapes[2]));
            m.put(0, 0, longs2doubles(longBuffer.array()));
            return m;
        } else if (tensor.dataType() == DataType.FLOAT) {
            FloatBuffer floatBuffer = FloatBuffer.allocate(tensor.numElements());
            tensor.writeTo(floatBuffer);
            m = new Mat(shapes[0], shapes[1], CvType.CV_32FC(shapes[2]));
            m.put(0, 0, floatBuffer.array());
            return m;
        } else if (tensor.dataType() == DataType.DOUBLE) {
            DoubleBuffer doubleBuffer = DoubleBuffer.allocate(tensor.numElements());
            tensor.writeTo(doubleBuffer);
            m = new Mat(shapes[0], shapes[1], CvType.CV_64FC(shapes[2]));
            m.put(0, 0, doubleBuffer.array());
            return m;
        } else {
            throw new IllegalArgumentException("tensor type should be bool, int, long, float, double.");
        }
    }

    //Convert from mat to ndarray.

    public static INDArray matToNDArray(Mat m, long... shapes) {
        if (shapes == null || shapes.length == 0) {
            if (m.channels() == 1) {
                shapes = new long[]{m.rows(), m.cols()};
            } else {
                shapes = new long[]{m.rows(), m.cols(), m.channels()};
            }
        }
        if (Nd4j.dataType() == DataBuffer.Type.INT) {
            return Nd4j.create(ints2floats(matToInts(m)), longs2ints(shapes), 'c');
        } else if (Nd4j.dataType() == DataBuffer.Type.LONG) {
            return Nd4j.create(longs2doubles(matToLongs(m)), longs2ints(shapes), 'c');
        } else if (Nd4j.dataType() == DataBuffer.Type.FLOAT) {
            return Nd4j.create(matToFloats(m), longs2ints(shapes), 'c');
        } else if (Nd4j.dataType() == DataBuffer.Type.DOUBLE) {
            return Nd4j.create(matToDoubles(m), longs2ints(shapes), 'c');
        } else {
            throw new IllegalArgumentException("Nd4j.dataType should be int, long, float, double.");
        }
    }

    //Convert from ndarray to mat.

    public static Mat ndArrayToFloatMat(INDArray array) {
        Mat m = null;
        m = new Mat((int) (array.shape()[0]), (int) (array.shape()[1]), CvType.CV_32FC((int) (array.shape()[2])));
        m.put(0, 0, Nd4j.toFlattened(array).toFloatVector());
        return m;
    }

    public static Mat ndArrayToDoubleMat(INDArray array) {
        Mat m = null;
        m = new Mat((int) (array.shape()[0]), (int) (array.shape()[1]), CvType.CV_64FC((int) (array.shape()[2])));
        m.put(0, 0, Nd4j.toFlattened(array).toDoubleVector());
        return m;
    }

    //Convert from ndarray to tensor.
    public static Tensor<Integer> ndArrayToIntTensor(INDArray ndArray, long... shapes) {
        return Tensor.create(shapes, IntBuffer.wrap(ndArrayToInts(ndArray)));
    }

    public static Tensor<Float> ndArrayToFloatTensor(INDArray ndArray, long... shapes) {
        return Tensor.create(shapes, FloatBuffer.wrap(ndArrayToFloats(ndArray)));
    }

    public static Tensor<Double> ndArrayToDoubleTensor(INDArray ndArray, long... shapes) {
        return Tensor.create(shapes, DoubleBuffer.wrap(ndArrayToDoubles(ndArray)));
    }

    public static <T> Tensor<T> ndArrayToTensor(INDArray ndArray, Class<T> cls, long... shapes) {
        if (Integer.class.equals(cls)) {
            return (Tensor<T>) ndArrayToIntTensor(ndArray, shapes);
        } else if (Float.class.equals(cls)) {
            return (Tensor<T>) ndArrayToFloatTensor(ndArray, shapes);
        } else if (Double.class.equals(cls)) {
            return (Tensor<T>) ndArrayToDoubleTensor(ndArray, shapes);
        } else {
            throw new IllegalArgumentException("only support int, float, double.");
        }
    }

    //Convert from tensor to ndarray.

    public static INDArray tensorToNdArray(Tensor<?> tensor) {
        if (Nd4j.dataType() == DataBuffer.Type.INT) {
            return Nd4j.create(ints2floats(tensorToInts(tensor)), longs2ints(tensor.shape()), 'c');
        } else if (Nd4j.dataType() == DataBuffer.Type.LONG) {
            return Nd4j.create(longs2doubles(tensorToLongs(tensor)), longs2ints(tensor.shape()), 'c');
        } else if (Nd4j.dataType() == DataBuffer.Type.FLOAT) {
            return Nd4j.create(tensorToFloats(tensor), longs2ints(tensor.shape()), 'c');
        } else if (Nd4j.dataType() == DataBuffer.Type.DOUBLE) {
            return Nd4j.create(tensorToDoubles(tensor), longs2ints(tensor.shape()), 'c');
        } else {
            throw new IllegalArgumentException("Nd4j.dataType should be int, long, float, double.");
        }
    }

    //bytes, shorts, booleans, ints, longs, floats, doubles.

    //to booleans
    public static boolean[] ubytes2booleans(byte[] bytesData) {
        boolean[] booleansData = new boolean[bytesData.length];
        for (int i = 0; i < booleansData.length; i++) {
            booleansData[i] = bytesData[i] != 0;
        }
        return booleansData;
    }

    public static boolean[] bytes2booleans(byte[] bytesData) {
        boolean[] booleansData = new boolean[bytesData.length];
        for (int i = 0; i < booleansData.length; i++) {
            booleansData[i] = bytesData[i] != 0;
        }
        return booleansData;
    }

    public static boolean[] ushorts2booleans(short[] shortsData) {
        boolean[] booleansData = new boolean[shortsData.length];
        for (int i = 0; i < booleansData.length; i++) {
            booleansData[i] = shortsData[i] != 0;
        }
        return booleansData;
    }

    public static boolean[] shorts2booleans(short[] shortsData) {
        boolean[] booleansData = new boolean[shortsData.length];
        for (int i = 0; i < booleansData.length; i++) {
            booleansData[i] = shortsData[i] != 0;
        }
        return booleansData;
    }

    public static boolean[] ints2booleans(int[] intsData) {
        boolean[] booleansData = new boolean[intsData.length];
        for (int i = 0; i < booleansData.length; i++) {
            booleansData[i] = intsData[i] != 0;
        }
        return booleansData;
    }

    public static boolean[] longs2booleans(long[] longsData) {
        boolean[] booleansData = new boolean[longsData.length];
        for (int i = 0; i < booleansData.length; i++) {
            booleansData[i] = longsData[i] != 0;
        }
        return booleansData;
    }

    public static boolean[] floats2booleans(float[] floatsData) {
        boolean[] booleansData = new boolean[floatsData.length];
        for (int i = 0; i < booleansData.length; i++) {
            booleansData[i] = floatsData[i] != 0;
        }
        return booleansData;
    }

    public static boolean[] doubles2booleans(double[] doublesData) {
        boolean[] booleansData = new boolean[doublesData.length];
        for (int i = 0; i < booleansData.length; i++) {
            booleansData[i] = doublesData[i] != 0;
        }
        return booleansData;
    }

    //to bytes

    public static byte[] ushorts2bytes(short[] shortsData) {
        byte[] bytesData = new byte[shortsData.length];
        for (int i = 0; i < bytesData.length; i++) {
            bytesData[i] = (byte) (shortsData[i] & 0xFFFF);
        }
        return bytesData;
    }

    public static byte[] shorts2bytes(short[] shortsData) {
        byte[] bytesData = new byte[shortsData.length];
        for (int i = 0; i < bytesData.length; i++) {
            bytesData[i] = (byte) shortsData[i];
        }
        return bytesData;
    }

    public static byte[] ints2bytes(int[] intsData) {
        byte[] bytesData = new byte[intsData.length];
        for (int i = 0; i < bytesData.length; i++) {
            bytesData[i] = (byte) intsData[i];
        }
        return bytesData;
    }

    public static byte[] longs2bytes(long[] longsData) {
        byte[] bytesData = new byte[longsData.length];
        for (int i = 0; i < bytesData.length; i++) {
            bytesData[i] = (byte) longsData[i];
        }
        return bytesData;
    }

    public static byte[] floats2bytes(float[] floatsData) {
        byte[] bytesData = new byte[floatsData.length];
        for (int i = 0; i < bytesData.length; i++) {
            bytesData[i] = (byte) floatsData[i];
        }
        return bytesData;
    }

    public static byte[] doubles2bytes(double[] doublesData) {
        byte[] bytesData = new byte[doublesData.length];
        for (int i = 0; i < bytesData.length; i++) {
            bytesData[i] = (byte) doublesData[i];
        }
        return bytesData;
    }

    //to shorts
    public static short[] ubytes2shorts(byte[] bytesData) {
        short[] shortsData = new short[bytesData.length];
        for (int i = 0; i < shortsData.length; i++) {
            shortsData[i] = (short) (bytesData[i] & 0xFF);
        }
        return shortsData;
    }

    public static short[] bytes2shorts(byte[] bytesData) {
        short[] shortsData = new short[bytesData.length];
        for (int i = 0; i < shortsData.length; i++) {
            shortsData[i] = (short) bytesData[i];
        }
        return shortsData;
    }

    public static short[] ints2shorts(int[] intsData) {
        short[] shortsData = new short[intsData.length];
        for (int i = 0; i < shortsData.length; i++) {
            shortsData[i] = (short) intsData[i];
        }
        return shortsData;
    }

    public static short[] longs2shorts(long[] longsData) {
        short[] shortsData = new short[longsData.length];
        for (int i = 0; i < shortsData.length; i++) {
            shortsData[i] = (short) longsData[i];
        }
        return shortsData;
    }

    public static short[] floats2shorts(float[] floatsData) {
        short[] shortsData = new short[floatsData.length];
        for (int i = 0; i < shortsData.length; i++) {
            shortsData[i] = (short) floatsData[i];
        }
        return shortsData;
    }

    public static short[] doubles2shorts(double[] doublesData) {
        short[] shortsData = new short[doublesData.length];
        for (int i = 0; i < shortsData.length; i++) {
            shortsData[i] = (short) doublesData[i];
        }
        return shortsData;
    }

    //to ints
    public static int[] ubytes2ints(byte[] bytesData) {
        int[] intsData = new int[bytesData.length];
        for (int i = 0; i < intsData.length; i++) {
            intsData[i] = bytesData[i] & 0xFF;
        }
        return intsData;
    }

    public static int[] bytes2ints(byte[] bytesData) {
        int[] intsData = new int[bytesData.length];
        for (int i = 0; i < intsData.length; i++) {
            intsData[i] = (int) bytesData[i];
        }
        return intsData;
    }

    public static int[] ushorts2ints(short[] shortsData) {
        int[] intsData = new int[shortsData.length];
        for (int i = 0; i < intsData.length; i++) {
            intsData[i] = shortsData[i] & 0xFFFF;
        }
        return intsData;
    }

    public static int[] shorts2ints(short[] shortsData) {
        int[] intsData = new int[shortsData.length];
        for (int i = 0; i < intsData.length; i++) {
            intsData[i] = (int) shortsData[i];
        }
        return intsData;
    }

    public static int[] longs2ints(long[] longsData) {
        int[] intsData = new int[longsData.length];
        for (int i = 0; i < intsData.length; i++) {
            intsData[i] = (int) longsData[i];
        }
        return intsData;
    }

    public static int[] floats2ints(float[] floatsData) {
        int[] intsData = new int[floatsData.length];
        for (int i = 0; i < intsData.length; i++) {
            intsData[i] = (int) floatsData[i];
        }
        return intsData;
    }

    public static int[] doubles2ints(double[] doublesData) {
        int[] intsData = new int[doublesData.length];
        for (int i = 0; i < intsData.length; i++) {
            intsData[i] = (int) doublesData[i];
        }
        return intsData;
    }

    //to longs
    public static long[] ubytes2longs(byte[] bytesData) {
        long[] longsData = new long[bytesData.length];
        for (int i = 0; i < longsData.length; i++) {
            longsData[i] = bytesData[i] & 0xFF;
        }
        return longsData;
    }

    public static long[] bytes2longs(byte[] bytesData) {
        long[] longsData = new long[bytesData.length];
        for (int i = 0; i < longsData.length; i++) {
            longsData[i] = (int) bytesData[i];
        }
        return longsData;
    }

    public static long[] ushorts2longs(short[] shortsData) {
        long[] longsData = new long[shortsData.length];
        for (int i = 0; i < longsData.length; i++) {
            longsData[i] = shortsData[i] & 0xFFFF;
        }
        return longsData;
    }

    public static long[] shorts2longs(short[] shortsData) {
        long[] longsData = new long[shortsData.length];
        for (int i = 0; i < longsData.length; i++) {
            longsData[i] = (int) shortsData[i];
        }
        return longsData;
    }

    public static long[] ints2longs(int[] intsData) {
        long[] longsData = new long[intsData.length];
        for (int i = 0; i < longsData.length; i++) {
            longsData[i] = (int) intsData[i];
        }
        return longsData;
    }

    public static long[] floats2longs(float[] floatsData) {
        long[] longsData = new long[floatsData.length];
        for (int i = 0; i < longsData.length; i++) {
            longsData[i] = (int) floatsData[i];
        }
        return longsData;
    }

    public static long[] doubles2longs(double[] doublesData) {
        long[] longsData = new long[doublesData.length];
        for (int i = 0; i < longsData.length; i++) {
            longsData[i] = (int) doublesData[i];
        }
        return longsData;
    }

    //to floats
    public static float[] ubytes2floats(byte[] bytesData) {
        float[] floatsData = new float[bytesData.length];
        for (int i = 0; i < floatsData.length; i++) {
            floatsData[i] = bytesData[i] & 0xFF;
        }
        return floatsData;
    }

    public static float[] bytes2floats(byte[] bytesData) {
        float[] floatsData = new float[bytesData.length];
        for (int i = 0; i < floatsData.length; i++) {
            floatsData[i] = (int) bytesData[i];
        }
        return floatsData;
    }

    public static float[] ushorts2floats(short[] shortsData) {
        float[] floatsData = new float[shortsData.length];
        for (int i = 0; i < floatsData.length; i++) {
            floatsData[i] = shortsData[i] & 0xFFFF;
        }
        return floatsData;
    }

    public static float[] shorts2floats(short[] shortsData) {
        float[] floatsData = new float[shortsData.length];
        for (int i = 0; i < floatsData.length; i++) {
            floatsData[i] = (int) shortsData[i];
        }
        return floatsData;
    }

    public static float[] ints2floats(int[] intsData) {
        float[] floatsData = new float[intsData.length];
        for (int i = 0; i < floatsData.length; i++) {
            floatsData[i] = (int) intsData[i];
        }
        return floatsData;
    }

    public static float[] longs2floats(long[] longsData) {
        float[] floatsData = new float[longsData.length];
        for (int i = 0; i < floatsData.length; i++) {
            floatsData[i] = (int) longsData[i];
        }
        return floatsData;
    }

    public static float[] doubles2floats(double[] doublesData) {
        float[] floatsData = new float[doublesData.length];
        for (int i = 0; i < floatsData.length; i++) {
            floatsData[i] = (int) doublesData[i];
        }
        return floatsData;
    }

    //to doubles
    public static double[] ubytes2doubles(byte[] bytesData) {
        double[] doublesData = new double[bytesData.length];
        for (int i = 0; i < doublesData.length; i++) {
            doublesData[i] = bytesData[i] & 0xFF;
        }
        return doublesData;
    }

    public static double[] bytes2doubles(byte[] bytesData) {
        double[] doublesData = new double[bytesData.length];
        for (int i = 0; i < doublesData.length; i++) {
            doublesData[i] = (int) bytesData[i];
        }
        return doublesData;
    }

    public static double[] ushorts2doubles(short[] shortsData) {
        double[] doublesData = new double[shortsData.length];
        for (int i = 0; i < doublesData.length; i++) {
            doublesData[i] = shortsData[i] & 0xFFFF;
        }
        return doublesData;
    }

    public static double[] shorts2doubles(short[] shortsData) {
        double[] doublesData = new double[shortsData.length];
        for (int i = 0; i < doublesData.length; i++) {
            doublesData[i] = (int) shortsData[i];
        }
        return doublesData;
    }

    public static double[] ints2doubles(int[] intsData) {
        double[] doublesData = new double[intsData.length];
        for (int i = 0; i < doublesData.length; i++) {
            doublesData[i] = (int) intsData[i];
        }
        return doublesData;
    }

    public static double[] longs2doubles(long[] longsData) {
        double[] doublesData = new double[longsData.length];
        for (int i = 0; i < doublesData.length; i++) {
            doublesData[i] = (int) longsData[i];
        }
        return doublesData;
    }

    public static double[] floats2doubles(float[] floatsData) {
        double[] doublesData = new double[floatsData.length];
        for (int i = 0; i < doublesData.length; i++) {
            doublesData[i] = (int) floatsData[i];
        }
        return doublesData;
    }
}
