package com.xujiafeng.myview.activity.animator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.xujiafeng.myview.R;
import com.xujiafeng.myview.util.ColorAnimatorUtil;

/**
 * Created by Administrator on 2017/5/19.
 */

public class TButton extends View
{
    private static final String TAG = "TButton";
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private RadialGradient mRadialGradient;
    private int mPressColor = PRESS_COLOR_DEFAULT;
    private int mUnPressColor = UNPRESS_COLOR_DEFAULT;
    public static final int PRESS_COLOR_DEFAULT = Color.GRAY;
    public static final int UNPRESS_COLOR_DEFAULT = Color.GRAY;
    private int[] colors;
    private float[] stops;
    private OnClickListener mL;

    public TButton(Context context)
    {
        this(context, null);
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
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TButton, defStyleAttr, 0);
        Log.e("====", "");
        int indexCount = typedArray.getIndexCount();
        Log.e("====", indexCount + "");
        for (int i = 0; i < indexCount; i++)
        {
            switch (typedArray.getIndex(i))
            {
                case R.styleable.TButton_press:
                    mPressColor = typedArray.getColor(R.styleable.TButton_press, PRESS_COLOR_DEFAULT);
                    break;
                case R.styleable.TButton_unpress:
                    mUnPressColor = typedArray.getColor(R.styleable.TButton_unpress, UNPRESS_COLOR_DEFAULT);
                    break;
            }
        }
        Log.e("====", Integer.toHexString(mPressColor) + "   " + Integer.toHexString(mUnPressColor));
        colors = new int[]{mUnPressColor, mUnPressColor};
        stops = new float[]{0f, 1f};
        typedArray.recycle();
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
                startAnimator(0);
                mL.onClick(this);
                return true;
            case MotionEvent.ACTION_UP:
                startAnimator(1);
                return false;
        }
        return false;
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l)
    {
        super.setOnClickListener(l);
        mL = l;
    }

    private ValueAnimator upAnimator;
    private ValueAnimator downAnimator;

    private void startAnimator(int type)
    {

        if (type == 1)
        {
            upAnimator = ValueAnimator.ofInt(0, 200);
            upAnimator.setDuration(200);
            upAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
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
            upAnimator.start();
        } else
        {
            downAnimator = ValueAnimator.ofInt(0, 200);
            downAnimator.setDuration(200);
            downAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
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
            downAnimator.start();
        }
    }
}
