package com.xuyu.myview.activity.animator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xuyu.myview.R;
import com.xuyu.myview.util.ColorAnimatorUtil;

/**
 * Created by Administrator on 2017/5/19.
 */

public class TButton extends View
{
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private RadialGradient mRadialGradient;
    private int mPressColor;
    private int mUnPressColor;
    private int[] colors;
    private float[] stops;

    public TButton(Context context)
    {
        super(context, null);
    }

    public TButton(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public TButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr)
    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ClockView, defStyleAttr, 0);
        mPressColor = typedArray.getColor(R.styleable.TButton_press, Color.parseColor("#ff0000"));
        mUnPressColor = typedArray.getColor(R.styleable.TButton_unpress, Color.parseColor("#00ff00"));
        colors = new int[]{mUnPressColor, mUnPressColor};
        stops = new float[]{0f, 1f};
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        mWidth = getWidth();
        mHeight = getHeight();
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        mRadialGradient = new RadialGradient(mWidth / 2, mHeight / 2, mWidth / 2, colors, stops, Shader.TileMode.CLAMP);
        mPaint.setShader(mRadialGradient);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                startDownAnimator();
                break;
            case MotionEvent.ACTION_UP:
                startUpAnimator();
                break;
        }
        return true;
    }

    private void startUpAnimator()
    {
        ValueAnimator animator = ValueAnimator.ofInt(0, 200);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                int value = (int) animation.getAnimatedValue();
                if (value < 100)
                {
                    colors = new int[]{mUnPressColor, mPressColor, mPressColor};
                    stops = new float[]{0f, value / 100f, 1f};
                } else
                {
                    colors = new int[]{mUnPressColor, ColorAnimatorUtil.getProgressColor(mPressColor, mUnPressColor, (value - 100) / 100f)};
                    stops = new float[]{0f, 1f};
                }
                postInvalidate();
            }
        });
        animator.start();
    }

    private void startDownAnimator()
    {
        ValueAnimator animator = ValueAnimator.ofInt(0, 200);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                int value = (int) animation.getAnimatedValue();
                if (value < 100)
                {
                    colors = new int[]{mPressColor, mUnPressColor, mUnPressColor};
                    stops = new float[]{0f, value / 100f, 1f};
                } else
                {
                    colors = new int[]{mPressColor, ColorAnimatorUtil.getProgressColor(mUnPressColor, mPressColor, (value - 100) / 100f)};
                    stops = new float[]{0f, 1f};
                }
                postInvalidate();
            }
        });
        animator.start();
    }
}
