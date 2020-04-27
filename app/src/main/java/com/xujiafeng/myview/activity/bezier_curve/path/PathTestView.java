package com.xujiafeng.myview.activity.bezier_curve.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by: xuyu
 * <p>
 * on: 2017/4/24.
 * <p>
 * 描述：输入类描述
 */
public class PathTestView extends View
{
    private Paint mPaint;

    public PathTestView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Path path = new Path();
        path.lineTo(300,300);
        path.lineTo(400,200);
        canvas.drawPath(path,mPaint);

        Path path1 = new Path();
        path1.moveTo(500,100);
        path1.lineTo(300,200);
        path1.lineTo(700,900);
        path1.close();
        canvas.drawPath(path1,mPaint);
    }
}
