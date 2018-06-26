package com.app.opencv.core;

import com.app.opencv.mat.MatHelper;
import com.app.utils.MatUtils;
import nu.pattern.OpenCV;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;

/***
 * Reduce
 *
 *
 */
public class Core_MatReduce {

    @Before
    public void loadNativeLibrary(){
        OpenCV.loadShared();
    }

    @Test
    public void reduceSum() {
        Mat m1 = MatHelper.getFloatMat(3, 3, 2);
        MatUtils.print2dMat(m1);

        Mat m2 = new Mat();
        Core.reduce(m1, m2, 0, Core.REDUCE_SUM);
        MatUtils.print2dMat(m2);
    }

    @Test
    public void reduceAvg() {
        Mat m1 = MatHelper.getFloatMat(3, 3, 2);
        MatUtils.print2dMat(m1);

        Mat m2 = new Mat();
        Core.reduce(m1, m2, 0, Core.REDUCE_AVG);
        MatUtils.print2dMat(m2);
    }

    @Test
    public void reduceMax() {
        Mat m1 = MatHelper.getFloatMat(3, 3, 2);
        MatUtils.print2dMat(m1);

        Mat m2 = new Mat();
        Core.reduce(m1, m2, 0, Core.REDUCE_MAX);
        MatUtils.print2dMat(m2);
    }

    @Test
    public void reduceMin() {
        Mat m1 = MatHelper.getFloatMat(3, 3, 2);
        MatUtils.print2dMat(m1);

        Mat m2 = new Mat();
        Core.reduce(m1, m2, 0, Core.REDUCE_MIN);
        MatUtils.print2dMat(m2);
    }

}
