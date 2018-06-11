package com.app.nd4j;

import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class _4_sort extends TestBasic {


    //注意，sort方法是指定dimension进行排序。
    //dimension指定决定了排列的数据集合。比如是行的集合，还是列的集合，还是...
    //但具体如何对比的目前不是很清楚。
    //下面几个例子中是3维数组，指定dimension0即排序行的集合，可以每个行并不是一个数字，而是另一个2维数组。
    //具体如何对比的呢？不是很清楚。
    @Test
    public void sort1() {
        INDArray arr1 = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{2, 3, 4}, 'c');

        printArrayInfos("arr1", arr1);

        Nd4j.sort(arr1, false);
        printArrayInfos("Nd4j.sort(arr1, false)", arr1);

        arr1 = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{2, 3, 4}, 'c');

        Nd4j.sort(arr1, 0, false);
        printArrayInfos("Nd4j.sort(arr1,0,false)", arr1);

        arr1 = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{2, 3, 4}, 'c');

        Nd4j.sort(arr1, 1, false);
        printArrayInfos("Nd4j.sort(arr1,1,false)", arr1);

    }


    //注意sortRows和sortCols方法不同于sort方法。
    //是针对二维数组的情况，根据指定列排序行，或者根据指定行排序列。
    //其次，sortRows和sortCols方法都是copy操作。
    //而sort方法是原地排序。
    @Test
    public void sortRowsCols() {

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

        //根据第二列排序rows
        ans = Nd4j.sortRows(arr, 2, false);
        printArrayInfos("Nd4j.sortRows(arr,2,false)", ans);

        //根据第二行排序cols
        ans = Nd4j.sortColumns(arr, 2, false);
        printArrayInfos("Nd4j.sortColumns(arr,2,false)", ans);

    }
}
