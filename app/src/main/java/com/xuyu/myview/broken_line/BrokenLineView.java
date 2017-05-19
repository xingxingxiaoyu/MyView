package com.xuyu.myview.broken_line;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xuyu.myview.R;

/**
 * Created by Administrator on 2017/5/19.
 */

public class BrokenLineView extends View
{
    //控件宽高
    private int mHeight;
    private int mWidth;
    //主线条颜色
    private int mainLineColor = MAIN_LINE_COLOR_DEFAULT;
    public final static int MAIN_LINE_COLOR_DEFAULT = Color.BLACK;
    //划线的模式
    private int mode = MODE_LINE_WITH_POINT;
    public final static int MODE_LINE_WITH_POINT = 0;
    public final static int MODE_LINE_WITHOUT_POINT = 1;
    public final static int MODE_BROKEN_LINE_WITH_POINT = 2;
    public final static int MODE_BROKEN_LINE_WITHOUT_POINT = 3;

    public BrokenLineView(Context context)
    {
        this(context, null);
    }

    public BrokenLineView(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public BrokenLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr)
    {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BrokenLineView, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++)
        {
            int typedArrayIndex = typedArray.getIndex(i);
            switch (typedArrayIndex)
            {
                case R.styleable.BrokenLineView_main_line_color:
                    mainLineColor = typedArray.getColor(typedArrayIndex, MAIN_LINE_COLOR_DEFAULT);
                    break;
                case R.styleable.BrokenLineView_mode:
                    mode = typedArray.getInt(typedArrayIndex, MODE_LINE_WITH_POINT);
                    break;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getHeight();
        mWidth = getWidth();
    }
}
