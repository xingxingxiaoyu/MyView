package com.xuyu.myview.skywheel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/12/26.
 */
public class SkyWheelView extends LinearLayout
{
    public SkyWheelView(Context context)
    {
        super(context);
    }

    public SkyWheelView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public SkyWheelView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {

    }
}
