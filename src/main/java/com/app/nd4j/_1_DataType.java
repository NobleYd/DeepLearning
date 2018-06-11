package com.app.nd4j;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class _1_DataType extends TestBasic {

    /***
     * getInt
     * getFloat
     * getDouble
     * 使用方式一致，返回数据类型不同。
     */
    @Test
    public void test() {
        INDArray arr = Nd4j.create(new double[][][]{
                {
                        {1.2, 2.2, 3.3, 4.4},
                        {5F, 6F, 7F, 8F},
                        {9, 10, 11, 12}
                },
                {
                        {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
                }
        });
        System.out.println("getInt");
        System.out.println(arr.getInt(0, 0, 0));//1
        System.out.println(arr.getInt(1, 0, 0));//1

        System.out.println("getFloat");
        System.out.println(arr.getFloat(new int[]{1, 0, 0}));//1.0
        System.out.println(arr.getFloat(new int[]{0, 0, 0}));//1.2
        System.out.println(arr.getFloat(new int[]{0, 1, 0}));//5.0

        System.out.println("getDouble");
        System.out.println(arr.getDouble(1, 0, 0));//1.0
        System.out.println(arr.getDouble(0, 0, 0));//1.2
        System.out.println(arr.getDouble(0, 1, 0));//5.0
    }


    /***
     * nd4j中不存在每个arr的特定数据类型。
     * 整个Nd4j的类型是统一的，通过Nd4j的静态方法设置和获取。
     *
     * 在构造NdArray的时候可以使用各种类型数据构造（不报错）。
     *
     * 在将NdArray转换为array时候也可以转换为各种类型（不报错）。
     *
     */
    @Test
    public void testToArrayTypes() {

        INDArray arr = Nd4j.create(new float[]{1, 2, 3, 4, 5, 6}, new int[]{2, 3});
        System.out.println(Nd4j.dataType());//double(TestBasic中设置的.)

        System.out.println(Floats.asList(Nd4j.toFlattened(arr).toFloatVector()));
        System.out.println(Doubles.asList(Nd4j.toFlattened(arr).toDoubleVector()));
    }

}
