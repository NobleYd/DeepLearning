package com.app.opencv.mat;

import com.app.utils.MatUtils;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.*;

/***
 * Mat的切片操作，包含如下方法列表：
 *
 * m1.submat(Rect roi);
 * m1.submat(Range rowRange, Range colRange);
 * m1.submat(int rowStart, int rowEnd, int colStart, int colEnd);
 *
 * m1.rowRange(Range r);
 * m1.rowRange(int startrow, int endrow);
 *
 * m1.colRange(Range r);
 * m1.colRange(int startcol, int endcol);
 *
 * m1.col(int x);
 * m1.row(int y);
 *
 * 总结：包含start，不包含end。
 *
 */
public class MatSlice {
    @Before
    public void loadLibrary() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void slice() {
        Mat m1 = MatHelper.getByteMat(10, 10, 1);

        System.out.println("m1.submat(new Rect(1,1,2,2)=");
        MatUtils.print2dMat(m1.submat(new Rect(1, 1, 2, 2)));

        System.out.println("m1.submat(new Rect(new Point(1, 1), new Point(3, 3)))=");
        MatUtils.print2dMat(m1.submat(new Rect(new Point(1, 1), new Point(3, 3))));

        System.out.println("m1.submat(new Rect(new Point(1, 1), new Size(2, 2)))=");
        MatUtils.print2dMat(m1.submat(new Rect(new Point(1, 1), new Size(2, 2))));

        System.out.println("m1.submat(new Range(1,3),new Range(1,3))=");
        MatUtils.print2dMat(m1.submat(new Range(1, 3), new Range(1, 3)));

        System.out.println("m1.submat(1, 3, 1, 3)=");
        MatUtils.print2dMat(m1.submat(1, 3, 1, 3));

        System.out.println("m1.rowRange(new Range(1, 3))=");
        MatUtils.print2dMat(m1.rowRange(new Range(1, 3)));

        System.out.println("m1.rowRange(1, 3)=");
        MatUtils.print2dMat(m1.rowRange(1, 3));

        System.out.println("m1.colRange(new Range(1, 3))=");
        MatUtils.print2dMat(m1.colRange(new Range(1, 3)));

        System.out.println("m1.colRange(1, 3)=");
        MatUtils.print2dMat(m1.colRange(1, 3));

        System.out.println("m1.row(1)=");
        MatUtils.print2dMat(m1.row(1));

        System.out.println("m1.col(1)=");
        MatUtils.print2dMat(m1.col(1));
    }

    /**
     * 全部输出为true(注意submatrix数据不独立)。
     */
    @Test
    public void isSubmatrix() {
        Mat m1 = MatHelper.getByteMat(10, 10, 1);

        System.out.println("m1.submat(new Rect(1,1,2,2).isSubmatrix=" + m1.submat(new Rect(1, 1, 2, 2)).isSubmatrix());

        System.out.println("m1.submat(new Rect(new Point(1, 1), new Point(3, 3))).isSubmatrix=" + m1.submat(new Rect(new Point(1, 1), new Point(3, 3))).isSubmatrix());

        System.out.println("m1.submat(new Rect(new Point(1, 1), new Size(2, 2))).isSubmatrix=" + m1.submat(new Rect(new Point(1, 1), new Size(2, 2))).isSubmatrix());

        System.out.println("m1.submat(new Range(1,3),new Range(1,3)).isSubmatrix=" + m1.submat(new Range(1, 3), new Range(1, 3)).isSubmatrix());

        System.out.println("m1.submat(1, 3, 1, 3).isSubmatrix=" + m1.submat(1, 3, 1, 3).isSubmatrix());

        System.out.println("m1.rowRange(new Range(1, 3)).isSubmatrix=" + m1.rowRange(new Range(1, 3)).isSubmatrix());

        System.out.println("m1.rowRange(1, 3).isSubmatrix=" + m1.rowRange(1, 3).isSubmatrix());

        System.out.println("m1.colRange(new Range(1, 3)).isSubmatrix=" + m1.colRange(new Range(1, 3)).isSubmatrix());

        System.out.println("m1.colRange(1, 3).isSubmatrix=" + m1.colRange(1, 3).isSubmatrix());

        System.out.println("m1.row(1).isSubmatrix=" + m1.row(1).isSubmatrix());

        System.out.println("m1.col(1).isSubmatrix=" + m1.col(1).isSubmatrix());
    }


}
