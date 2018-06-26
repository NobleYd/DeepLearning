package com.app.opencv.core;

import com.app.opencv.mat.MatHelper;
import com.app.utils.MatUtils;
import nu.pattern.OpenCV;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Range;

/***
 * Mat之间的比较。
 *
 * 注意：2个Mat的比较，要求其size和type一致。也就是shape必须一致。
 *
 * 对比结果为整数，255表示true，0表示false。
 *
 */

public class Core_MatCompare {


    @Before
    public void loadNativeLibrary() {
        OpenCV.loadShared();
    }

    @Test
    public void compare1() {

        Mat m1 = MatHelper.getByteMat(3, 3, 3);
        Mat m2 = MatHelper.getByteMat(3, 3, 3);

        Mat m3 = new Mat();
        Core.compare(m1, m2, m3, Core.CMP_EQ);
        MatUtils.print2dMat(m3);//all 255

        Core.compare(m1, m2, m3, Core.CMP_NE);
        MatUtils.print2dMat(m3);//all 0

    }

    @Test
    public void compare2() {

        Mat m1 = new Mat(5, 5, CvType.CV_8UC3);
        Mat m2 = new Mat(3, 3, CvType.CV_8UC3);
        //随机数据。

        Mat m3 = new Mat();
        Core.compare(m1.submat(new Range(1, 4), new Range(1, 4)), m2, m3, Core.CMP_GT);
        MatUtils.print2dMat(m3);

    }

}
