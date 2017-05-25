package com.xuyu.myview.activity.animator;

import android.animation.TimeInterpolator;
import android.util.Log;

import com.google.gson.Gson;
import com.xuyu.myview.util.LagrangeInterpolationUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/22.
 */

public class TInterpolator implements TimeInterpolator
{
    double[] a;

    @Override
    public float getInterpolation(float input)
    {
        if (a == null)
        {
            LagrangeInterpolationUtil lagrangeInterpolationUtil = new LagrangeInterpolationUtil();
            ArrayList<double[]> pointList = new ArrayList<>();
            pointList.add(new double[]{0, 0, 0});
            pointList.add(new double[]{0, 0.8, 1});
            pointList.add(new double[]{0.9, 1.2, 0});
            pointList.add(new double[]{1, 1, 0});
            lagrangeInterpolationUtil.setPointList(pointList);
            a=lagrangeInterpolationUtil.getA();
            Log.e("==",new Gson().toJson(a));
        }
        double v = LagrangeInterpolationUtil.getValue(input,a);
        Log.e("==",v+"");
        return (float) v;
    }
}
