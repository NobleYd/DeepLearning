package com.app.opencv.mat;

import com.app.utils.MatUtils;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;

/***
 * push_back方法貌似和vconcat类似。
 * 要求cols相等。
 */
public class MatPushBack {

    @Before
    public void loadLibrary() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void pushBack() {
        Mat m1 = MatHelper.getByteMat(3, 3, 1);
        Mat m2 = MatHelper.getByteMat(2, 3, 1);

        System.out.println("Before, m1.dataAddr()=" + m1.dataAddr());
        m1.push_back(m2);
        System.out.println("After, m1.dataAddr()=" + m1.dataAddr());
        //dataAddr变化了。

        MatUtils.print2dMat(m1);
    }

}
