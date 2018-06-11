package com.app.nd4j;

import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.NDArrayIndex;

/***
 * 总结 sameData 方法：
 * tranpose
 * getRow
 * getColumn
 * get(INDArrayIndex... indexes)
 * reshape
 * swapAxes
 * permute
 *
 * 总结非 sameData 犯法：
 * getRows
 * getColumns
 * getScalar
 * nd4jToFlattened
 *
 */
public class _2_View extends TestBasic {

    @Test
    public void testDataChange() {
        double[][][] data = {
                {
                        {1.2, 2.2, 3.3, 4.4},
                        {5F, 6F, 7F, 8F},
                        {9, 10, 11, 12}
                },
                {
                        {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
                }
        };
        INDArray arr = Nd4j.create(data);
        System.out.println("测试修改data数组是否会影响arr。");
        data[0][0][0] = 100;
        System.out.println(arr);
        System.out.println("结果：修改data数组不会影响arr。");
    }

    @Test
    public void tranpose_getRow_getRows_getCol_getCols() {
        double[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        INDArray arr = Nd4j.create(data);
        printArrayInfos("arr", arr);

        INDArray ans = null;

        ans = arr.transpose();
        printArrayInfos("arr.transpose", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 0}, ans, new int[]{0, 0}));//true

        ans = arr.getRow(0);
        printArrayInfos("arr.getRow(0)", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 0}, ans, new int[]{0, 0}));//true

        ans = arr.getRow(1);
        printArrayInfos("arr.getRow(1)", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{1, 0}, ans, new int[]{0, 0}));//true

        ans = arr.getRows(0, 1, 2);
        printArrayInfos("arr.getRows(0, 1, 2)", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 0}, ans, new int[]{0, 0}));//false

        ans = arr.getRows(0, 2);
        printArrayInfos("arr.getRows(0, 2)", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 0}, ans, new int[]{0, 0}));//false

        ans = arr.getColumn(0);
        printArrayInfos("arr.getColumn(0)", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 0}, ans, new int[]{0, 0}));//true

        ans = arr.getColumn(1);
        printArrayInfos("arr.getColumn(1)", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 1}, ans, new int[]{0, 0}));//true

        ans = arr.getColumns(0, 1, 2);
        printArrayInfos("arr.getColumns(0, 1, 2)", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 0}, ans, new int[]{0, 0}));//false

        ans = arr.getColumns(0, 2);
        printArrayInfos("arr.getColumns(0, 2)", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 0}, ans, new int[]{0, 0}));//false

    }

    @Test
    public void get() {
        double[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        INDArray arr = Nd4j.create(data);
        printArrayInfos("arr", arr);

        INDArray ans = null;

        ans = arr.get(NDArrayIndex.point(1), NDArrayIndex.interval(1, 3));
        printArrayInfos("arr.get(NDArrayIndex.point(1),NDArrayIndex.interval(1,3))", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{1, 1}, ans, new int[]{0, 0}));//true


        ans = arr.get(NDArrayIndex.point(0), NDArrayIndex.point(0));
        printArrayInfos("arr.get(NDArrayIndex.point(0),NDArrayIndex.point(0))", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 0}, ans, new int[]{0, 0}));//true

        ans.assign(Nd4j.create(new float[]{123456}, new int[]{1, 1}));
        System.out.println(ans);
        System.out.println(arr);
        //注意，get返回的都是view。
        //对比getScalar方法，返回的同样是ndarray。
        //好像没有必要使用getScalar方法。
        //getInt，getDouble之类用于直接返回具体数字。getScalar返回的还是ndarray，同时还不能操作原始数据，好像没啥用。


    }

    @Test
    public void getScalar() {
        double[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        INDArray arr = Nd4j.create(data);
        printArrayInfos("arr", arr);

        INDArray ans = null;

        ans = arr.getScalar(0, 0);
        printArrayInfos("arr.getScalar(0,0)", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 0}, ans, new int[]{0, 0}));//false
        //getSclar的rank是2，shape为[1,1]。
    }

    @Test
    public void reshape() {
        double[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        INDArray arr = Nd4j.create(data);
        printArrayInfos("arr", arr);

        INDArray ans = null;

        ans = arr.reshape(2, 2, 3);
        printArrayInfos("arr.reshape(2,2,3)", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 0}, ans, new int[]{0, 0, 0}));//true
    }

    @Test
    public void reshape_minus_1() {
        double[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        INDArray arr = Nd4j.create(data);
        printArrayInfos("arr", arr);

        INDArray ans = null;

        ans = arr.reshape(-1);
        printArrayInfos("arr.reshape(-1)", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 0}, ans, new int[]{0}));//true
        //这种情况比较特殊，数组rank为1。
        //nd4j在创建的时候通常强制rank为2（也有部分特殊情况）。但是reshape好像不会限制rank。
    }

    @Test
    public void nd4jToFlattened() {
        double[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        INDArray arr = Nd4j.create(data);
        printArrayInfos("arr", arr);

        INDArray ans = null;

        ans = Nd4j.toFlattened(arr);
        printArrayInfos("Nd4j.toFlattened(arr)", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 0}, ans, new int[]{0}));//false
        //注意flatten之后rank是2，而不是1。
    }

    @Test
    public void swapAxes() {
        double[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        INDArray arr = Nd4j.create(data);
        printArrayInfos("arr", arr);

        INDArray ans = null;

        ans = arr.swapAxes(0, 1);
        printArrayInfos("arr.swapAxes(0, 1)", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 0}, ans, new int[]{0, 0}));//true
        //swaxes 和 permute 方法本质类似。
    }

    @Test
    public void permute() {
        double[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        INDArray arr = Nd4j.create(data);
        printArrayInfos("arr", arr);

        INDArray ans = null;

        ans = arr.permute(1, 0);
        printArrayInfos("arr.permute(1, 0)", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 0}, ans, new int[]{0, 0}));//true

        //swaxes 和 permute 方法本质类似。
        //目前来看，permute的实现是根据参数修改shape，stride信息实现。
        //对比reshape有所区别，reshape是修改shape和重新计算stride。
    }

    @Test
    public void permute2() {
        double[] data = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
        };
        INDArray arr = Nd4j.create(data, new int[]{2, 3, 4});
        printArrayInfos("arr", arr);

        INDArray ans = null;

        ans = arr.permute(2, 0, 1);
        printArrayInfos("arr.permute(2, 0, 1)", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 0, 0}, ans, new int[]{0, 0, 0}));//true
    }

    @Test
    public void template() {
        double[][] data = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        INDArray arr = Nd4j.create(data);
        printArrayInfos("arr", arr);

        INDArray ans = null;

        ans = arr.reshape(4, 3);
        printArrayInfos("ans", ans);
        System.out.println("useSameData: " + useSameData(arr, new int[]{0, 0}, ans, new int[]{0, 0}));//true
    }

}
