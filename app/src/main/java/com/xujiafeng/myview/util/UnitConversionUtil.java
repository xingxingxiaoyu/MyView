package com.xujiafeng.myview.util;

import com.xujiafeng.myview.application.BaseApplication;

/**
 * Created by Administrator on 2017/5/19.
 */

public class UnitConversionUtil
{

    public static int Dp2Px(float dp)
    {
        final float scale = BaseApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int Px2Dp(float px)
    {
        final float scale = BaseApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
