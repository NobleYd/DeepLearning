package com.app.tensorflow;

import org.junit.Test;
import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.Tensors;

import java.nio.*;

public class TestTensor {

    /***
     * 总结：类型之间相互严格检查。
     *
     * 除了byte比较特殊。
     * 任何类型都可以放入bytebuffer中。只要buffer空间足够。
     */
    @Test
    public void testTypes() {
        DataType dataType = DataType.BOOL;//byte
        dataType = DataType.UINT8;
        dataType = DataType.INT32;//int 4byte
        dataType = DataType.INT64;//long 8byte
        dataType = DataType.FLOAT;//float 4byte
        dataType = DataType.DOUBLE;//double 8byte


        boolean[] booleans = new boolean[1];
        byte[] bytes = new byte[1];
        byte[] bytes2 = new byte[2];
        byte[] bytes3 = new byte[3];
        byte[] bytes4 = new byte[4];
        byte[] bytes5 = new byte[5];
        byte[] bytes6 = new byte[6];
        byte[] bytes7 = new byte[7];
        byte[] bytes8 = new byte[8];

        short[] shorts = new short[1];
        int[] ints = new int[1];
        long[] longs = new long[1];
        float[] floats = new float[1];
        double[] doubles = new double[1];
        String[] strings = new String[1];

        Tensor<Boolean> booleanTensor = Tensors.create(true);
        //没有创建byte和short的tensor的入口。
        //Tensor<Byte> byteTensor = Tensors.create();
        //Tensor<Byte> byteTensor = new Tensor<Byte>();
        //Tensor<Short> shortTensor = Tensors.create(1);
        Tensor<Integer> integerTensor = Tensors.create(1);
        Tensor<Long> longTensor = Tensors.create(1L);
        Tensor<Float> floatTensor = Tensors.create(1.2F);
        Tensor<Double> doubleTensor = Tensors.create(1.2);
        Tensor<String> stringTypeData = Tensors.create("stringType");

        //tensor.writeTo(xx);
        //支持ByteBuffer，IntBuffer，LongBuffer，FloatBuffer，DoubleBuffer

        //下面测试不同类型的报错情况
        booleanTensor.writeTo(ByteBuffer.wrap(bytes));
        booleanTensor.writeTo(ByteBuffer.wrap(bytes2));
        //booleanTensor.writeTo(IntBuffer.wrap(ints));//error
        //booleanTensor.writeTo(LongBuffer.wrap(longs));//error
        //booleanTensor.writeTo(FloatBuffer.wrap(floats));//error
        //booleanTensor.writeTo(DoubleBuffer.wrap(doubles));//error

        integerTensor.writeTo(ByteBuffer.wrap(bytes4));
        integerTensor.writeTo(IntBuffer.wrap(ints));
        //integerTensor.writeTo(LongBuffer.wrap(longs));//error
        //integerTensor.writeTo(FloatBuffer.wrap(floats));//error
        //integerTensor.writeTo(DoubleBuffer.wrap(doubles));//error

        longTensor.writeTo(ByteBuffer.wrap(bytes8));
        //longTensor.writeTo(IntBuffer.wrap(ints));
        longTensor.writeTo(LongBuffer.wrap(longs));
        //longTensor.writeTo(FloatBuffer.wrap(floats));
        //longTensor.writeTo(DoubleBuffer.wrap(doubles));

        floatTensor.writeTo(ByteBuffer.wrap(bytes4));
        //floatTensor.writeTo(IntBuffer.wrap(ints));
        //floatTensor.writeTo(LongBuffer.wrap(longs));
        floatTensor.writeTo(FloatBuffer.wrap(floats));
        //floatTensor.writeTo(DoubleBuffer.wrap(doubles));

        doubleTensor.writeTo(ByteBuffer.wrap(bytes8));
        //doubleTensor.writeTo(IntBuffer.wrap(ints));
        //doubleTensor.writeTo(LongBuffer.wrap(longs));
        //doubleTensor.writeTo(FloatBuffer.wrap(floats));
        doubleTensor.writeTo(DoubleBuffer.wrap(doubles));


    }

}
