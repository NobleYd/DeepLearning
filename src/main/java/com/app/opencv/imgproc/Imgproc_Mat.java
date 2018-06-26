package com.app.opencv.imgproc;

import com.app.opencv.mat.MatHelper;
import com.app.utils.MatUtils;
import nu.pattern.OpenCV;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Imgproc_Mat {

    @Before
    public void loadNativeLibrary() {
        OpenCV.loadShared();
    }


    @Test
    public void gaussianBlur1() {
        Mat m1 = MatHelper.getFloatMat(128, 128, 3);
        Mat m2 = new Mat();
        Imgproc.GaussianBlur(m1, m2, new Size(25, 25), 0, 0, Core.BORDER_REFLECT);
        MatUtils.print2dMat(m2);
    }

    //原地操作

    /***
     * 个人思考：这种操作原地进行貌似不可能，但是对比1和2的测试结果一致，可能内部实现是先计算得到结果（中间结果）。然后复制到dst中。
     */
    @Test
    public void gaussianBlur2() {
        Mat m1 = MatHelper.getFloatMat(128, 128, 3);
        Imgproc.GaussianBlur(m1, m1, new Size(25, 25), 0, 0, Core.BORDER_REFLECT);
        MatUtils.print2dMat(m1);
    }

}
