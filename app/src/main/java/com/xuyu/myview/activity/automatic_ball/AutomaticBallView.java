package com.xuyu.myview.activity.automatic_ball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * Created by Administrator on 2016/12/26.
 */
public class AutomaticBallView extends View
{
    //方向0-360
    private double direction;
    //画笔
    private Paint paint;
    private int width;
    private int height;
    //圆的半径
    private int radius;
    //球的位置
    private MPoint point;
    //刷新的时间
    private long refreshTime;
    //每帧的速度
    private double speed;
    private double PI;
    private MHandler handler;

    public AutomaticBallView(Context context)
    {
        this(context,null);
    }

    public AutomaticBallView(Context context, AttributeSet attrs)
    {
        this(context, attrs,0);
    }

    public AutomaticBallView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        handler=new MHandler();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);

        PI = Math.PI;
        direction = PI * 7 / 4;
        //默认球的半径
        radius = 50;
        //默认球的位置
        point = new MPoint(50, 50);
        refreshTime = 8L;
        speed = 10;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        width = getWidth();
        height = getHeight();

        canvas.drawCircle(point.x, point.y, radius, paint);
        handler.sendEmptyMessageDelayed(0,refreshTime);
    }

    private int i=0;
    private class MHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            float dx =(float)(speed * Math.cos(direction));
            float dy =(float)(-speed * Math.sin(direction));
            Log.e("===",dx+"====="+dy);
            Log.e("====",direction+"");
            point.offset(dx, dy);
            //碰到顶部
            if (point.y <= radius)
            {
                point.y=radius;
                if (direction >= 0 && direction <= 1.0 / 2 * PI)
                {
                    setRandomDirection(3.0 / 2 * PI, 2 * PI);
                } else if (direction >=1.0/2* PI && direction <=  PI)
                {
                    setRandomDirection(PI, 3.0 / 2 * PI);
                }
                setPaintColor();
            }
            //碰到底部
            else if (point.y >= height - radius)
            {
                point.y = height - radius;
                if (direction >= 3.0/2 * PI && direction <=  2 * PI)
                {
                    setRandomDirection(0, (1.0/2) * PI);
                } else if (direction >= PI && direction <= (3.0/2) * PI)
                {
                    setRandomDirection((1.0/2) * PI, PI);
                }
                setPaintColor();
            }
            //碰到左端
            else if (point.x <= radius)
            {
                point.x = radius;
                if (direction >= PI && direction <= (3.0/2) * PI)
                {
                    setRandomDirection((3.0/2) * PI, 2 * PI);
                } else if (direction >= (1.0/2) * PI && direction <= PI)
                {
                    setRandomDirection(0, (1.0/2) * PI);
                }
                setPaintColor();
            }
            //碰到右端
            else if (point.x >= width - radius)
            {
                point.x = width - radius;
                if (direction >= 0 && direction <= (1.0/2) * PI)
                {
                    setRandomDirection((1.0/2) * PI, PI);
                } else if (direction >= (3.0/2) * PI && direction <= 2 * PI)
                {
                    setRandomDirection(PI, (3/2) * PI);
                }
                setPaintColor();
            }
            invalidate();
        }
    };

    private void setRandomDirection(double start, double end)
    {
        direction = start + (end - start) * (150+new Random().nextInt(700))/1000;
        Log.e("======direction======",direction+"");
    }

    private void setPaintColor()
    {
        Random random = new Random();
        paint.setColor(Color.argb(255,
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)));
    }
    class MPoint
    {
        float x;
        float y;

        public MPoint()
        {
        }

        public MPoint(float x, float y)
        {
            this.x = x;
            this.y = y;
        }

        void offset(float dx, float dy)
        {
            x+=dx;
            y+=dy;
        }
    }
}
