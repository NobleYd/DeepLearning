package com.app.opencv.mat;

import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;

/***
 * 对于数据不对的mat，可以使用clone方法复制一个mat出来，此时两个mat的数据是独立的。
 */
public class MatClone {

    @Before
    public void loadLibrary() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void matClone() {

        Mat m1 = MatHelper.getByteMat(10, 10, 1);

        System.out.println("m1.submat(new Rect(1,1,2,2).isSubmatrix=" + m1.submat(new Rect(1, 1, 2, 2)).isSubmatrix());

        System.out.println("m1.submat(new Rect(1,1,2,2).clone().isSubmatrix=" + m1.submat(new Rect(1, 1, 2, 2)).clone().isSubmatrix());

    }


}
