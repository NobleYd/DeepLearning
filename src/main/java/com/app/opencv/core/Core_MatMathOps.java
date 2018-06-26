package com.app.opencv.core;

import com.app.opencv.mat.MatHelper;
import com.app.utils.MatUtils;
import nu.pattern.OpenCV;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;

public class Core_MatMathOps {

    @Before
    public void loadNativeLibrary() {
        OpenCV.loadShared();
    }

}
