package com.app.opencv.mat;

import nu.pattern.OpenCV;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;

public class defaultMat {

    @Before
    public void loadNativeLibrary(){
        OpenCV.loadShared();
    }

    @Test
    public void defaultMat() {
        Mat m = new Mat();
        System.out.println(m.rows());//0
        System.out.println(m.cols());//0
        System.out.println(m.channels());//1
    }

}
