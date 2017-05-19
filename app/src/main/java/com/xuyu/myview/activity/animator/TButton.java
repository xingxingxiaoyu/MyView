package com.xuyu.myview.activity.animator;

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
        mPressColor = typedArray.getColor(R.styleable.TButton_press, Color.parseColor("#eeeeee"));
        mUnPressColor = typedArray.getColor(R.styleable.TButton_unpress, Color.parseColor("#999999"));
        mRadialGradient = new RadialGradient(200, 200, 200, mPressColor,mUnPressColor, Shader.TileMode.CLAMP);
        mPaint.setShader(mRadialGradient);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getWidth();
        mHeight = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
}
