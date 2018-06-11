package com.app.nd4j;

import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.NDArrayIndex;
import org.nd4j.linalg.indexing.SpecifiedIndex;

import java.util.Arrays;

public class _5_sub_array extends TestBasic {

    @Test
    public void test1() {
        INDArray arr = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{6, 4});
        printArrayInfos("arr", arr);

        INDArray ans;

        //INDArray get(INDArrayIndex... indexes);
        System.out.println(arr.get(NDArrayIndex.point(2), NDArrayIndex.point(2)));

        //INDArray get(INDArray indices); //Indices must be a vector or matrix.
        //vector: 对行进行抽取
        ans = arr.get(Nd4j.create(new float[]{1, 2, 3}));
        printArrayInfos("arr.get(Nd4j.create(new float[]{1,2,3}))", ans);
        //matrix，indices[0]对应了dimension0，indices[1]对应dimension1
        ans = arr.get(Nd4j.create(new float[][]{
                {1, 2},
                {3, 3}
        }));
        printArrayInfos("arr.get(Nd4j.create(new float[][]{{1,2},{3,3}}))", ans);

        //INDArray get(List<List<Integer>> indices);
        ans = arr.get(Arrays.asList(
                Arrays.asList(1, 2, 3)
        ));
        printArrayInfos("ans", ans);

        ans = arr.get(Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(2, 3)
        ));
        printArrayInfos("ans", ans);
    }


    @Test
    public void newAxes() {
        INDArray arr = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{6, 4});
        printArrayInfos("arr", arr);


        NDArrayIndex.empty();
        NDArrayIndex.point(0);
        NDArrayIndex.interval(0, 1);
        NDArrayIndex.all();
        NDArrayIndex.newAxis();
        new SpecifiedIndex(1, 2);

        INDArray ans;

        ans = arr.get(NDArrayIndex.empty());
        printArrayInfos("ans", ans);


        ans = arr.get(NDArrayIndex.all(), NDArrayIndex.newAxis(), NDArrayIndex.all());
        printArrayInfos("ans", ans);
    }


    @Test
    public void test2() {
        INDArray arr = Nd4j.linspace(0, 124, 125).reshape(new int[]{5, 5, 5});
        printArrayInfos("arr", arr);
        INDArray ans;

        //
        ans = arr.get(Nd4j.create(new double[][]{
                {0, 2, 1},
                {0, 1, 2},
                {0, 1, 1}
        }));
        printArrayInfos("ans", ans);//[1,3]，分别对应(0,0,0),(2,1,1),(1,2,1)

        ans = arr.get(Nd4j.create(new double[][]{
                {0, 2, 1},
                {0, 1, 2}
        }));
        printArrayInfos("ans", ans);//[3,1,5]
        //（注意，当获取到的不是数字，而是array的情况，并且这个array是一维）
        // 返回的shape多个1，即1darray是当作2d考虑的。

        ans = arr.get(Nd4j.create(new double[][]{
                {0, 2, 1}
        }));
        printArrayInfos("ans", ans);//[3,5,5]

    }
}
