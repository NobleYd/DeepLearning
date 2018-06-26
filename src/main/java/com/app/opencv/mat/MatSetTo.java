package com.app.opencv.mat;

import com.app.utils.MatUtils;
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
    public void loadLibrary() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
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

}
