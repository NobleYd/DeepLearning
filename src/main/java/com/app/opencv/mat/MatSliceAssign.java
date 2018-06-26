package com.app.opencv.mat;

import com.app.utils.MatUtils;
import nu.pattern.OpenCV;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.*;

/***
 * 对Mat进行Slice并赋值。
 *
 * 赋值我们主要通过copyTo，setTo方法。AssignTo当前不清楚具体用法。
 *
 * copyTo用于多个Mat的数据传输。
 *
 * setTo用于Sclar数据的传输。
 *
 */
public class MatSliceAssign {

    @Before
    public void loadNativeLibrary(){
        OpenCV.loadShared();
    }

    //1. 将数据分为 mat，slice，sclar三种，然后考虑多种情况进行测试。

    @Test
    public void scalar2mat() {
        Mat m1 = MatHelper.getByteMat(5, 5, 2);
        m1.setTo(Scalar.all(5));
        MatUtils.print2dMat(m1);
    }


    @Test
    public void scalar2slice() {
        Mat m1 = MatHelper.getByteMat(5, 5, 2);
        m1.submat(new Range(1, 3), new Range(1, 4)).setTo(Scalar.all(5));
        MatUtils.print2dMat(m1);
    }

    @Test
    public void mat2mat() {
        Mat m1 = MatHelper.getByteMat(5, 5, 2);
        Mat m2 = Mat.zeros(5, 5, CvType.CV_8UC2);

        System.out.println("Before, m1.dataAddr()=" + m1.dataAddr());
        m2.copyTo(m1);
        System.out.println("After,  m1.dataAddr()=" + m1.dataAddr());
        //一致
        MatUtils.print2dMat(m1);
    }

    @Test
    public void mat2slice() {
        Mat m1 = MatHelper.getByteMat(5, 5, 2);
        Mat m2 = Mat.zeros(3, 3, CvType.CV_8UC2);

        System.out.println("Before, m1.dataAddr()=" + m1.dataAddr());
        m2.copyTo(m1.submat(new Range(1, 4), new Range(1, 4)));
        System.out.println("After,  m1.dataAddr()=" + m1.dataAddr());
        //一致
        MatUtils.print2dMat(m1);
    }

    @Test
    public void slice2mat() {
        Mat m1 = MatHelper.getByteMat(5, 5, 2);
        Mat m2 = Mat.zeros(10, 10, CvType.CV_8UC2);

        System.out.println("Before, m1.dataAddr()=" + m1.dataAddr());
        m2.submat(new Range(1, 6), new Range(1, 6)).copyTo(m1);
        System.out.println("After,  m1.dataAddr()=" + m1.dataAddr());
        //一致
        MatUtils.print2dMat(m1);
    }

    @Test
    public void slice2slice() {
        Mat m1 = MatHelper.getByteMat(5, 5, 2);
        Mat m2 = Mat.zeros(10, 10, CvType.CV_8UC2);

        System.out.println("Before, m1.dataAddr()=" + m1.dataAddr());
        m2.submat(new Range(1, 4), new Range(1, 4))
                .copyTo(m1.submat(new Range(1, 4), new Range(1, 4)));
        System.out.println("After,  m1.dataAddr()=" + m1.dataAddr());
        //一致
        MatUtils.print2dMat(m1);
    }

    //2. 测试copyTo的shape不同的情况。
    // 根据MatCopy中的测试，a.copyTo(b)，若a和b的shape不同，b的shape会被覆盖。
    // 但是，如果b是slice情况会如何呢？毕竟slice只是submatrix，并没有独立数据。

    //结果总结：a.copyTo(b)，b为slice，shape不同情况，b不会发生任何变化。不报错！

    @Test
    public void mat2sliceAltB() {
        Mat b = MatHelper.getByteMat(5, 5, 2);
        Mat a = Mat.zeros(3, 3, CvType.CV_8UC2);

        System.out.println("Before, b.dataAddr()=" + b.dataAddr());
        a.copyTo(b.submat(new Range(1, 5), new Range(1, 5)));
        System.out.println("After,  b.dataAddr()=" + b.dataAddr());
        //一致
        MatUtils.print2dMat(b);
        //-->异常，复制没成功。b没有任何变化。
    }

    @Test
    public void mat2sliceAgtB() {
        Mat b = MatHelper.getByteMat(5, 5, 2);
        Mat a = Mat.zeros(4, 4, CvType.CV_8UC2);

        System.out.println("Before, b.dataAddr()=" + b.dataAddr());
        a.copyTo(b.submat(new Range(1, 4), new Range(1, 4)));
        System.out.println("After,  b.dataAddr()=" + b.dataAddr());
        //一致
        MatUtils.print2dMat(b);
        //-->异常，复制没成功。b没有任何变化。
    }

    @Test
    public void slice2sliceAltB() {
        Mat b = MatHelper.getByteMat(5, 5, 2);
        Mat a = Mat.zeros(10, 10, CvType.CV_8UC2);

        System.out.println("Before, b.dataAddr()=" + b.dataAddr());
        a.submat(new Range(1, 4), new Range(1, 4))
                .copyTo(b.submat(new Range(1, 5), new Range(1, 5)));
        System.out.println("After,  b.dataAddr()=" + b.dataAddr());
        //一致
        MatUtils.print2dMat(b);
        //-->异常，复制没成功。b没有任何变化。
    }

    @Test
    public void slice2sliceAgtB() {
        Mat b = MatHelper.getByteMat(5, 5, 2);
        Mat a = Mat.zeros(10, 10, CvType.CV_8UC2);

        System.out.println("Before, b.dataAddr()=" + b.dataAddr());
        a.submat(new Range(1, 5), new Range(1, 5))
                .copyTo(b.submat(new Range(1, 4), new Range(1, 4)));
        System.out.println("After,  b.dataAddr()=" + b.dataAddr());
        //一致
        MatUtils.print2dMat(b);
        //-->异常，复制没成功。b没有任何变化。
    }

}
