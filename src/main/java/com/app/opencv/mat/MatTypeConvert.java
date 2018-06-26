package com.app.opencv.mat;

import nu.pattern.OpenCV;
import org.junit.Before;
import org.junit.Test;
import org.nd4j.linalg.util.SerializationUtils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/***
 * Mat.convertTo方法。
 */
public class MatTypeConvert {

    @Before
    public void loadNativeLibrary(){
        OpenCV.loadShared();
    }

    @Test
    public void matTypeConvert1() {
        Mat m1 = new Mat(3, 3, CvType.CV_8UC1);
        Mat m2 = new Mat();
        System.out.println("m1: " + m1);
        m1.convertTo(m2, CvType.CV_32FC1);
        System.out.println("After convert, m1: " + m1);//-->convert不影响m1自身。
        System.out.println("After convert, m2: " + m2);
        System.out.println("m2.isSubmatrix(): " + m2.isSubmatrix());//convert返回的是复制数据，不共享。
    }

    //原地转换类型
    @Test
    public void matTypeConvert2() {
        Mat m1 = new Mat(3, 3, CvType.CV_8UC1);
        System.out.println("m1: " + m1);
        m1.convertTo(m1, CvType.CV_32FC1);
        System.out.println("After convert, m1: " + m1);
        //-->注意dataAddr变化（毕竟不同类型数据需要的存储空间大小不一样）
    }

}
