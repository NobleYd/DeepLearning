package com.app.opencv;

import com.app.utils.MatUtils;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.*;

import java.util.Arrays;

public class TestMatSlice {

    @Before
    public void loadLibrary() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    @Test
    public void testSubmat1() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC1);
        float[] data = new float[25];
        Arrays.fill(data, 25F);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);

        Mat m2 = m1.submat(new Rect(1, 1, 3, 3));
        MatUtils.print2dMat(m2);

        System.out.println(m1.isSubmatrix());//false
        System.out.println(m2.isSubmatrix());//true

        //修改m2(影响m1)
        m2.put(0, 0, 1.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);

        //修改m3(不影响m1，也不影响m2)
        Mat m3 = m2.clone();
        m3.put(1, 1, 2.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);
        MatUtils.print2dMat(m3);

        System.out.println(m2.isSubmatrix());//true
    }


    @Test
    public void testSubmat2() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC1);
        float[] data = new float[25];
        Arrays.fill(data, 25F);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);
        //注意Range包含start，不包含end。
        Mat m2 = m1.submat(new Range(1, 4), new Range(1, 4));
        MatUtils.print2dMat(m2);

        System.out.println(m1.isSubmatrix());//false
        System.out.println(m2.isSubmatrix());//true

        //修改m2(不影响m1)
        m2.put(0, 0, 1.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);

        //修改m3(不影响m1，也不影响m2)
        Mat m3 = m2.clone();
        m3.put(1, 1, 2.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);
        MatUtils.print2dMat(m3);
    }


    @Test
    public void testSubmat3() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC1);
        float[] data = new float[25];
        Arrays.fill(data, 25F);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);
        //注意包含start，不包含end。
        Mat m2 = m1.submat(1, 4, 1, 4);
        MatUtils.print2dMat(m2);

        System.out.println(m1.isSubmatrix());//false
        System.out.println(m2.isSubmatrix());//true

        //修改m2(不影响m1)
        m2.put(0, 0, 1.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);

        //修改m3(不影响m1，也不影响m2)
        Mat m3 = m2.clone();
        m3.put(1, 1, 2.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);
        MatUtils.print2dMat(m3);
    }


    @Test
    public void testRowRange1() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC1);
        float[] data = new float[25];
        Arrays.fill(data, 25F);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);
        //注意包含start，不包含end。
        Mat m2 = m1.rowRange(new Range(1, 4));
        MatUtils.print2dMat(m2);

        System.out.println(m1.isSubmatrix());//false
        System.out.println(m2.isSubmatrix());//true

        //修改m2(不影响m1)
        m2.put(0, 0, 1.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);

        //修改m3(不影响m1，也不影响m2)
        Mat m3 = m2.clone();
        m3.put(1, 1, 2.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);
        MatUtils.print2dMat(m3);
    }

    @Test
    public void testRowRange2() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC1);
        float[] data = new float[25];
        Arrays.fill(data, 25F);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);
        //注意包含start，不包含end。
        Mat m2 = m1.rowRange(1, 4);
        MatUtils.print2dMat(m2);

        System.out.println(m1.isSubmatrix());//false
        System.out.println(m2.isSubmatrix());//true

        //修改m2(不影响m1)
        m2.put(0, 0, 1.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);

        //修改m3(不影响m1，也不影响m2)
        Mat m3 = m2.clone();
        m3.put(1, 1, 2.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);
        MatUtils.print2dMat(m3);
    }

    @Test
    public void testColRange1() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC1);
        float[] data = new float[25];
        Arrays.fill(data, 25F);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);
        //注意包含start，不包含end。
        Mat m2 = m1.colRange(new Range(1, 4));
        MatUtils.print2dMat(m2);

        System.out.println(m1.isSubmatrix());//false
        System.out.println(m2.isSubmatrix());//true

        //修改m2(不影响m1)
        m2.put(0, 0, 1.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);

        //修改m3(不影响m1，也不影响m2)
        Mat m3 = m2.clone();
        m3.put(1, 1, 2.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);
        MatUtils.print2dMat(m3);
    }

    @Test
    public void testColRange2() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC1);
        float[] data = new float[25];
        Arrays.fill(data, 25F);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);
        //注意包含start，不包含end。
        Mat m2 = m1.colRange(1, 4);
        MatUtils.print2dMat(m2);

        System.out.println(m1.isSubmatrix());//false
        System.out.println(m2.isSubmatrix());//true

        //修改m2(不影响m1)
        m2.put(0, 0, 1.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);

        //修改m3(不影响m1，也不影响m2)
        Mat m3 = m2.clone();
        m3.put(1, 1, 2.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);
        MatUtils.print2dMat(m3);
    }

    @Test
    public void testCol() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC1);
        float[] data = new float[25];
        Arrays.fill(data, 25F);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);
        //注意包含start，不包含end。
        Mat m2 = m1.col(1);
        MatUtils.print2dMat(m2);

        System.out.println(m1.isSubmatrix());//false
        System.out.println(m2.isSubmatrix());//true

        //修改m2(不影响m1)
        m2.put(0, 0, 1.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);

        //修改m3(不影响m1，也不影响m2)
        Mat m3 = m2.clone();
        m3.put(1, 0, 2.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);
        MatUtils.print2dMat(m3);
    }

    @Test
    public void testRow() {
        Mat m1 = new Mat(5, 5, CvType.CV_32FC1);
        float[] data = new float[25];
        Arrays.fill(data, 25F);
        m1.put(0, 0, data);
        MatUtils.print2dMat(m1);
        //注意包含start，不包含end。
        Mat m2 = m1.row(1);
        MatUtils.print2dMat(m2);

        System.out.println(m1.isSubmatrix());//false
        System.out.println(m2.isSubmatrix());//true

        //修改m2(不影响m1)
        m2.put(0, 0, 1.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);

        //修改m3(不影响m1，也不影响m2)
        Mat m3 = m2.clone();
        m3.put(0, 1, 2.0);

        MatUtils.print2dMat(m1);
        MatUtils.print2dMat(m2);
        MatUtils.print2dMat(m3);
    }

}
