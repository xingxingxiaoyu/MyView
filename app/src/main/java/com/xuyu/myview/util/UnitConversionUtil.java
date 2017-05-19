package com.xuyu.myview.util;

import android.content.Context;

import com.xuyu.myview.application.BApplication;

/**
 * Created by Administrator on 2017/5/19.
 */

public class UnitConversionUtil
{

    public static int Dp2Px(float dp)
    {
        final float scale = BApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int Px2Dp(float px)
    {
        final float scale = BApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
