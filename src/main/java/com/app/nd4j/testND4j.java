package com.app.nd4j;

import com.google.common.primitives.Ints;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class testND4j {

    @Test
    public void testTranspose() {
        INDArray arr1 = Nd4j.create(new float[]{1, 2, 3, 4, 5, 6}, new int[]{2, 3}, 'c');
        System.out.println(arr1);
        arr1 = arr1.transpose();
        System.out.println(arr1);
    }

    @Test
    public void testReshape() {
        INDArray arr1 = Nd4j.create(new float[]{1, 2, 3, 4, 5, 6}, new int[]{2, 3}, 'c');
        System.out.println(arr1);
        arr1 = arr1.reshape(3, 2);
        System.out.println(arr1);
    }

    @Test
    public void testSwapAxis() {
        INDArray arr1 = Nd4j.create(new float[]{1, 2, 3, 4, 5, 6}, new int[]{2, 3}, 'c');
        System.out.println(arr1);
        arr1 = arr1.swapAxes(0, 1);
        System.out.println(arr1);

        System.out.println();

        INDArray arr2 = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{2, 3, 4}, 'c');
        System.out.println(arr2);
        arr2 = arr2.swapAxes(0, 1);
        System.out.println(arr2);
    }


    @Test
    public void testPermute1() {
        INDArray arr1 = Nd4j.create(new float[]{1, 2, 3, 4, 5, 6}, new int[]{2, 3}, 'c');
        System.out.println(arr1);
        arr1 = arr1.permute(1, 0);
        System.out.println(arr1);

        System.out.println();

        INDArray arr2 = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{2, 3, 4}, 'c');
        System.out.println(arr2);
        arr2 = arr2.permute(2, 1, 0);
        System.out.println(arr2);
    }

    @Test
    public void testPermute2() {
        INDArray arr1 = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{2, 3, 4}, 'c');
        System.out.println("arr1");
        System.out.println(arr1);

        INDArray arr2 = arr1.permute(0, 2, 1);
        System.out.println("arr2");
        System.out.println(arr2);
        System.out.println(Ints.asList(arr2.shape()));

        arr2.putScalar(0, 0, 0, 100);
        System.out.println("after putScalar");
        System.out.println(arr1);
        System.out.println(arr2);

        System.out.println(arr1.isView());
        System.out.println(arr2.isView());
        System.out.println(arr1.isAttached());
        System.out.println(arr2.isAttached());
        //(注意isView方法内部实现不是很懂，此处都是false)
        //测试表明，permute返回的也只是一个view。
        //这回导致使用permute的结果进行stack操作会出错。
    }

    @Test
    public void testIsView() {
        INDArray arr1 = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{6, 4}, 'c');
        System.out.println(arr1.isView());//false

        INDArray arr2 = arr1.getRows(0, 1, 2);
        System.out.println(arr2.isView());//false
        System.out.println(arr2.length());//12
        System.out.println("===");
        arr2 = arr1.getRow(1);
        System.out.println(arr2.isView());//true
        System.out.println(arr2.length());//4
//  这个是当前测试唯一发现可以返回true的情况。不清楚具体什么原理。

    }


    @Test
    public void testStack1() {
        INDArray arr1 = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{2, 3, 4}, 'c');
        INDArray arr2 = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{2, 3, 4}, 'c');

        System.out.println("arr1");
        System.out.println(arr1);

        System.out.println("arr2");
        System.out.println(arr2);

