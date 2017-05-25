package com.xuyu.myview.util;

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
        for (int i = n; i >= m; i--)
        {
            result *= i;
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
        for (int i = 0; i < arr.length; i++)
        {
            matrix[i][j] = arr[i];
        }
        return matrix;
    }

}
