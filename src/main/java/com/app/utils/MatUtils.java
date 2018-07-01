package com.app.utils;

import com.google.common.primitives.Doubles;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatUtils {

    private MatUtils() {
    }

    private static final double scale_one = 1.0;
    private static final double scale_zero = 0.0;
    private static final double gamma_zero = 0.0;

    public static String mat2string(Mat m) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m.rows(); i++) {
            for (int j = 0; j < m.cols(); j++) {
                sb.append(Doubles.asList(m.get(i, j)));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static void print2dMat(Mat m) {
        System.out.println(mat2string(m));
    }

    /***
     * dst = mats0, mats1, ...
     * @param dst
     * @param mats
     * @return dst
     */
    public static Mat hConcatTo(Mat dst, Mat... mats) {
        Core.hconcat(new ArrayList<Mat>() {{
            addAll(Arrays.asList(mats));
        }}, dst);
        return dst;
    }

    /***
     * mats0 = mats0, mats1, ...
     * @param mats
     * @return mats[0]
     */
    public static Mat hConcat(Mat... mats) {
        return hConcatTo(mats[0], mats);
    }

    /***
     * dst = mats0,
     *       mats1,
     *       ...
     * @param dst
     * @param mats
     * @return dst
     */
    public static Mat vConcatTo(Mat dst, Mat... mats) {
        Core.vconcat(new ArrayList<Mat>() {{
            addAll(Arrays.asList(mats));
        }}, dst);
        return dst;
    }

    /***
     * mats0 = mats0,
     *         mats1,
     *         ...
     * @param mats
     * @return mats[0]
     */
    public static Mat vConcat(Mat... mats) {
        return vConcatTo(mats[0], mats);
    }

    /***
     * dst = mats0_channels, mats1_channels, ...
     * @param dst
     * @param mats
     * @return dst
     */
    public static Mat mergeTo(Mat dst, Mat... mats) {
        Core.merge(new ArrayList<Mat>() {{
            addAll(Arrays.asList(mats));
        }}, dst);
        return dst;
    }

    /***
     * mats0 = mats0_channels, mats1_channels, ...
     * @param mats
     * @return mats0
     */
    public static Mat merge(Mat... mats) {
        return mergeTo(mats[0], mats);
    }

    /***
     * splits = m.c1, m.c2, ...
     * @param m
     * @return splits
     */
    public static List<Mat> split(Mat m) {
        List<Mat> splits = new ArrayList<Mat>();
        Core.split(m, splits);
        return splits;
    }


    //四则运算
    //加法／减法 -> 加法
    //乘法／除法 -> 乘法 -> 加法

    /***
     * dst = alpha * m1 + beta * m2 + gamma
     * @param alpha
     * @param m1
     * @param beta
     * @param m2
     * @param gamma
     * @param dst
     */
    public static Mat add(double alpha, Mat m1, double beta, Mat m2, double gamma, Mat dst) {
        return add(alpha, m1, beta, m2, gamma, dst, dst.type());
    }

    public static Mat add(double alpha, Mat m1, double beta, Mat m2, double gamma, Mat dst, int dtype) {
        Core.addWeighted(m1, alpha, m2, beta, gamma, dst, dtype);
        return dst;
    }

    /***
     * m1 = alpha * m1 + beta * m2 + gamma
     * @param alpha
     * @param m1
     * @param beta
     * @param m2
     * @param gamma
     */
    public static Mat add(double alpha, Mat m1, double beta, Mat m2, double gamma) {
        return add(alpha, m1, beta, m2, gamma, m1);
    }

    /***
     * dst = m1 + m2
     * @param m1
     * @param m2
     * @param dst
     */
    public static Mat add(Mat m1, Mat m2, Mat dst) {
        return add(scale_one, m1, scale_one, m2, scale_zero, dst);
    }

    /***
     * m1 = m1 + m2
     * @param m1
     * @param m2
     */
    public static Mat add(Mat m1, Mat m2) {
        return add(m1, m2, m1);
    }

    /***
     * dst = m1 + scalar
     * @param m1
     * @param gamma
     * @param dst
     */
    public static Mat add(Mat m1, double gamma, Mat dst) {
        return add(scale_one, m1, scale_zero, m1, gamma, dst);
    }

    /***
     * m1 = m1 + scalar
     * @param m1
     * @param gamma
     */
    public static Mat add(Mat m1, double gamma) {
        return add(m1, gamma, m1);
    }


    /***
     * dst = m1 * m2
     * @param m1
     * @param m2
     * @param dst
     */
    public static Mat mul(Mat m1, Mat m2, Mat dst) {
        Core.multiply(m1, m2, dst);
        return dst;
    }

    /***
     * m1 = m1 * m2
     * @param m1
     * @param m2
     */
    public static Mat mul(Mat m1, Mat m2) {
        return mul(m1, m2, m1);
    }

    /***
     * dst = m1 * scalar
     * @param m1
     * @param scalar
     * @param dst
     */
    public static Mat mul(Mat m1, double scalar, Mat dst) {
        return add(scalar, m1, scale_zero, m1, gamma_zero, dst);
    }

    /***
     * m1 = m1 * scalar
     * @param m1
     * @param scalar
     */
    public static Mat mul(Mat m1, double scalar) {
        return mul(m1, scalar, m1);
    }

    /***
     * dst = m1 / m2
     * @param m1
     * @param m2
     * @param dst
     */
    public static Mat div(Mat m1, Mat m2, Mat dst) {
        Core.divide(m1, m2, dst);
        return dst;
    }

    /***
     * m1 = m1 / m2
     * @param m1
     * @param m2
     */
    public static Mat div(Mat m1, Mat m2) {
        return div(m1, m2, m1);
    }

    /***
     * dst = m1 / scalar
     * @param m1
     * @param scalar
     * @param dst
     */
    public static Mat div(Mat m1, double scalar, Mat dst) {
        return mul(m1, 1.0 / scalar, dst);
    }

    /***
     * m1 = m1 / scalar
     * @param m1
     * @param scalar
     */
    public static Mat div(Mat m1, double scalar) {
        return div(m1, scalar, m1);
    }

    /***
     * dst = scalar / m1
     * @param scalar
     * @param m1
     * @param dst
     */
    public static Mat div(double scalar, Mat m1, Mat dst) {
        Core.divide(scalar, m1, dst);
        return dst;
    }

    /***
     * m1 = scalar / m1
     * @param scalar
     * @param m1
     */
    public static Mat div(double scalar, Mat m1) {
        return div(scalar, m1, m1);
    }

    /***
     * dst = m1 ** pow
     * @param m1
     * @param pow
     * @param dst
     */
    public static Mat pow(Mat m1, double pow, Mat dst) {
        Core.pow(m1, pow, dst);
        return dst;
    }

    /***
     * m1 = m1 ** pow
     * @param m1
     * @param pow
     */
    public static Mat pow(Mat m1, double pow) {
        return pow(m1, pow, m1);
    }

}
