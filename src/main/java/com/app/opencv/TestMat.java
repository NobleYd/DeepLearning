package com.app.opencv;

import com.app.utils.MatUtils;
import nu.pattern.OpenCV;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestMat {

    /***
     * 一旦说：事实说明，native lib的使用，在linux下是.so文件，mac下是.dylib。
     * 并且也只需要动态库文件 + jar（提供API）。
     * 但是根据如下测试，方法1出错，因为普通的opencv好像内部对dylib的路径写死了？
     */
    @Before
    public void loadLibrary() {
        //方法1：使用libs中的jar和dylib。
        //System.load("/Users/nobleyd/IdeaProjects/DeepLearning/libs/libopencv_java341.dylib");
        //实验说明不可以，因为该dylib内部对路径什么的有点设定？具体不是很清除。

        //方法2
        //使用openpnp提供的opencv，整合了加载其包中内置的native lib。
        //OpenCV.loadLocally();
        OpenCV.loadShared();

        //方法3
        //通过查看方法2的load源码，发现就是load对应的dylib文件。因此如下方式也是可以的。
        //System.load("/Users/nobleyd/IdeaProjects/DeepLearning/libs/libopencv_java320.dylib");
    }


    @Test
    public void matBasicInfo() {
        System.out.println();

        Mat m1 = new Mat(2, 3, CvType.CV_32FC2);
        m1.put(0, 0, new float[]{
                1.1F, 1.2F, 2.1F, 2.2F, 3.1F, 3.2F,
                4.1F, 4.2F, 5.1F, 5.2F, 6.1F, 6.2F
        });

        System.out.println("depth: " + m1.depth());//5(精度，具体看CvType)
        System.out.println("elemSize: " + m1.elemSize());//8(单像素字节数)
        System.out.println("elemSize1: " + m1.elemSize1());//4(单像素单通道字节数)
        System.out.println("channels: " + m1.channels());//2
        System.out.println("total: " + m1.total());//6
        System.out.println("size: " + m1.size());//3x2
        System.out.println("dims:" + m1.dims());//2
        System.out.println("rows:" + m1.rows());//2
        System.out.println("cols:" + m1.cols());//3
        System.out.println("step1:" + m1.step1());//6(单行)
        System.out.println("type:" + m1.type());//13
        // type: CV_(位数)+(数据类型)+(通道数)
    }

    @Test
    public void matClone() {

        Mat m1 = Mat.eye(3, 3, CvType.CV_8UC3);
        System.out.println(m1);

        Mat m2 = m1.clone();
        System.out.println(m1 == m2);
        System.out.println(m1.equals(m2));

        m1.put(0, 0, 1, 2, 3);

        for (double d : m1.get(0, 0))
            System.out.println(d);

        for (double d : m2.get(0, 0))
            System.out.println(d);

    }

    @Test
    public void matResize() {
        Mat m1 = Mat.eye(3, 3, CvType.CV_8UC1);
        MatUtils.print2dMat(m1);

        Mat m2 = m1.clone();
        Imgproc.resize(m1, m2, new Size(5, 5), 1.0, 1.0, Imgproc.INTER_CUBIC);
        MatUtils.print2dMat(m2);
    }

    @Test
    public void matCopy() {
        Mat m1 = Mat.ones(3, 3, CvType.CV_8UC3);
        Mat m2 = new Mat(5, 5, CvType.CV_8UC3);
        //copyTo会完全覆盖m2（m2的shape会变）
        m1.copyTo(m2);
        MatUtils.print2dMat(m2);
    }


    @Test
    public void matGetPut() {
        Mat m1 = Mat.ones(3, 3, CvType.CV_8UC3);
        byte[] data = new byte[3 * 3 * 3];
        m1.get(0, 0, data);
        for (int i = 0; i < data.length; i++) {
            if (i % 3 == 0)
                System.out.print(", ");
            if (i % 9 == 0)
                System.out.println();
            System.out.print(data[i] + " ");
        }
        System.out.println();
        System.out.println();
        MatUtils.print2dMat(m1);


        m1.put(0, 0, 1, 2, 3);
        MatUtils.print2dMat(m1);

        m1.put(0, 0, 3, 2, 1, 6, 5, 4);
        MatUtils.print2dMat(m1);
        //从这个测试可以看出，put函数需要指定一个开始放置的位置
        //其次数据必须是channels的整数倍
        //数据具体数量不限制，以此往后放置即可。

        //在注意，如果是4维？5维？貌似目前没有考虑这些
        //opencv本身做图片处理，可能就不考虑那么多。
        //蠢了，好像就没有4维的情况。mat的构造本身就支持rows，cols。channels是隐藏的。

    }


    @Test
    public void matTypeCast() {
        Mat m1 = Mat.ones(3, 3, CvType.CV_8UC3);
        MatUtils.print2dMat(m1);
        System.out.println(m1.type());
        double[] ans1 = m1.get(0, 0);

        byte[] ans3 = new byte[3];
        float[] ans4 = new float[3];
        m1.get(0, 0, ans3);
        m1.get(0, 0, ans4);


        m1.convertTo(m1, CvType.CV_64FC1);
        MatUtils.print2dMat(m1);
        System.out.println(m1.type());
        double[] ans2 = m1.get(0, 0);

        ans3 = new byte[3];
        ans4 = new float[3];
        m1.get(0, 0, ans3);
        m1.get(0, 0, ans4);

    }


    @Test
    public void defaultMat() {
        Mat m = new Mat();
        System.out.println(m.rows());//0
        System.out.println(m.cols());//0
        System.out.println(m.channels());//1
    }

    @Test
    public void resize() {
        // 注意默认的Mat()的size0,0情况不可以resize。
        //0，0Mat可以作为dst，但作为src会报错。
        Mat m = new Mat(3, 3, CvType.CV_32FC1);
        m.put(0, 0, new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        MatUtils.print2dMat(m);

        Imgproc.resize(m, m, new Size(5, 5));
        MatUtils.print2dMat(m);
    }


    //push back
    //m1 m2 的cols()必须相等。
    @Test
    public void testPushBack() {
        Mat m1 = Mat.ones(3, 3, CvType.CV_32FC1);
        m1.put(0, 0, new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        MatUtils.print2dMat(m1);

        Mat m2 = Mat.ones(2, 4, CvType.CV_32FC1);
        m2.put(0, 0, new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        MatUtils.print2dMat(m2);

        m1.push_back(m2);
        MatUtils.print2dMat(m1);
    }


    //rows相同
    //type相同
    @Test
    public void testHconcat() {
        Mat m1 = Mat.ones(3, 3, CvType.CV_32FC1);
        m1.put(0, 0, new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        MatUtils.print2dMat(m1);

        Mat m2 = Mat.ones(3, 2, CvType.CV_32FC1);
        m2.put(0, 0, new float[]{1, 2, 3, 4, 5, 6});
        // m2.put(0, 0, new float[]{1,1, 2,2, 3,3, 4,4, 5,5, 6,6});
        MatUtils.print2dMat(m2);

        Mat m3 = new Mat();
        Core.hconcat(Arrays.asList(m1, m2), m3);
        MatUtils.print2dMat(m3);

        Core.hconcat(Arrays.asList(m1, m2), m1);
        MatUtils.print2dMat(m1);
    }


    //cols相同
    //type相同
    @Test
    public void testVconcat() {
        Mat m1 = Mat.ones(3, 3, CvType.CV_32FC1);
        m1.put(0, 0, new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        MatUtils.print2dMat(m1);

        Mat m2 = Mat.ones(2, 3, CvType.CV_32FC1);
        m2.put(0, 0, new float[]{1, 2, 3, 4, 5, 6});
        MatUtils.print2dMat(m2);

        Mat m3 = new Mat();
        Core.vconcat(new ArrayList<Mat>() {{
            add(m1);
            add(m2);
        }}, m3);
        MatUtils.print2dMat(m3);
    }

    //可以理解为channel方向上的扩张
    //size相同
    //depth相同（本质即精度相同）
    //注意：type和depth是不同的，type中包含精度depth，以及channels信息。
    @Test
    public void testMerge() {
        Mat m1 = Mat.ones(2, 3, CvType.CV_32FC1);
        m1.put(0, 0, new float[]{1, 2, 3, 4, 5, 6});
        MatUtils.print2dMat(m1);

        Mat m2 = Mat.ones(2, 3, CvType.CV_32FC2);
        m2.put(0, 0, new float[]{1, 2, 3, 4, 5, 6,
                7, 8, 9, 10, 11, 12});
        MatUtils.print2dMat(m2);

        Mat m3 = new Mat();
        Core.merge(new ArrayList<Mat>() {{
            add(m1);
            add(m2);
        }}, m3);
        MatUtils.print2dMat(m3);
    }

    //分割多通道mat为多个单通道的mat
    //注意，分割方法只能分割为多个单通道的，没办法指定具体每个mat的通道数量。
    @Test
    public void testSplit() {
        Mat m = Mat.ones(2, 3, CvType.CV_8UC4);
        m.put(0, 0, new byte[]{
                11, 12, 13, 14, 21, 22, 23, 24, 31, 32, 33, 34,
                41, 42, 43, 44, 51, 52, 53, 54, 61, 62, 63, 64
        });
        MatUtils.print2dMat(m);

        List<Mat> list = new ArrayList<Mat>();
        Core.split(m, list);//需要注意的是，此处不需要向list中提前放入Mat。

        for (Mat m0 : list) {
            MatUtils.print2dMat(m0);
        }
    }

    //重复
    //mat在x，y方向上重复。个人：很想numpy的tile函数。
    @Test
    public void testRepeat() {
        Mat m1 = Mat.ones(2, 3, CvType.CV_32FC1);
        m1.put(0, 0, new float[]{1, 2, 3, 4, 5, 6});
        MatUtils.print2dMat(m1);

        Mat m2 = new Mat();
        Core.repeat(m1, 2, 3, m2);
        MatUtils.print2dMat(m2);

    }

    @Test
    public void testReduce() {
        Mat m1 = Mat.ones(2, 3, CvType.CV_32FC1);
        m1.put(0, 0, new float[]{
                1, 2, 3,
                4, 5, 6
        });
        MatUtils.print2dMat(m1);

        Mat m2 = new Mat();

        Core.reduce(m1, m2, 0, Core.REDUCE_SUM);
        MatUtils.print2dMat(m2);//shape: 1 x 3, m2: [5,7,9]

        Core.reduce(m1, m2, 1, Core.REDUCE_SUM);
        MatUtils.print2dMat(m2);//shape: 2 x 1, m2: [6]
        //                                          [15]
    }

    @Test
    public void testConverters1() {
        Mat m1 = Mat.ones(6, 1, CvType.CV_32FC1);
        m1.put(0, 0, new float[]{
                1, 2, 3, 4, 5, 6
        });
        MatUtils.print2dMat(m1);
        List<Float> list = new ArrayList<Float>();
        Converters.Mat_to_vector_float(m1, list);
        System.out.println(list);
    }


}
