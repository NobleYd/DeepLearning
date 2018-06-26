package com.app.opencv.mat;

import com.app.utils.MatUtils;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import java.nio.ByteBuffer;

/***
 * 注意，统一使用经过测试的方法，未经测试方法尽量不用，坑太多。
 */
public class MatCreate {
    @Before
    public void loadLibrary() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    //标准创建Mat（推荐，但是本例有危险。）
    @Test
    public void newMat1() {
        Mat m1 = new Mat(3, 3, CvType.CV_8UC1);
        MatUtils.print2dMat(m1);
        //-->多次运行会发现，输出结果不一样。
        //即：new Mat方式没有初始化Mat。
        //所以谨慎使用，务必先创建，然后在使用之前初始化。

        //初始化方式：
        m1.setTo(Scalar.all(10));
        MatUtils.print2dMat(m1);//-->完美
    }

    /****
     * Mat.zeros和Mat.ones。
     *
     * 不推荐使用Mat.zeros和Mat.ones。
     * 原因1：不支持创建并直接其他Sclar值，扩展性并不高。
     * 原因2：可以通过new Mat指定Sclar方式替代。
     * 原因3：在多通道情况下，Mat.zeros和Mat.ones方式的实际效果和想象不同。
     */
    @Test
    public void matZerosOnes() {
        Mat m1 = Mat.zeros(3, 3, CvType.CV_8UC2);
        MatUtils.print2dMat(m1);//-->可用，只是为了统一，如果不使用Mat.ones，推荐zeros也不使用。

        Mat m2 = Mat.ones(3, 3, CvType.CV_8UC2);
        MatUtils.print2dMat(m2);
        //-->从结果可以看到，只有通道1被设置为1，其他通道是0。
        //即：Mat.ones只设置通道1为1而已。
    }

    //标准创建Mat+赋初值
    //注意，new Mat支持带一个buffer的数据，不推荐，因为经过测试使用方式很乱。
    @Test
    public void newMat2() {
        Mat m1 = new Mat(3, 3, CvType.CV_8UC2, Scalar.all(10));
        MatUtils.print2dMat(m1);
        //使用Sclar方式更加类似于初始化，可以用于替代Mat.zeros，ones等。
    }

    //其他复杂数据情况，使用先创建，然后mat.put方式实现。

}
