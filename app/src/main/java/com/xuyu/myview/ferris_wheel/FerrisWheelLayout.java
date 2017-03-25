package com.xuyu.myview.ferris_wheel;

import android.content.Context;
import android.icu.math.MathContext;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by: xuyu
 * <p>
 * on: 2017/3/23.
 * <p>
 * 描述：输入类描述
 */
public class FerrisWheelLayout extends ViewGroup
{

    private int DEFAULT_SIZE = 200;

    public FerrisWheelLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public FerrisWheelLayout(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public FerrisWheelLayout(Context context)
    {
        this(context, null);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr)
    {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measure(widthMeasureSpec);
        int height = measure(widthMeasureSpec);
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

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int centerX = l + (r - l) / 2;
        int centerY = t + (b - t) / 2;
        int radius = Math.min((r - l) / 2, (b - t) / 2) - 30;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            child.layout(centerX + (int) (radius * Math.cos(Math.PI * 2 / childCount * i)) - childWidth / 2,
                    centerY + (int) (radius * Math.cos(Math.PI * 2 / childCount * i)) - childHeight / 2,
                    centerX + (int) (radius * Math.cos(Math.PI * 2 / childCount * i)) + childWidth / 2,
                    centerY + (int) (radius * Math.cos(Math.PI * 2 / childCount * i)) + childWidth / 2);
        }
    }
    class LayoutParams extends ViewGroup.LayoutParams
    {
        public LayoutParams(Context c, AttributeSet attrs)
        {
            super(c, attrs);
        }

        public LayoutParams(int width, int height)
        {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source)
        {
            super(source);
        }
    }
}
