package com.app.opencv.mat;

import com.app.utils.MatUtils;
import nu.pattern.OpenCV;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

/***
 * copyTo
 */
public class MatSetTo {

    @Before
    public void loadNativeLibrary() {
        OpenCV.loadShared();
    }

    /***
     * a.setTo(b)
     * --> a = b
     * 注意是b向a赋值。
     *
     * 使用Scalar赋值。
     */
    @Test
    public void matSetTo1() {
        Mat m1 = MatHelper.getFloatMat(3, 3, 2);
        Mat m2 = new Mat(5, 5, CvType.CV_32FC2, Scalar.all(10));

        m1.setTo(Scalar.all(10));
        MatUtils.print2dMat(m1);
    }

    /***
     * 其他重载目前发现比较复杂，不推荐使用。
     */

    //经过多次测试，终于知道其他情况啥意思了。
    //用处不大，下面简单看看。
    //setTo(Mat value, Mat mask)
    //value：这个必须是1x1或者1xn或者nx1，并且n必须等于通道数。

    //1x4情况【推荐】
    @Test
    public void matSetTo2() {
        Mat m1 = MatHelper.getFloatMat(3, 3, 4);
        Mat m2 = new Mat(1, 4, CvType.CV_32FC4);
        m2.put(0, 0, new float[]{11, 12, 13, 14});
        m2.put(0, 1, new float[]{21, 22, 23, 24});
        m2.put(0, 2, new float[]{31, 32, 33, 34});
        m2.put(0, 3, new float[]{41, 42, 43, 44});

        System.out.println("Before, m1: " + m1);
        Mat m3 = m1.setTo(m2);
        System.out.println("After, m1: " + m1);
        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m3);

    }


    //1x1情况
    //1x1情况下，只使用第一个元素的第一个channel的数字。
    @Test
    public void matSetTo3() {
        Mat m1 = MatHelper.getFloatMat(3, 3, 4);
        Mat m2 = new Mat(1, 1, CvType.CV_32FC4, new Scalar(1, 2, 3, 4));

        System.out.println("Before, m1: " + m1);
        Mat m3 = m1.setTo(m2);
        System.out.println("After, m1: " + m1);
        System.out.println("Return m3: " + m3+", m3==m1: " + (m3==m1));//false
        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m3);

    }


}
