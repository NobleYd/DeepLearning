package com.app.nd4j;

import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.SpecifiedIndex;

import java.util.Arrays;

import static org.nd4j.linalg.indexing.NDArrayIndex.*;

public class _6_test extends TestBasic {

    @Test
    public void t1() {
        INDArray arr = Nd4j.linspace(0, 9999, 10000).reshape(new int[]{10, 10, 10, 10});
        printArrayInfos("arr", arr);

        //get(point_index): 这种情况会降1维
        printArrayInfos("1", arr.get(point(0)));//rank3 [10,10,10]
        printArrayInfos("2", arr.get(all(), point(0)));//rank3 [10,10,10]
        printArrayInfos("3", arr.get(all(), interval(0, 5), point(1), interval(5, 8)));//rank3 [10,5,3]

        printArrayInfos("1_", arr.get(Arrays.asList(Arrays.asList(0))));//[1,10,10,10]
        printArrayInfos("2_", arr.get(Arrays.asList(
                Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
                Arrays.asList(0)
        )));//[10,10,10]

        printArrayInfos("3_", arr.get(Arrays.asList(
                Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
                Arrays.asList(0, 1, 2, 3, 4),
                Arrays.asList(1),
                Arrays.asList(5, 6, 7)
        )));//[150,1,1]


        //只要带了：SpecifiedIndex，则必须给定所有维度的index。
        //不可只给定前几个维度的index，否则报错。
        printArrayInfos("4", arr.get(all(), new SpecifiedIndex(1, 3, 5, 7), all(), all()));
        //[10,4,10,10]
        printIntArray(arr.get(all(), new SpecifiedIndex(1, 3, 5, 7), all(), all()).shape());
        //[10,10,10,10]
        printIntArray(arr.get().shape());
        //[4,4]
        printIntArray(arr.get(interval(1, 5), point(1), interval(5, 9), point(2)).shape());
        //[10,10]
        printIntArray(arr.get(all(), point(1), point(2)).shape());
        //[10,10,10]
        printIntArray(arr.get(point(1)).shape());
        //每指定一个point类型index，就降低一个维度。


        //printIntArray(arr.get(interval(1, 1)).shape());//Invalid shape: Requested INDArray shape [0, 10, 10, 10] contains dimension size values < 1
        //[1,10,10,10]
        printIntArray(arr.get(interval(1, 1, true)).shape());
        //[10,1,1]
        printIntArray(arr.get(all(), interval(1, 2), point(5), interval(2, 3)).shape());
        //通过interval方式指定某个维度只有1个元素，这样不会降低维度。
        //对比python改java时候，要根据python情况决定如何使用。
        //python中，只有明确某个维度使用固定数字索引的方式，擦灰降低维度。
        //使用interval方式也不会降低维度。


        //[10,10,10]
        printIntArray(arr.get(new SpecifiedIndex(1), all(), all(), all()).shape());
        //[10,10,10,10]
        printIntArray(arr.get(all(), all(), all(), all()).shape());
        //[10,10,10]
        printIntArray(arr.get(all(), new SpecifiedIndex(2), all(), all()).shape());
        //[10,2,10,10]
        printIntArray(arr.get(all(), new SpecifiedIndex(2, 3), all(), all()).shape());

        printIntArray(Nd4j.toFlattened(arr).toIntVector());
//        printIntArray();

    }


}
