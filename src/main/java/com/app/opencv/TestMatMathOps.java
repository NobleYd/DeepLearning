package com.app.opencv;

import com.app.utils.MatUtils;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import java.util.Arrays;

public class TestMatMathOps {


    @Before
    public void loadLibrary() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    @Test
    public void testAdd1() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC1);
        float[] data = new float[25];
        Arrays.fill(data, 25F);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);

        Core.add(m1, new Scalar(1), m1);

        MatUtils.print2dMat(m1);
    }


    @Test
    public void testAdd2() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC2);
        float[] data = new float[25 * 2];
        Arrays.fill(data, 25);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);

        Core.add(m1, new Scalar(1), m1);
        MatUtils.print2dMat(m1);

        Core.add(m1, new Scalar(1, 1), m1);
        MatUtils.print2dMat(m1);

        Core.add(m1, new Scalar(1, 1, 1), m1);
        MatUtils.print2dMat(m1);

    }

    @Test
    public void testAdd3() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC3);
        float[] data = new float[25 * 3];
        Arrays.fill(data, 25);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);

        Core.add(m1, Scalar.all(5), m1);
        MatUtils.print2dMat(m1);
    }

    @Test
    public void testAdd4() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC4);
        float[] data = new float[25 * 4];
        Arrays.fill(data, 25);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);

        Core.add(m1, Scalar.all(5), m1);
        MatUtils.print2dMat(m1);
    }

    //超过4个channel的情况
    @Test
    public void testAdd5() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC(5));
        float[] data = new float[25 * 5];
        Arrays.fill(data, 25);
        m1.put(0, 0, data);

        //注意，公用data是没问题的。因为Mat.put方式只是使用data进行数据复制，而不是共享这部分数据。
        Mat m2 = new Mat(5, 5, CvType.CV_32FC(5));
        Arrays.fill(data, 10);
        m2.put(0, 0, data);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);

        Core.add(m1, m2, m1);
        MatUtils.print2dMat(m1);
    }

    //超过4个channel的情况
    @Test
    public void testAdd6() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC(5));
        float[] data = new float[25 * 5];
        Arrays.fill(data, 25);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);

        //这种方式虽然必须指定另一个Mat，但可以通过指定beta为0消除它。
        //如何通过gamma指定需要加的常数。
        Core.addWeighted(m1, 1.0, new Mat(5, 5, CvType.CV_32FC(5)), 0.0, 15.0, m1);

        MatUtils.print2dMat(m1);
    }

    //加减乘除类似，不一个一个测试。
    @Test
    public void testScale1() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC2);
        float[] data = new float[25 * 2];
        Arrays.fill(data, 25);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);

        Core.multiply(m1, Scalar.all(2), m1, 3.0);
        MatUtils.print2dMat(m1);
    }

    @Test
    public void testScale2() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC2);
        float[] data = new float[25 * 2];
        Arrays.fill(data, 25);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);


        Mat m2 = new Mat(5, 5, CvType.CV_32FC2);
        Arrays.fill(data, 2);
        m2.put(0, 0, data);
        MatUtils.print2dMat(m2);

        //scale这个参数可以当作使用常数乘法
        Core.multiply(m1, m2, m1, 3.0);
        MatUtils.print2dMat(m1);
    }

    //Mat和常数的乘法(貌似目前只能通过构造另一个Mat实现)
    //并没有提供矩阵和常量的乘法（4个channel以内可以通过Scalar实现）
    //超过4个channel的方式通过scale参数实现，但是就需要提供另一个Mat参数（乘法的话可以都设置为1）
    //但是这个全部是1的Mat的构造好像也挺麻烦，不能通过Mat.ones实现，因为通过测试发现Mat.ones构造的Mat只能是1channel的Mat。
    @Test
    public void testScale3() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC1);
        float[] data = new float[25 * 1];
        Arrays.fill(data, 25);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);

        Core.multiply(m1, Mat.ones(5, 5, CvType.CV_32FC1), m1, 3.0);
        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(Mat.ones(5, 5, CvType.CV_32FC1));

    }

    //（4channel以上）多通道情况下乘常数
    //上一个例子中通过Mat.ones实现的1通道情况。-->实际没啥用，因为1channel情况可以通过Scalar更简单的实现。
    //4channel以上情况，需要通过Mat实现。但是为了避免准备数据，通过Arrays.fill等准备数据，太复杂。
    //可以使用AddWeighted方式。
    //使用一个合适shape，值随意的Mat，配合beta为0。(-->最新发现：最简单是重复使用Mat1，反正shape合适，也不需要用到其value)
    //gamma根据情况看是否需要设置0。
    //alpha则设置为要乘的常数。
    @Test
    public void testScale4() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC(5));
        float[] data = new float[25 * 5];
        Arrays.fill(data, 25);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);
        //乘一个常数
        Core.addWeighted(m1, 3.0, m1, 0.0, 0.0, m1);
        MatUtils.print2dMat(m1);
    }

    //总结
    //加法，乘法可以统一使用addWeighted会很方便。


    @Test
    public void testDivide1() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC(5));
        float[] data = new float[25 * 5];
        Arrays.fill(data, 4);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);
        //常数/Mat
        Core.divide(1.0, m1, m1);
        MatUtils.print2dMat(m1);

        //Mat/常数(只能通过addweight实现，或者就是构造一个全部1的Mat作为Mat2，如下)
        Mat m2 = new Mat(5, 5, CvType.CV_32FC(5));
        Arrays.fill(data, 1);
        m2.put(0, 0, data);
        Core.divide(m1, m2, m1, 1 / 2.0);//事实是，除法中scale也是乘法的意思。所以还是按照乘法实现。
        MatUtils.print2dMat(m1);


    }

}
