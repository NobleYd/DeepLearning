package com.app.opencv.mat;

import com.app.utils.MatUtils;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/***
 * 类似numpy的tile函数。
 */
public class Core_MatRepeat {
    @Before
    public void loadLibrary() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    //mat在x，y方向上重复。
    @Test
    public void repeat() {
        Mat m1 = MatHelper.getByteMat(2, 3, 1);

        Mat m2 = new Mat();
        Core.repeat(m1, 2, 3, m2);

        MatUtils.print2dMat(m2);
    }

}
