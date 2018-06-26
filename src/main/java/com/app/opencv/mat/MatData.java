package com.app.opencv.mat;

import com.app.utils.MatUtils;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import nu.pattern.OpenCV;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;

/***
 * Mat数据的操作。
 *
 * 需要注意，不论是put还是get，data数组的类型和mat的数据类型必须严格一致，否则报错。
 *
 * 此外，data的大小必须是channels的整数倍。
 *
 */
public class MatData {

    @Before
    public void loadNativeLibrary(){
        OpenCV.loadShared();
    }

    /***
     * mat.put(i,j,data[])
     * 从mat[i,j]开始放data数据。
     */
    @Test
    public void matPut() {
        Mat m1 = MatHelper.getFloatMat(3, 3, 2);
        MatUtils.print2dMat(m1);
        m1.put(1, 1, new float[]{1, 2, 3, 4});
        MatUtils.print2dMat(m1);
    }

    /***
     * mat.get(i,j,data[]):从mat[i,j]开始取数据到data。
     * mat.get(i,j):从mat[i,j]开始取数据并返回，数据个数为channels。
     */
    @Test
    public void matGet() {
        Mat m1 = MatHelper.getFloatMat(3, 3, 2);
        MatUtils.print2dMat(m1);
        float[] data = new float[4];
        m1.get(1, 1, data);
        System.out.println(Floats.asList(data));
        double[] data2 = m1.get(1, 1);
        System.out.println(Doubles.asList(data2));
    }

}
