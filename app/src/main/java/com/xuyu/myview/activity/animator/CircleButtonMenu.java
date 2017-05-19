package com.xuyu.myview.activity.animator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.xuyu.myview.util.UnitConversionUtil;

/**
 * Created by Administrator on 2017/5/19.
 */

public class CircleButtonMenu extends ViewGroup
{
    private int DEFAULT_SIZE = 200;
    private Paint mPaint;

    public CircleButtonMenu(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public CircleButtonMenu(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CircleButtonMenu(Context context)
    {
        this(context, null);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr)
    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measure(widthMeasureSpec);
        int height = measure(widthMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            View childAt = getChildAt(i);
            LayoutParams layoutParams = childAt.getLayoutParams();
            if (i == 0)
            {
                layoutParams.width = UnitConversionUtil.Dp2Px(50);
                layoutParams.height = UnitConversionUtil.Dp2Px(50);
            } else
            {
                layoutParams.width = UnitConversionUtil.Dp2Px(40);
                layoutParams.height = UnitConversionUtil.Dp2Px(40);
            }
        }
        int length = Math.min(width, height);
        setMeasuredDimension(length, length);
    }

    private int measure(int widthMeasureSpec)
    {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        switch (mode)
        {
            case MeasureSpec.AT_MOST:
                return Math.min(size, DEFAULT_SIZE);
            case MeasureSpec.EXACTLY:
                return size;
            case MeasureSpec.UNSPECIFIED:
                return DEFAULT_SIZE;
            default:
                return 0;
        }
    }

    private int centerX;
    private int centerY;
    private int radius;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        centerX = (r - l) / 2;
        centerY = (b - t) / 2;
        radius = Math.min((r - l) / 2, (b - t) / 2) - 30;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            View child = getChildAt(i);
            LayoutParams layoutParams = child.getLayoutParams();
            int childWidth = layoutParams.width;
            int childHeight = layoutParams.height;
            if (i == 0)
            {
                child.layout(centerX + (int) (radius * Math.cos((Math.PI * 2) / childCount * i)) - childWidth / 2,
                        centerY + (int) (radius * Math.sin((Math.PI * 2) / childCount * i)) - childHeight / 2,
                        centerX + (int) (radius * Math.cos((Math.PI * 2) / childCount * i)) + childWidth / 2,
                        centerY + (int) (radius * Math.sin((Math.PI * 2) / childCount * i)) + childWidth / 2);
            } else
            {
                child.layout(centerX - childWidth / 2,
                        centerY - childHeight / 2,
                        centerX + childWidth / 2,
                        centerY + childWidth / 2);
            }
        }
    }
}
