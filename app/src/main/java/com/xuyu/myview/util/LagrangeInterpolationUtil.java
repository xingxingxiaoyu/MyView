package com.xuyu.myview.util;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2017/5/22.
 * 拉格朗日插值工具类
 */

public class LagrangeInterpolationUtil
{
    /**
     * 获取
     *
     * @param x
     * @return
     */
    public static double getValue(double x, double[] a)
    {
        double result = 0;
        for (int i = 0; i <= a.length - 1; i++)
        {
            result += a[i] * Math.pow(x, a.length - 1 - i);
        }
        return result;
    }

    /**
     * 获取
     *
     * @param x
     * @return
     */
    public double getValue(double x)
    {
        double result = 0;
        for (int i = 0; i <= n; i++)
        {
            result += a[i] * Math.pow(x, n - i);
        }
        return result;
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

    public double[] getA()
    {
        return a;
    }

    /**
     * 函数的最高项系数
     */
    private int n;

    public void setPointList(List<double[]> pointList)
    {
        this.pointList = pointList;
        n = pointList.size() - 1;
        a = new double[n + 1];
        double[][] baseMatrix = getBaseMatrix(pointList);
        double[] b = getB(pointList);
        for (int i = 0; i <= n; i++)
        {
            double[][] matrix = MathUtil.change(i, b, baseMatrix);
            double det = MathUtil.getDet(baseMatrix);
            a[i] = MathUtil.getDet(matrix) / det;
        }
    }

    private double[] getB(List<double[]> pointList)
    {
        double[] b = new double[n];
        for (int i = 0; i < n; i++)
        {
            b[i] = pointList.get(i)[1];
        }
        return b;
    }

    /**
     * 获取方程组系数矩阵
     *
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
                double[] doubles = pointList.get(i);
                baseMatrix[i][j] = MathUtil.Anm(n - j, (int) doubles[2]) * pow(doubles[0], n - j - (int) doubles[2]);
            }
        }
        return baseMatrix;
    }

    private double pow(double x, int n)
    {
        if (n < 0)
        {
            return 0;
        } else
        {
            return Math.pow(x, n);
        }
    }
}
