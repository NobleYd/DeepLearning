package com.app.opencv.mat;

import com.app.utils.MatUtils;
import nu.pattern.OpenCV;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

/***
 * 不推荐使用。
 */
public class MatAssignTo {

    @Before
    public void loadNativeLibrary(){
        OpenCV.loadShared();
    }


}
