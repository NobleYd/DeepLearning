package com.app.nd4j;

import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

//rank
//shape
//length
//stride
//data type
public class _0_BasicInfo extends TestBasic {

    @Test
    public void basicInfo() {
        INDArray arr = Nd4j.create(new double[][][]{
                {
                        {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
                },
                {
                        {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
                }
        });
        printArrayInfos(null, arr);
        System.out.println(arr.rank());//3
        printIntArray(arr.shape());//[2, 3, 4]
        System.out.println(arr.length());//24
        printIntArray(arr.stride());//[12,4,1]
        System.out.println(Nd4j.dataType());//DOUBLE
        //注意，ND4j的数据类型是全局唯一的。
    }


}
