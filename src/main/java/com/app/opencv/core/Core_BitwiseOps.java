package com.app.opencv.core;

import com.app.utils.MatUtils;
import nu.pattern.OpenCV;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.*;

/***
 *
 * Core的bitwise操作，操作列表如下：
 *
 * Core.bitwise_and
 * Core.bitwise_or
 * Core.bitwise_not
 * Core.bitwise_xor
 *
 * -->以及利用bitwise操作完成logical操作。
 *
 */
public class Core_BitwiseOps {

    @Before
    public void loadNativeLibrary() {
        OpenCV.loadShared();
    }

    @Test
    public void bitwiseAnd() {
        //7 = 4+2+1 --> 2进制: 00000111
        Mat m1 = new Mat(3, 3, CvType.CV_8UC1, Scalar.all(7));
        //101 = 64+32+4+1 --> 2进制: 01100101
        Mat m2 = new Mat(3, 3, CvType.CV_8UC1, Scalar.all(101));

        Mat m3 = new Mat();
        Core.bitwise_and(m1, m2, m3);
        MatUtils.print2dMat(m3);//all 5

        System.out.println("7 & 101 = " + (7 & 101));//5
    }

    @Test
    public void bitwiseOr() {
        //7 = 4+2+1 --> 2进制: 00000111
        Mat m1 = new Mat(3, 3, CvType.CV_8UC1, Scalar.all(7));
        //101 = 64+32+4+1 --> 2进制: 01100101
        Mat m2 = new Mat(3, 3, CvType.CV_8UC1, Scalar.all(101));

        Mat m3 = new Mat();
        Core.bitwise_or(m1, m2, m3);
        MatUtils.print2dMat(m3);//all 103

        System.out.println("7 | 101 = " + (7 | 101));//103
    }

    @Test
    public void bitwiseXor() {
        //7 = 4+2+1 --> 2进制: 00000111
        Mat m1 = new Mat(3, 3, CvType.CV_8UC1, Scalar.all(7));
        //101 = 64+32+4+1 --> 2进制: 01100101
        Mat m2 = new Mat(3, 3, CvType.CV_8UC1, Scalar.all(101));

        Mat m3 = new Mat();
        Core.bitwise_xor(m1, m2, m3);
        MatUtils.print2dMat(m3);//all 98

        System.out.println("7 ^ 101 = " + (7 ^ 101));//98
    }

    @Test
    public void bitwiseNot() {
        //7 = 4+2+1 --> 2进制: 00000111
        Mat m1 = new Mat(3, 3, CvType.CV_8UC1, Scalar.all(7));

        Mat m3 = new Mat();
        Core.bitwise_not(m1, m3);
        MatUtils.print2dMat(m3);//all 98

        System.out.println("~7 = " + (~7));//-8
        System.out.println("(256 + (~7)) = " + (256 + (~7)));//248
    }

    //利用bitwise操作完成logical操作。
    //根据Compare的测试，opencv的compare使用0表示false，255表示true。
    //因此，两者配合可以完美实现逻辑与，逻辑或，逻辑非。
    @Test
    public void logicalNot() {
        Mat m1 = new Mat(5, 5, CvType.CV_8UC3);
        Mat m2 = new Mat(5, 5, CvType.CV_8UC3);
        //随机数据。

        Mat m3 = new Mat();
        Core.compare(m1, m2, m3, Core.CMP_GT);
        MatUtils.print2dMat(m3);
        Core.bitwise_not(m3, m3);
        MatUtils.print2dMat(m3);
    }

    @Test
    public void logicalAnd() {
        Mat m1 = new Mat(5, 5, CvType.CV_8UC3);
        Mat m2 = new Mat(5, 5, CvType.CV_8UC3);
        Mat m3 = new Mat(5, 5, CvType.CV_8UC3);
        Core.randn(m1, 1, 1);
        Core.randn(m2, 1, 1);
        Core.randn(m3, 1, 1);
        //随机数据。

        Mat m12 = new Mat();
        Mat m13 = new Mat();
        Core.compare(m1, m2, m12, Core.CMP_GT);
        Core.compare(m1, m3, m13, Core.CMP_GT);
        MatUtils.print2dMat(m12);
        MatUtils.print2dMat(m13);

        Mat m4 = new Mat();
        Core.bitwise_and(m12, m13, m4);
        MatUtils.print2dMat(m4);
    }

    @Test
    public void logicalOr() {
        Mat m1 = new Mat(5, 5, CvType.CV_8UC3);
        Mat m2 = new Mat(5, 5, CvType.CV_8UC3);
        Mat m3 = new Mat(5, 5, CvType.CV_8UC3);
        Core.randn(m1, 1, 1);
        Core.randn(m2, 1, 1);
        Core.randn(m3, 1, 1);
        //随机数据。

        Mat m12 = new Mat();
        Mat m13 = new Mat();
        Core.compare(m1, m2, m12, Core.CMP_GT);
        Core.compare(m1, m3, m13, Core.CMP_GT);
        MatUtils.print2dMat(m12);
        MatUtils.print2dMat(m13);

        Mat m4 = new Mat();
        Core.bitwise_or(m12, m13, m4);
        MatUtils.print2dMat(m4);
    }

}