//        INDArray arr3 = Nd4j.hstack(arr1, arr2);
//        System.out.println("arr3");
//        System.out.println(arr3);

        INDArray arr4 = Nd4j.vstack(arr1, arr2);
        System.out.println("arr4");
        System.out.println(arr4);

        arr4.putScalar(0, 0, 0, 100);
        System.out.println("after putScalar, arr1:");
        System.out.println(arr1);
        System.out.println(arr4);
        //测试表明stack操作返回的不是view。
        System.out.println(arr4.isView());

    }

    //变相操作：如何stack3，4维。
    //先permute，再stack，再permute。
    //好像找到解决方法了，看下一个测试。
    @Test
    public void testStack2() {
        INDArray arr1 = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{2, 3, 4}, 'c');
        INDArray arr2 = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{2, 3, 4}, 'c');

        System.out.println("arr1");
        System.out.println(arr1);

        System.out.println("arr2");
        System.out.println(arr2);

        System.out.println("arr1.permute, " + arr1.permute(2, 0, 1).isView());
        System.out.println(arr1.permute(2, 0, 1));

        System.out.println("arr2.permute, " + arr2.permute(2, 0, 1).isView());
        System.out.println(arr2.permute(2, 0, 1));

        INDArray arr3 = Nd4j.vstack(arr1.permute(2, 0, 1), arr2.permute(2, 0, 1));
        INDArray arr4 = Nd4j.vstack(arr1.permute(2, 0, 1).dup(), arr2.permute(2, 0, 1).dup());

        System.out.println("arr3" + arr3.isView());
        System.out.println(arr3);

        System.out.println("arr4" + arr4.isView());
        System.out.println(arr4);

        System.out.println("arr3.permute(1,2,0)");
        System.out.println(arr3.permute(1, 2, 0));
    }


    //解决3，4维度的stack等。
    @Test
    public void testStack3() {
        INDArray arr1 = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{2, 3, 4}, 'c');
        INDArray arr2 = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{2, 3, 4}, 'c');

        System.out.println("arr1");
        System.out.println(arr1);

        System.out.println("arr2");
        System.out.println(arr2);

        INDArray arr3 = Nd4j.concat(2, arr1, arr2);
        System.out.println("arr3");
        System.out.println(arr3);
    }

    @Test
    public void testCreateWithShape() {
        INDArray arr;

        //Nd4j.create(0);//Number of columns should be positive for new INDArray
        //create(int columns) -> 指定int参数cols(>0)，创建 1 x cols 的二维数组。
        arr = Nd4j.create(1);
        System.out.println(Ints.asList(arr.shape()));//[1,1]
        //create(int columns, char order)
        arr = Nd4j.create(1, 'c');
        System.out.println(Ints.asList(arr.shape()));//[1,1]

        //Nd4j.create(0,0);//(all dimensions must be 1 or more)
        //Nd4j.create(1,0);//(all dimensions must be 1 or more)
        //Nd4j.create(0,1);//(all dimensions must be 1 or more)
        //create(int rows, int columns) -> 指定int参数rows和cols，创建 rows x cols 的二维数组。
        arr = Nd4j.create(2, 3);
        System.out.println(Ints.asList(arr.shape()));//[2,3]
        //create(int rows, int columns, char ordering)
        arr = Nd4j.create(2, 3, 'c');
        System.out.println(Ints.asList(arr.shape()));//[2,3]

        //create(int... shape) -> 指定>=3个int参数shapes，创建 shapes 形状的数组。
        arr = Nd4j.create(1, 2, 3);
        System.out.println(Ints.asList(arr.shape()));//[1,2,3]
        arr = Nd4j.create(1, 2, 3, 'c');//注意字符本身就是int，因此还是：create(int... shape)
        System.out.println(Ints.asList(arr.shape()));//[1,2,3,99]
        arr = Nd4j.create(new int[]{0});
        System.out.println(Ints.asList(arr.shape()));//[1,1]
        arr = Nd4j.create(new int[]{2});
        System.out.println(Ints.asList(arr.shape()));//[1,2]
        arr = Nd4j.create(new int[]{1, 2});
        System.out.println(Ints.asList(arr.shape()));//[1,2]
        arr = Nd4j.create(new int[]{1, 2, 3});
        System.out.println(Ints.asList(arr.shape()));//[1,2,3]
        arr = Nd4j.create(new int[]{2}, 'c');
        System.out.println(Ints.asList(arr.shape()));//[1,2]

    }


    @Test
    public void testCreateWithDataShape() {
        INDArray arr;

        //create(float[] data)
        arr = Nd4j.create(new float[]{1, 2, 3, 4, 5, 6});
        System.out.println(Ints.asList(arr.shape()));//[1,6]
        arr = Nd4j.create(new float[]{1, 2, 3, 4, 5, 6}, 'c');
        System.out.println(Ints.asList(arr.shape()));//[1,6]

        //create(float[][] data)
        arr = Nd4j.create(new float[][]{{1, 2, 3}, {4, 5, 6}});
        System.out.println(Ints.asList(arr.shape()));//[2,3]

        //create(float[] data, int[] shape)
        arr = Nd4j.create(new float[]{1, 2, 3, 4, 5, 6}, new int[]{2, 3});
        System.out.println(Ints.asList(arr.shape()));//[2,3]

        //注意shapes只给一个的情况，数字必须等于提供的数据的length。否则报错。
        arr = Nd4j.create(new float[]{1, 2, 3, 4, 5, 6}, new int[]{6});
        System.out.println(Ints.asList(arr.shape()));//[6]
        //此处比较奇怪，带了order的情况和不带order的情况不一样。
        arr = Nd4j.create(new float[]{1, 2, 3, 4, 5, 6}, new int[]{6}, 'c');
        System.out.println(Ints.asList(arr.shape()));//[1,6]

        //create(int rows, int columns)
        arr = Nd4j.create(1, 2);
        System.out.println(Ints.asList(arr.shape()));//[1,2]
    }

    @Test
    public void testGetData1d() {
        INDArray arr = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{24});
        System.out.println(Ints.asList(arr.shape()));
        //注意，测试发现就算是[1，24]情况，也可以通过单个indices访问。
        System.out.println(arr.getInt(0));
        System.out.println(arr.getInt(10));


        arr = Nd4j.create(new float[]{1, 2, 3, 4}, new int[]{1, 4});
        System.out.println(Ints.asList(arr.shape()));
        System.out.println(arr.getInt(2));

        arr.put(0, 2, 100);
        System.out.println(arr);
        //arr.put(2,0,100);//cannot get [2,0] from a [1, 4] NDArray
        //System.out.println(arr);


        arr = Nd4j.create(new float[]{1, 2, 3, 4}, new int[]{4, 1});
        System.out.println(Ints.asList(arr.shape()));
        System.out.println(arr.getInt(3));

        arr.put(0, 2, 100);
        System.out.println(arr);
        arr.put(2, 0, 100);
        System.out.println(arr);

    }

    @Test
    public void testGetData2d() {
        INDArray arr = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{6, 4}, 'c');
        System.out.println(Ints.asList(arr.shape()));
        //当非scalar和vectors的情况，只给1个indice则报错。
        //Indexes length must be > 1 for non vectors and scalars
        //System.out.println(arr.getInt(0));
        //System.out.println(arr.getInt(10));
    }

    @Test
    public void testGetData3d() {
        INDArray arr1 = Nd4j.create(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16,
                17, 18, 19, 20,
                21, 22, 23, 24
        }, new int[]{2, 3, 4}, 'c');
    }

}
