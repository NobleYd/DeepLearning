package com.app.opencv.mat;

import com.app.utils.MatUtils;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/***
 * Reduce
 *
 *
 */
public class Core_MatReduce {
    @Before
    public void loadLibrary() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
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
