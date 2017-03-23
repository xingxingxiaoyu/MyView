package com.xuyu.myview.clock;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.MainThread;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.view.View;

import com.xuyu.myview.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by: xuyu
 * <p>
 * on: 2017/3/22.
 * <p>
 * 描述：输入类描述
 */
public class ClockView extends View
{
    private int hour;
    private int minute;
    private int second;
    private Paint mPaintCircle;
    private int mHeight;
    private int mWidth;
    private int DEFAULT_SIZE = 600;
    private Paint mPaintLine;
    private Paint mPaintText;
    private Paint mPaintPoint;
    private Paint mPaintHour;
    private Paint mPaintMinute;
    private Paint mPaintSecond;
    private boolean isRunning;

    public ClockView(Context context)
    {
        this(context, null);
    }

    public ClockView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr)
    {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ClockView, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();
        Calendar c = Calendar.getInstance();
        int currentHour = c.get(Calendar.HOUR_OF_DAY);
        int currentMinute = c.get(Calendar.MINUTE);
        int currentSecond = c.get(Calendar.SECOND);
        for (int i = 0; i < indexCount; i++)
        {
            int index = typedArray.getIndex(i);
            switch (index)
            {
                case R.styleable.ClockView_hour:
                    this.hour = typedArray.getInt(index, currentHour);
                    break;
                case R.styleable.ClockView_minute:
                    minute = typedArray.getInt(index, currentMinute);
                    break;
                case R.styleable.ClockView_second:
                    second = typedArray.getInt(index, currentSecond);
                    break;
                default:
                    break;
            }
        }
        mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle.setColor(Color.GRAY);
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setStrokeWidth(5);

        mPaintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintLine.setColor(Color.GREEN);

        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setColor(Color.BLACK);
        mPaintText.setTextSize(20);

        mPaintPoint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintPoint.setColor(Color.RED);

        mPaintHour = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintHour.setColor(Color.BLACK);
        mPaintHour.setStrokeWidth(8);

        mPaintMinute = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintMinute.setColor(Color.BLUE);
        mPaintMinute.setStrokeWidth(8);

        mPaintSecond = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintSecond.setColor(Color.RED);
        mPaintSecond.setStrokeWidth(8);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measure(widthMeasureSpec);
        int height = measure(widthMeasureSpec);
        int length = Math.min(width, height);
        setMeasuredDimension(length, length);
    }

    private int measure(int widthMeasureSpec)
    {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        switch (mode)
        {
            case MeasureSpec.AT_MOST:
                return Math.min(size, DEFAULT_SIZE);
            case MeasureSpec.EXACTLY:
                return size;
            case MeasureSpec.UNSPECIFIED:
                return DEFAULT_SIZE;
            default:
                return 0;
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int radius = 0;
        mHeight = getHeight();
        mWidth = getWidth();
        canvas.translate(mHeight / 2F, mWidth / 2F);
        radius = mHeight / 2 - 2;
        //画最外面的圆
        canvas.drawCircle(0, 0, radius, mPaintCircle);
        //画刻度
        for (int i = 0; i < 60; i++)
        {
            if (i % 5 == 0)
            {
                canvas.drawLine(0, -radius + 8, 0, -radius + 18, mPaintLine);
            }
            else
            {
                canvas.drawLine(0, -radius + 8, 0, -radius + 15, mPaintLine);
            }
            canvas.rotate(6);
        }
        //画数字
        int value = 7;
        canvas.translate(-value, value);
        for (int i = 1; i <= 12; i++)
        {
            String text = String.valueOf(i);
            canvas.drawText(text, (float) ((radius - 30) * Math.cos(-Math.PI / 2 + i * Math.PI / 6)),
                    (float) ((radius - 30) * Math.sin(-Math.PI / 2 + i * Math.PI / 6)), mPaintText);
        }
        canvas.translate(value, -value);

        canvas.drawCircle(0, 0, 3, mPaintPoint);
        //画时针
        canvas.drawLine(0, 0, (float) ((radius - 80) * Math.cos(-Math.PI / 2 + hour * Math.PI / 6 + minute * Math.PI / 360 + second * Math.PI / (1800 * 60))),
                (float) ((radius - 80) * Math.sin(-Math.PI / 2 + hour * Math.PI / 6 + minute * Math.PI / 360) + second * Math.PI / (1800 * 60)), mPaintHour);
        //画分针
        canvas.drawLine(0, 0, (float) ((radius - 60) * Math.cos(-Math.PI / 2 + minute * Math.PI / 30 + second * Math.PI / 1800)),
                (float) ((radius - 60) * Math.sin(-Math.PI / 2 + minute * Math.PI / 30 + second * Math.PI / 1800)), mPaintMinute);
        //画秒针
        canvas.drawLine(0, 0, (float) ((radius - 35) * Math.cos(-Math.PI / 2 + second * Math.PI / 30)),
                (float) ((radius - 35) * Math.sin(-Math.PI / 2 + second * Math.PI / 30)), mPaintSecond);
        //画圆点
        canvas.drawCircle(0, 0, 3, mPaintPoint);
    }

    public void start()
    {
        isRunning = true;
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (isRunning)
                {
                    try
                    {
                        Thread.sleep(1000);
                        second++;
                        if (second == 60)
                        {
                            second = 0;
                            minute++;
                            if (minute == 60)
                            {
                                minute = 0;
                                hour++;
                                if (hour == 12)
                                {
                                    hour = 0;
                                }
                            }
                        }
                        if (mOnTimeChangeListener != null)
                        {
                            mHandler.post(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    mOnTimeChangeListener.onTimeChange(hour, minute, second);
                                }
                            });
                        }
                        postInvalidate();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private Handler mHandler=new Handler();
    public void stop()
    {
        isRunning = false;
    }

    public void setHour(int hour)
    {
        this.hour = hour;
        postInvalidate();
    }

    public void setMinute(int minute)
    {
        this.minute = minute;
        postInvalidate();
    }

    public void setSecond(int second)
    {
        this.second = second;
        postInvalidate();
    }

    private OnTimeChangeListener mOnTimeChangeListener;

    public void setOnTimeChangeListener(OnTimeChangeListener onTimeChangeListener)
    {
        mOnTimeChangeListener = onTimeChangeListener;
    }

    interface OnTimeChangeListener
    {
        void onTimeChange(int hour, int minute, int second);
    }
}
