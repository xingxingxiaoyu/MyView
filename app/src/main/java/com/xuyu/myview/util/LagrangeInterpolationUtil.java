package com.xuyu.myview.util;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22.
 * 拉格朗日插值工具类
 */

public class LagrangeInterpolationUtil
{

    /**获取
     * @param x
     * @return
     */
    public double getValue(double x)
    {
        return 0;
    }

    /**
     * point[0]  x
     * point[1]  y
     * point[2]  0：y为函数值 >0：y为高阶导数值 缺省为0
     */
    private List<double[]> pointList;
    /**
     * 函数的多项式系数
     */
    private double[] a;
    /**
     * 函数的最高项系数
     */
    private int n;

    public void setPointList(List<double[]> pointList)
    {
        this.pointList = pointList;
        n = pointList.size() - 1;
        a = new double[n];
        double[][]baseMatrix=getBaseMatrix(pointList);
    }

    /**获取方程组系数矩阵
     * @param pointList
     * @return
     */
    private double[][] getBaseMatrix(List<double[]> pointList)
    {
        int size = pointList.size();
        double[][] baseMatrix = new double[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
//                baseMatrix[i][j]=
            }
        }
        return baseMatrix;
    }
}
