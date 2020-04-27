package com.xujiafeng.myview.activity.bezier_curve.bezier_show;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: xuyu
 * <p>
 * on: 2017/4/21.
 * <p>
 * 描述：输入类描述
 */
public class BezierShowView extends View
{
    private Point p0;
    private Point p2;
    private Point p1;
    private Paint pointPaint;
    private Paint linePaint;
    private TextPaint mTextPaint;
    private float mProgress;
    private float mProgress2;
    private List<Point> curveList;
    private Point p6;
    private Point p3;
    private Point p4;
    private Point p5;
    private List<Point> curveList2;

    public BezierShowView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {
        p0 = new Point(30, 300);
        p1 = new Point(200, 30);
        p2 = new Point(500, 300);
        pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

        p3 = new Point(30, 700);
        p4 = new Point(200, 430);
        p5 = new Point(400, 390);
        p6 = new Point(500, 800);
        curveList = new ArrayList<>();
        curveList2 = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        drawBezier(canvas, p0, p1, p2, mProgress, curveList);

        drawPoint(canvas, p3, Color.RED);
        drawPoint(canvas, p4, Color.RED);
        drawPoint(canvas, p5, Color.RED);
        drawPoint(canvas, p6, Color.RED);
        drawLine(canvas,p3,p4);
        drawLine(canvas,p4,p5);
        drawLine(canvas,p5,p6);

        Point point = getPoint(mProgress2, p3, p4);
        drawPoint(canvas, point, Color.BLUE);
        Point point2 = getPoint(mProgress2, p4, p5);
        drawPoint(canvas, point2, Color.BLUE);
        Point point3 = getPoint(mProgress2, p5, p6);
        drawPoint(canvas, point3, Color.BLUE);


        drawBezier(canvas, point, point2, point3, mProgress2, curveList2);
    }

    private void drawBezier(Canvas canvas, Point p0, Point p1, Point p2, float mProgress, List<Point> curveList)
    {
        drawPoint(canvas, p0, Color.RED);
        canvas.drawText("p0", 30, 320, mTextPaint);
        drawPoint(canvas, p1, Color.RED);
        canvas.drawText("p1", 200, 10, mTextPaint);
        drawPoint(canvas, p2, Color.RED);
        canvas.drawText("p2", 500, 320, mTextPaint);

        drawLine(canvas, p0, p1);
        drawLine(canvas, p1, p2);

        Point pa = drawPointInLine(canvas, mProgress, p0, p1, Color.BLUE);
        Point pb = drawPointInLine(canvas, mProgress, p1, p2, Color.BLUE);

        drawLine(canvas, pa, pb);
        Point point = getPoint(mProgress, pa, pb);
        drawPoint(canvas, point, Color.GREEN);
        curveList.add(point);

        drawCurve(canvas, curveList);
    }


    private void drawCurve(Canvas canvas, List<Point> curveList)
    {
        int size = curveList.size();
        for (int i = 0; i < size - 1; i++)
        {
            drawLine(canvas, curveList.get(i), curveList.get(i + 1), Color.GREEN);
        }
    }

    private Point drawPointInLine(Canvas canvas, float progress, Point p_a, Point p_b, int color)
    {

        Point point = getPoint(progress, p_a, p_b);
        drawPoint(canvas, point, color);
        return point;
    }

    private Point getPoint(float progress, Point p_a, Point p_b)
    {
        return new Point((int) getPointFloat(progress, p_a.x, p_b.x), (int) getPointFloat(progress, p_a.y, p_b.y));
    }

    private float getPointFloat(float progress, float x1, float x2)
    {
        return x1 + (x2 - x1) * progress;
    }

    private void drawLine(Canvas canvas, Point p_a, Point p_b)
    {
        linePaint.setColor(Color.BLACK);
        canvas.drawLine(p_a.x, p_a.y, p_b.x, p_b.y, linePaint);
    }

    private void drawLine(Canvas canvas, Point p_a, Point p_b, int color)
    {
        linePaint.setColor(color);
        canvas.drawLine(p_a.x, p_a.y, p_b.x, p_b.y, linePaint);
    }

    private void drawPoint(Canvas canvas, Point p, int color)
    {
        pointPaint.setColor(color);
        canvas.drawCircle(p.x, p.y, 3, pointPaint);
    }

    public void start()
    {
        curveList.clear();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0F, 1F);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                float value = (float) animation.getAnimatedValue();
                setProgress(value);
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    public void start2()
    {
        curveList2.clear();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0F, 1F);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                float value = (float) animation.getAnimatedValue();
                setProgress2(value);
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    public void setProgress(float progress)
    {
        mProgress = progress;
    }

    public void setProgress2(float progress)
    {
        mProgress2 = progress;
    }

}
