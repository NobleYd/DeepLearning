package com.app.opencv.core;

import com.app.opencv.mat.MatHelper;
import com.app.utils.MatUtils;
import nu.pattern.OpenCV;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.List;

/***
 * MatSlice实现了切片。
 *
 * Core:
 *
 * Mat的concat功能类似于numpy的stack功能。
 *
 * 包含 vconcat 和 hconcat 方法。
 *
 * 此外，在channedls维度上支持 merge 以及 split 方法。
 *
 */
public class Core_MatConcatMergeSplit {

    @Before
    public void loadNativeLibrary(){
        OpenCV.loadShared();
    }


    //vConcat要求rows相等。
    @Test
    public void hConcat() {
        List<Mat> mats = new ArrayList<>();
        Mat m1 = MatHelper.getByteMat(3, 2, 2);
        Mat m2 = MatHelper.getByteMat(3, 1, 2);
        mats.add(m1);
        mats.add(m2);

        Mat m3 = new Mat();
        Core.hconcat(mats, m3);

        MatUtils.print2dMat(m3);
    }

    //vConcat要求cols相等。
    @Test
    public void vConcat() {
        List<Mat> mats = new ArrayList<>();
        Mat m1 = MatHelper.getByteMat(2, 3, 2);
        Mat m2 = MatHelper.getByteMat(1, 3, 2);
        mats.add(m1);
        mats.add(m2);

        Mat m3 = new Mat();
        Core.vconcat(mats, m3);

        MatUtils.print2dMat(m3);
    }

    @Test
    public void merge() {
        List<Mat> channels = new ArrayList<>();
        Mat m1 = MatHelper.getFloatMat(3, 3, 3);
        Mat m2 = MatHelper.getFloatMat(3, 3, 3);
        channels.add(m1);
        channels.add(m2);

        Mat m3 = new Mat();
        Core.merge(channels, m3);

        MatUtils.print2dMat(m3);
    }

    @Test
    public void split() {
        Mat m1 = MatHelper.getFloatMat(3, 3, 3);
        List<Mat> channels = new ArrayList<>();
        Core.split(m1, channels);
        for (Mat channel : channels) {
            System.out.println(channel);
            MatUtils.print2dMat(channel);
        }
    }


}
