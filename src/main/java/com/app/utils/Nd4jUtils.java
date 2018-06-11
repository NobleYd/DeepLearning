package com.app.utils;

import org.nd4j.linalg.indexing.INDArrayIndex;
import org.nd4j.linalg.indexing.NDArrayIndex;
import org.nd4j.linalg.indexing.SpecifiedIndex;

/***
 * Author: hinoble@gmail.com
 */
public class Nd4jUtils {

    public static INDArrayIndex point(int point) {
        return NDArrayIndex.point(point);
    }

    public static INDArrayIndex points(int... indexes) {
        return new SpecifiedIndex(indexes);
    }

    public static INDArrayIndex points(long... indexes) {
        return new SpecifiedIndex(indexes);
    }

    public static INDArrayIndex all() {
        return NDArrayIndex.all();
    }

    public static INDArrayIndex interval(int begin, int end) {
        return NDArrayIndex.interval(begin, end, false);
    }

    public static INDArrayIndex interval(int begin, int end, boolean inclusive) {
        return NDArrayIndex.interval(begin, end, inclusive);
    }

    public static INDArrayIndex interval(int begin, int end, int max) {
        if (begin < 0)
            begin += max;
        if (end < 0)
            end += max;
        return NDArrayIndex.interval(begin, end);
    }

    public static INDArrayIndex interval(int begin, int end, int max, boolean inclusive) {
        if (begin < 0)
            begin += max;
        if (end < 0)
            end += max;
        return NDArrayIndex.interval(begin, end, inclusive);
    }

}
