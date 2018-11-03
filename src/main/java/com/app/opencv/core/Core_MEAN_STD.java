package com.app.opencv.core;

import com.app.opencv.mat.MatHelper;
import com.app.utils.MatUtils;
import nu.pattern.OpenCV;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.*;

public class Core_MEAN_STD {

    @Before
    public void loadNativeLibrary() {
        OpenCV.loadShared();
    }

    @Test
    public void mean() {
        Mat m1 = MatHelper.getFloatMat(3, 3, 2);
        MatUtils.print2dMat(m1);

        Scalar mean = Core.mean(m1);
        System.out.println(mean);
    }

    //注意此方法最多支持4通道。
    //多余4通道计算返回是错误的。
    @Test
    public void meanstd() {
        Mat m1 = MatHelper.getFloatMat(3, 3, 4);
        MatUtils.print2dMat(m1);

        MatOfDouble mean = new MatOfDouble();
        MatOfDouble std = new MatOfDouble();
        Core.meanStdDev(m1, mean, std);

        MatUtils.print2dMat(mean);
        MatUtils.print2dMat(std);
    }

    @Test
    public void meanstd2() {
        Mat m1 = new Mat(3,3,CvType.CV_32FC4,Scalar.all(5));
        MatUtils.print2dMat(m1);

        MatOfDouble mean = new MatOfDouble();
        MatOfDouble std = new MatOfDouble();
        Core.meanStdDev(m1, mean, std);

        MatUtils.print2dMat(mean);
        MatUtils.print2dMat(std);
    }

}
