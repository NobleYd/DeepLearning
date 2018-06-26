package com.app.opencv.mat;

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
public class MatCopy {

    @Before
    public void loadNativeLibrary(){
        OpenCV.loadShared();
    }

    /***
     * a.copyTo(b)
     * --> b = a
     * 注意是a向b赋值。
     *
     * 结论：如果a和b的shape不一致，会覆盖b的shape，包括channels信息。
     * 因此，如果真要直接覆盖复制，根本没必要指定b的shape，以及值，反正都会被覆盖。
     *
     */
    @Test
    public void matCopy1() {
        Mat m1 = MatHelper.getFloatMat(3, 3, 2);
        Mat m2 = new Mat(5, 5, CvType.CV_32FC2, Scalar.all(10));
        Mat m3 = new Mat(3, 3, CvType.CV_32FC2, Scalar.all(10));

        //情况1（copy前后m2的dataAddr变了）
        System.out.println("Before m1.copyTo(m2), m2 = " + m2);
        m1.copyTo(m2);
        System.out.println("After m1.copyTo(m2), m2 = " + m2);
        //情况2（copy前后m3的dataAddr没变）
        System.out.println("Before m1.copyTo(m3), m3 = " + m3);
        m1.copyTo(m3);
        System.out.println("After m1.copyTo(m3), m3 = " + m3);
    }

}
