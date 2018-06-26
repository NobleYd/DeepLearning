package com.app.opencv.mat;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class MatHelper {
    public static Mat getFloatMat(int rows, int cols, int channels) {
        float[] data = new float[rows * cols * channels];
        float tmp = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int c = 0; c < channels; c++) {
                    tmp = (float) (10 * i + j + 0.1 * c);
                    data[i * (cols * channels) + j * channels + c] = tmp;
                }
            }
        }
        Mat m1 = new Mat(rows, cols, CvType.CV_32FC(channels));
        m1.put(0, 0, data);
        return m1;
    }

    public static Mat getByteMat(int rows, int cols, int channels) {
        byte[] data = new byte[rows * cols * channels];
        float tmp = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tmp = (float) (10 * i + j);
                for (int c = 0; c < channels; c++) {
                    data[i * (cols * channels) + j * channels + c] = (byte) tmp;
                }
            }
        }
        Mat m1 = new Mat(rows, cols, CvType.CV_8UC(channels));
        m1.put(0, 0, data);
        return m1;
    }
}
