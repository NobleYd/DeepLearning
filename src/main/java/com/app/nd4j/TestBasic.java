package com.app.nd4j;

import com.google.common.primitives.Ints;
import org.junit.Before;
import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class TestBasic {
    @Before
    public void init() {
        Nd4j.setDataType(DataBuffer.Type.DOUBLE);
        System.out.println("inti nd4j data type as double." + System.lineSeparator());
    }

    public void printIntArray(int[] array) {
        System.out.println(Ints.asList(array));
    }

    public void printArrayInfos(String title, INDArray ans) {
        if (title != null)
            System.out.println(System.lineSeparator() + title + ":");
        System.out.println("\t" + "ans.rank: " + ans.rank());
        System.out.println("\t" + "ans.shape: " + Ints.asList(ans.shape()));
        System.out.println("\t" + "ans.offset: " + ans.offset());
        System.out.println("\t" + "ans.length: " + ans.length());
        System.out.println("\t" + "ans.stride: " + Ints.asList(ans.stride()));
        System.out.println("\t" + "isView: " + ans.isView());
        System.out.println("\t" + "ans: " + System.lineSeparator() + ans);
    }

    //返回arr1和arr2是否相同数据。
    public boolean useSameData(INDArray arr1, int[] indices1, INDArray arr2, int[] indices2) {
        //保存原始value
        double tmp = arr1.getDouble(indices1);

        int tmp1 = 12345678;
        arr1.putScalar(indices1, tmp1);
        int tmp2 = arr2.getInt(indices2);
        boolean ans = (tmp1 == tmp2);

        //还原数据
        arr1.putScalar(indices1, tmp);

        //返回
        return ans;
    }

}
