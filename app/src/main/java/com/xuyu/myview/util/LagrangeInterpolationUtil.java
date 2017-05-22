package com.xuyu.myview.util;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22.
 * 拉格朗日插值工具类
 */

public class LagrangeInterpolationUtil
{
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

    private double[][] getBaseMatrix(List<double[]> pointList)
    {
        int size = pointList.size();
        double[][] baseMatrix = new double[size][size];
        return baseMatrix;
    }

    public double getValue(double x)
    {
        return 0;
    }

    private double getDet(double[][] matrix)
    {
        int length = matrix.length;
        double result = 0;
        if (length == 1)
        {
            result = matrix[0][0];
        } else
        {
            for (int i = 0; i < length; i++)
            {
                result += matrix[0][i] * Math.pow(-1, i) * getDet(ignore(0, i, matrix));
            }
        }
        return result;
    }

    private double[][] ignore(int i, int j, double[][] matrix)
    {
        int length = matrix.length;
        double[][] result = new double[length - 1][length - 1];
        for (int k = 0; k < length - 1; k++)
        {
            for (int m = 0; m < length - 1; m++)
            {
                result[k][m] = matrix[k >= i ? k + 1 : i][m >= j ? m + 1 : m];
            }
        }
        return result;
    }
}
