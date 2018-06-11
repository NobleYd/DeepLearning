package com.app.nd4j;

import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.NDArrayIndex;

/***
 * 在nd4j中，1D array是比较特殊的。
 *
 * 很多create方法会将只有一个shape的情况强制转换为row vector。
 *
 * 目前只有少数create方法，以及reshape(-1)或者reshape(length)方法可以得到rank为1的array。
 *
 */
public class _3_1D_Array extends TestBasic {

    @Test
    public void _1DArray() {
        INDArray arr = Nd4j.create(new float[]{1, 2, 3, 4}, new int[]{4});
        printArrayInfos("arr", arr);//rank: 1, shape: [4]

        //给定scalar，shape设定为1
        arr = Nd4j.create(new float[]{5}, new int[]{1});
        printArrayInfos("arr", arr);//rank: 1, shape: [1]

        //给定scalar，不设置shape，默认是2d。
        arr = Nd4j.create(new float[]{5});
        printArrayInfos("arr", arr);//rank: 2, shape: [1,1]

        //true scalar(当给定scalar，shape设置为空数组时)
        arr = Nd4j.create(new float[]{5}, new int[]{});
        printArrayInfos("arr", arr);//rank: 0, shape: []

        arr = Nd4j.create(new int[]{5});
        printArrayInfos("arr", arr);//rank: 2, shape: [1,5]

        arr = Nd4j.create(new int[]{0});
        printArrayInfos("arr", arr);//rank: 2, shape: [1,1]

    }


    //数据获取，rank=0
    @Test
    public void rank0() {
        INDArray arr;
        arr = Nd4j.create(new float[]{5}, new int[]{});
        printArrayInfos("arr", arr);//rank: 0, shape: []

        System.out.println(arr.getScalar(0));
        printArrayInfos("arr.getScalar(0)", arr.getScalar(0));//rank2, [1,1]
        //System.out.println(arr.getScalar(0,0));//error
        //System.out.println(arr.getScalar(new int[]{0,0}));//error
        System.out.println(arr.getDouble(0));
        //System.out.println(arr.getDouble(0,0));//error
        //System.out.println(arr.getDouble(new int[]{0, 0}));///error

        //总结：true scalar（rank0）可以通过指定一个indice获取数据。
    }

    @Test
    public void rank1() {
        INDArray arr = Nd4j.create(new float[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{10});
        printArrayInfos("arr", arr);

        System.out.println(arr.getScalar(5));
        System.out.println(arr.getDouble(5));

        //System.out.println(arr.getScalar(0,5));//error
        //System.out.println(arr.getDouble(0,5));//error

        //总结：使用getDouble，getFloat，getInt即可。
        //如果是为了局部赋值，使用get方法即可，不使用getScalar，getScalar返回的ndarray是不共享数据的。

    }

    @Test
    public void rank2() {
        INDArray arr = Nd4j.create(new float[]{
                0, 1, 2, 3, 4,
                5, 6, 7, 8, 9,
                10, 11, 12, 13, 14,
                15, 16, 17, 18, 19,
                20, 21, 22, 23, 24
        }, new int[]{5, 5});
        printArrayInfos("arr", arr);

        System.out.println(arr.getScalar(1, 1));
        System.out.println(arr.getDouble(1, 1));

        printArrayInfos("row(0)", arr.getRow(0));
        printArrayInfos("rows(0)", arr.getRows(0));
        printArrayInfos("col(0)", arr.getColumn(0));
        printArrayInfos("cols(0)", arr.getColumns(0));

    }

    @Test
    public void rank2C_row1() {
        INDArray arr = Nd4j.create(new float[]{
                0, 1, 2, 3, 4,
                5, 6, 7, 8, 9,
                10, 11, 12, 13, 14,
                15, 16, 17, 18, 19,
                20, 21, 22, 23, 24
        }, new int[]{5, 5});
        printArrayInfos("arr", arr);

        INDArray row0 = arr.getRow(0).dup();
        printArrayInfos("row0", row0);

        System.out.println(row0.getScalar(3));
        System.out.println(row0.getScalar(0, 2));
        System.out.println(row0.getDouble(2));
        System.out.println(row0.getDouble(0, 2));

        INDArray rows0 = arr.getRows(0).dup();
        System.out.println(rows0.getScalar(3));
        System.out.println(rows0.getScalar(0, 2));
        System.out.println(rows0.getDouble(2));
        System.out.println(rows0.getDouble(0, 2));


    }

    @Test
    public void rank2C_row2() {
        INDArray arr = Nd4j.create(new float[]{
                0, 1, 2, 3, 4,
                5, 6, 7, 8, 9,
                10, 11, 12, 13, 14,
                15, 16, 17, 18, 19,
                20, 21, 22, 23, 24
        }, new int[]{5, 5});
        printArrayInfos("arr", arr);

        INDArray row0 = arr.get(NDArrayIndex.point(1));//
        printArrayInfos("row0", row0);//[1,5]
        printArrayInfos("row0[2]", row0.get(NDArrayIndex.point(2)));//rank2,[1,1]
        System.out.println(row0.get(NDArrayIndex.point(2)));//7
        //注意这个测试说明，get方法也类似于getDouble等方法，在row0为vector情况下，将唯一的indice当作了列坐标。
        //这样有个好处，就是我们不必过于纠结返回的vector是rank1还是rank2。
        //只需要在获取数据时候统一只给定一个indice即可。不论是rank2还是rank1都可以正确获取数据。
        //只要没出现判断错误，比如不是vector类型，结果我们给定了一个indice。
        //但是那种情况会直接报错的。

        System.out.println(row0.getScalar(3));
        System.out.println(row0.getScalar(0, 2));
        System.out.println(row0.getDouble(2));
        System.out.println(row0.getDouble(0, 2));
    }

    @Test
    public void rank2_col1() {
        INDArray arr = Nd4j.create(new float[]{
                0, 1, 2, 3, 4,
                5, 6, 7, 8, 9,
                10, 11, 12, 13, 14,
                15, 16, 17, 18, 19,
                20, 21, 22, 23, 24
        }, new int[]{5, 5});
        printArrayInfos("arr", arr);


        INDArray col0 = arr.getColumn(0).dup();
        INDArray cols0 = arr.getColumns(0).dup();

        System.out.println(col0.getScalar(2));
        System.out.println(col0.getScalar(2, 0));
        System.out.println(col0.getDouble(4));
        System.out.println(col0.getDouble(0, 4));//0
        System.out.println(col0.getDouble(4, 0));//20
    }


    @Test
    public void zeroRowNcols() {
        INDArray arr = Nd4j.create(new float[]{}, new int[]{0, 5});//error
        printArrayInfos("arr", arr);


    }

}
