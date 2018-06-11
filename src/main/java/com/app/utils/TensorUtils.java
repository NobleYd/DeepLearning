package com.app.utils;

import org.tensorflow.Tensor;

import java.nio.ByteBuffer;

@Deprecated
public class TensorUtils {

    private TensorUtils() {
    }

    public static byte[][] toStringBytesArray(Tensor tensor) {
        System.out.println(String.format("bytes count %d", tensor.numBytes()));

        ByteBuffer byteBuffer = ByteBuffer.allocate(tensor.numBytes());
        tensor.writeTo(byteBuffer);

        byteBuffer.position(tensor.numElements() * 8);

        int hexAdecima = 1;
        int count = 0;
        int b = (byteBuffer.get() & 0xFF);
        for (; b >= 0x80; ) {
            count += (b - 0x80) * hexAdecima;
            hexAdecima *= 0x80;
            b = (byteBuffer.get() & 0xFF);
        }
        count += b * hexAdecima;

        byte[][] result = new byte[tensor.numElements()][];
        for (int i = 0; i < result.length; i++) {
            byte[] buffer = new byte[count];
            byteBuffer.get(buffer);

            result[i] = buffer;
        }

        return result;
    }

}