package com.xuyu.myview.activity.animator.sinple_animator;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/5/22.
 */

public class SimpleAnimatorView extends View
{
    private Paint mPaint;
    private RectF mRectF;
    private int mHeight;
    private int mWidth;

    public SimpleAnimatorView(Context context)
    {
        this(context, null);
    }

    public SimpleAnimatorView(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public SimpleAnimatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
        mHeight = getHeight();
        mWidth = getWidth();
    }

    private void init()
    {
        mRectF = new RectF(-200, -100, 200, 100);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#770000"));
    }

    private float roundRadius;
    private float width;
    private float height = 0f;

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        mRectF.set(-200f + width, -100f + height, 200f - width, 100f + height);
        canvas.drawRoundRect(mRectF, roundRadius, roundRadius, mPaint);
    }

    public void startAnimator()
    {
        reset();
        AnimatorSet set = new AnimatorSet();
        final ValueAnimator animator1 = ValueAnimator.ofFloat(0f, 100f);
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                roundRadius = (float) animator1.getAnimatedValue();
                postInvalidate();
            }
        });
        animator1.setDuration(1000);
        final ValueAnimator animator2 = ValueAnimator.ofFloat(0f, 100f);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                width = (float) animator2.getAnimatedValue();
                postInvalidate();
            }
        });
        animator2.setDuration(1000);
        final ValueAnimator animator3 = ValueAnimator.ofFloat(0f, -200f);
        animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                height = (float) animator3.getAnimatedValue();
                postInvalidate();
            }
        });
        animator3.setDuration(1000);
        set.play(animator1);
        set.play(animator2).after(800);
        set.play(animator3).after(1600);
        set.start();
    }

    private void reset()
    {
        roundRadius = 0f;
        width = 0f;
        height = 0f;
    }
}
