package com.xujiafeng.myview.util;

/**
 * Created by Administrator on 2017/5/24.
 * 数学相关的工具类
 */
public class MathUtil
{
    /**
     * 获取行列式值
     *
     * @param matrix
     * @return
     */
    public static double getDet(double[][] matrix)
    {
        double[][] clone = matrix.clone();
        int length = clone.length;
        double result = 0;
        if (length == 1)
        {
            result = clone[0][0];
        } else
        {
            for (int i = 0; i < length; i++)
            {
                result += clone[0][i] * Math.pow(-1, i) * getDet(ignore(0, i, clone));
            }
        }
        return result;
    }

    /**
     * 获取行列式指定的余子式
     *
     * @param i
     * @param j
     * @param matrix
     * @return
     */
    public static double[][] ignore(int i, int j, double[][] matrix)
    {
        double[][] clone = matrix.clone();
        int length = clone.length;
        double[][] result = new double[length - 1][length - 1];
        for (int k = 0; k < length - 1; k++)
        {
            for (int m = 0; m < length - 1; m++)
            {
                result[k][m] = clone[k >= i ? k + 1 : i][m >= j ? m + 1 : m];
            }
        }
        return result;
    }

    /**
     * 获取n取m的排列值
     *
     * @param n
     * @param m
     * @return
     */
    public static int Anm(int n, int m)
    {
        int result = 1;
        for (int i = 0; i < m; i++)
        {
            result *= n - i;
        }
        return result;
    }

    /**
     * 把矩阵的第j列更换为arr
     *
     * @param j
     * @param arr
     * @param matrix
     * @return
     */
    public static double[][] change(int j, double[] arr, double[][] matrix)
    {
        int length = matrix.length;
        double[][] clone = new double[length][length];
        for (int i = 0; i < length; i++)
        {
            for (int k = 0; k < matrix[0].length; k++)
            {
                clone[i][k] = matrix[i][k];
            }
        }
        for (int i = 0; i < arr.length; i++)
        {
            clone[i][j] = arr[i];
        }
        return clone;
    }

}
