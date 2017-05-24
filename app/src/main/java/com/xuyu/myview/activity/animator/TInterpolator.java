package com.xuyu.myview.activity.animator;

import android.animation.TimeInterpolator;
import android.util.Log;

/**
 * Created by Administrator on 2017/5/22.
 */

public class TInterpolator implements TimeInterpolator
{
    @Override
    public float getInterpolation(float input)
    {
        float v = (float) (-2.94 * Math.pow(input, 3) + 3.14 * Math.pow(input, 2) + 0.8 * input);
        Log.e("=====", v + "");
        return v;
    }
}
