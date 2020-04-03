package com.xuyu.myview.activity.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.xuyu.myview.util.UnitConversionUtil;

/**
 * Created by Administrator on 2017/5/19.
 */

public class CircleButtonMenu extends ViewGroup
{
    private static final String TAG = "CircleButtonMenu";
    private int DEFAULT_SIZE = 200;
    private Paint mPaint;
    private int state = STATE_OPEN;
    public static final int STATE_OPEN = 0;
    public static final int STATE_CLOSE = 1;

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
            if (i == childCount - 1)
            {
                layoutParams.width = UnitConversionUtil.Dp2Px(50);
                layoutParams.height = UnitConversionUtil.Dp2Px(50);
                childAt.setOnClickListener(listener);
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
    private int max_radius = 0;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        centerX = (r - l) / 2;
        centerY = (b - t) / 2;
        max_radius = Math.min((r - l) / 2, (b - t) / 2) - 60;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            View child = getChildAt(i);
            LayoutParams layoutParams = child.getLayoutParams();
            int childWidth = layoutParams.width;
            int childHeight = layoutParams.height;
            if (i == childCount - 1)
            {
                child.layout(centerX - childWidth / 2,
                        centerY - childHeight / 2,
                        centerX + childWidth / 2,
                        centerY + childWidth / 2);
            } else
            {
                child.layout(centerX + (int) (max_radius * Math.cos((Math.PI * 2) / (childCount - 1) * i)) - childWidth / 2,
                        centerY + (int) (max_radius * Math.sin((Math.PI * 2) / (childCount - 1) * i)) - childHeight / 2,
                        centerX + (int) (max_radius * Math.cos((Math.PI * 2) / (childCount - 1) * i)) + childWidth / 2,
                        centerY + (int) (max_radius * Math.sin((Math.PI * 2) / (childCount - 1) * i)) + childWidth / 2);
            }
        }
    }

    public void setState(int state)
    {
        this.state = state;
    }

    private OnClickListener listener = new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if (state == STATE_CLOSE)
            {
                ValueAnimator openAnimator = ValueAnimator.ofInt(0, max_radius);
                openAnimator.setDuration(1000);
                openAnimator.addListener(new AnimatorListenerAdapter()
                {
                    @Override
                    public void onAnimationEnd(Animator animation)
                    {
                        super.onAnimationEnd(animation);
                        setState(STATE_OPEN);
                    }
                });
                openAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation)
                    {
                        int childCount = getChildCount();
                        int radius = (int) animation.getAnimatedValue();
                        for (int i = 0; i < childCount - 1; i++)
                        {
                            View child = getChildAt(i);
                            LayoutParams layoutParams = child.getLayoutParams();
                            int childWidth = layoutParams.width;
                            int childHeight = layoutParams.height;
                            child.setLeft(centerX + (int) (radius * Math.cos((Math.PI * 2) / (childCount - 1) * i)) - childWidth / 2);
                            child.setTop(centerY + (int) (radius * Math.sin((Math.PI * 2) / (childCount - 1) * i)) - childHeight / 2);
                            child.setRight(centerX + (int) (radius * Math.cos((Math.PI * 2) / (childCount - 1) * i)) + childWidth / 2);
                            child.setBottom(centerY + (int) (radius * Math.sin((Math.PI * 2) / (childCount - 1) * i)) + childHeight / 2);
                        }
                    }
                });
                openAnimator.setInterpolator(new TInterpolator());
                openAnimator.start();
            } else if (state == STATE_OPEN)
            {
                ValueAnimator closeAnimator = ValueAnimator.ofInt(max_radius, 0);
                closeAnimator.setDuration(1000);
                closeAnimator.addListener(new AnimatorListenerAdapter()
                {
                    @Override
                    public void onAnimationEnd(Animator animation)
                    {
                        super.onAnimationEnd(animation);
                        setState(STATE_CLOSE);
                    }
                });
                closeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation)
                    {
                        int childCount = getChildCount();
                        int radius = (int) animation.getAnimatedValue();
                        for (int i = 0; i < childCount - 1; i++)
                        {
                            View child = getChildAt(i);
                            LayoutParams layoutParams = child.getLayoutParams();
                            int childWidth = layoutParams.width;
                            int childHeight = layoutParams.height;
                            child.setLeft(centerX + (int) (radius * Math.cos((Math.PI * 2) / (childCount - 1) * i)) - childWidth / 2);
                            child.setTop(centerY + (int) (radius * Math.sin((Math.PI * 2) / (childCount - 1) * i)) - childHeight / 2);
                            child.setRight(centerX + (int) (radius * Math.cos((Math.PI * 2) / (childCount - 1) * i)) + childWidth / 2);
                            child.setBottom(centerY + (int) (radius * Math.sin((Math.PI * 2) / (childCount - 1) * i)) + childHeight / 2);
                        }
                    }
                });
                closeAnimator.setInterpolator(new TInterpolator());
                closeAnimator.start();
            }
        }
    };
}
