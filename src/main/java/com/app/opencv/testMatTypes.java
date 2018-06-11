package com.app.opencv;

import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class testMatTypes {

    @Before
    public void before() {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

    }

    @Test
    public void types() {
        //type = depth + channel_number
        //此处讨论depth。

        //整数类
        int CV_8U = 0;
        int CV_8S = 1;
        int CV_16U = 2;
        int CV_16S = 3;
        int CV_32S = 4;
        //浮点数类
        int CV_32F = 5;
        int CV_64F = 6;
        //用户类?
        int CV_USRTYPE1 = 7;

    }

    /***
     * Mat的类型是严格检查的。Mat.get和Mat.put方法对不符合的类型报错。
     */
    @Test
    public void testGetPutTypes() {
        Mat intMat = new Mat(3, 3, CvType.CV_8UC1);
        //intMat.put(0, 0, new float[]{1, 2F, 2.2F});
        //-->error
        //java.lang.UnsupportedOperationException: Mat data type is not compatible: 0

        //intMat.get(0,0,new float[5]);
        //-->error
        //java.lang.UnsupportedOperationException: Mat data type is not compatible: 0
    }

    /***
     * 因此，get和put需要使用严格一致的类型。
     * 那么这些类型如何对应呢？
     * 下面进行测试。
     */
    @Test
    public void testJavaTypes() {
        //整数类
        int CV_8U = 0;
        Mat m1 = new Mat(3, 3, CvType.CV_8UC1);
        m1.put(0, 0, new byte[]{1, 2, 3});//ok
        //m1.put(0,0,new short[]{1,2,3});//error
        //m1.put(0,0,new int[]{1,2,3});//error

        int CV_8S = 1;
        Mat m2 = new Mat(3, 3, CvType.CV_8SC1);
        m2.put(0, 0, new byte[]{1, 2, 3});//ok
        //m2.put(0,0,new short[]{1,2,3});//error
        //m2.put(0,0,new int[]{1,2,3});//error

        int CV_16U = 2;
        Mat m3 = new Mat(3, 3, CvType.CV_16UC1);
        //m3.put(0,0,new byte[]{1,2,3});//error
        m3.put(0, 0, new short[]{1, 2, 3});//ok
        //m3.put(0,0,new int[]{1,2,3});//error

        int CV_16S = 3;
        Mat m4 = new Mat(3, 3, CvType.CV_16SC1);
        //m4.put(0, 0, new byte[]{1, 2, 3});//error
        m4.put(0, 0, new short[]{1, 2, 3});//ok
        //m4.put(0, 0, new int[]{1, 2, 3});//error

        int CV_32S = 4;
        Mat m5 = new Mat(3, 3, CvType.CV_32SC1);
        //m5.put(0, 0, new byte[]{1, 2, 3});//error
        //m5.put(0, 0, new short[]{1, 2, 3});//error
        m5.put(0, 0, new int[]{1, 2, 3});//ok
        //m5.put(0, 0, new float[]{1F, 2F, 3F});//error

        //浮点数类(注意除了浮点类float和double之间可以混用，其他类型都是严格一致。)
        int CV_32F = 5;
        Mat m6 = new Mat(3, 3, CvType.CV_32FC1);
        //m6.put(0, 0, new int[]{1, 2, 3});//error
        m6.put(0, 0, new float[]{1, 2, 3});//ok
        m6.put(0, 0, new double[]{1, 2, 3});//ok

        int CV_64F = 6;
        Mat m7 = new Mat(3, 3, CvType.CV_64FC1);
        //m6.put(0, 0, new int[]{1, 2, 3});//error
        m6.put(0, 0, new float[]{1, 2, 3});//ok
        m6.put(0, 0, new double[]{1, 2, 3});//ok


    }

}
