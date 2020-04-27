package com.xujiafeng.myview.activity.bezier_curve.my_bezier_line;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by: xuyu
 * <p>
 * on: 2017/3/15.
 * <p>
 * 描述：输入类描述
 */
public class BezierCurveLine extends View
{
    private Paint mPaint;

    public BezierCurveLine(Context context)
    {
        super(context);
        init();
    }

    private Point start, end, controlPoint1, controlPoint2;

    public void setPoint(Point start, Point end, Point controlPoint1, Point controlPoint2)
    {
        this.start = start;
        this.end = end;
        this.controlPoint1 = controlPoint1;
        this.controlPoint2 = controlPoint2;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        RectF rectF = new RectF(controlPoint1.x - 2, controlPoint1.y - 2, controlPoint1.x + 2, controlPoint1.y + 2);
        canvas.drawOval(rectF,mPaint);
        canvas.drawPoint(controlPoint2.x,controlPoint2.y,mPaint);
        for (int i = 0; i < 1000; i++)
        {
            Point point1 = getBezierCurveLine(start, end, controlPoint1, controlPoint2, 0.001 * i);
            Point point2 = getBezierCurveLine(start, end, controlPoint1, controlPoint2, 0.001 * (i + 1));
            canvas.drawLine(point1.x,point1.y,point2.x,point2.y,mPaint);
        }
    }

    public void setPaintColor(int color)
    {
        mPaint.setColor(color);
    }

    private void init()
    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
    }

    private Point getBezierCurveLine(Point start, Point end, Point controlPoint1, Point controlPoint2, double t)
    {
        Point point = new Point((int) getBezierCurveLine(start.x, end.x, controlPoint1.x, controlPoint2.x, t)
                , (int) getBezierCurveLine(start.y, end.y, controlPoint1.y, controlPoint2.y, t));
        return point;
    }

    private double getBezierCurveLine(double start, double end, double controlPoint1, double controlPoint2, double t)
    {
        return start * Math.pow(1 - t, 3) + 3 * controlPoint1 * t * Math.pow(1 - t, 2) + 3 * controlPoint2 * Math.pow(t, 2) * (1 - t) + end * Math.pow(t, 3);
    }
}
