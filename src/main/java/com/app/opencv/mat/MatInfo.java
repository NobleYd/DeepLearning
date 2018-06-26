package com.app.opencv.mat;

import nu.pattern.OpenCV;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;

public class MatInfo {

    @Before
    public void loadNativeLibrary(){
        OpenCV.loadShared();
    }

    @Test
    public void matBasicInfo() {
        System.out.println();

        Mat m1 = MatHelper.getFloatMat(2, 3, 2);

        System.out.println("depth: " + m1.depth());//5(精度，具体看CvType)
        System.out.println("elemSize: " + m1.elemSize());//8(单像素字节数)
        System.out.println("elemSize1: " + m1.elemSize1());//4(单像素单通道字节数)
        System.out.println("channels: " + m1.channels());//2
        System.out.println("total: " + m1.total());//6
        System.out.println("size: " + m1.size());//3x2
        System.out.println("dims:" + m1.dims());//2
        System.out.println("rows:" + m1.rows());//2
        System.out.println("cols:" + m1.cols());//3
        System.out.println("step1:" + m1.step1());//6(单行)
        System.out.println("type:" + m1.type());//13
        // type: CV_(位数)+(数据类型)+(通道数)
    }

}
