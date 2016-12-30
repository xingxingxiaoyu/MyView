package com.xuyu.myview.identifying_code;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/12/27.
 */
public class IdentifyingCodeView extends View
{
    private Paint paint;

    public IdentifyingCodeView(Context context)
    {
        super(context);
    }

    public IdentifyingCodeView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        canvas.drawText();
    }
}
