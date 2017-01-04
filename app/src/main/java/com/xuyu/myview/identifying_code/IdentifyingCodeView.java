package com.xuyu.myview.identifying_code;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by Administrator on 2016/12/27.
 */
public class IdentifyingCodeView extends View
{

    private Paint paint;
    private int length;

    public IdentifyingCodeView(Context context)
    {
        super(context);
    }

    public IdentifyingCodeView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    private void init()
    {
        length=5;
        this.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int width1 = getWidth();
        int width= width1 /length;
        int height = getHeight();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(60.0F);
        paint.setColor(Color.BLACK);
        RectF rectF=new RectF(0,0,width1,height);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rectF,paint);
        canvas.translate(-width/2.0F,height/2.0F+5);
        for (int i = 0; i<length; i++)
        {
            paint.setColor(getRandomColor());
            float randomAngle = getRandomAngle();
            canvas.translate(width,0);
            canvas.rotate(randomAngle);
            canvas.drawText(getRandomString(),0,0,paint);
            canvas.rotate(-randomAngle);

        }

    }

    private int getRandomColor()
    {
        Random random = new Random();
        int red=0;
        int green=0;
        int blue=0;
        do
        {
             red= random.nextInt(256);
             green= random.nextInt(256);
             blue= random.nextInt(256);
        }while (255-red+255-green+255-blue<100);
        int argb=Color.argb(255,red,green,blue);
        return argb;
    }

    private float getRandomAngle()
    {
        return (new Random().nextFloat()-0.5F)*90;
    }

    /**
     * @return 0123456789abcdefghijklmnopqrstuvwxyz
     */
    private String getBasicString() {
        char one='0';
        char a='a';
        char A='A';
        StringBuffer s=new StringBuffer();
        for (int i = 0; i < 10; i++) {
            s.append((char)(one+i));
        }
        for (int i = 0; i < 26; i++) {
            s.append((char)(a+i));
        }
        for (int i = 0; i < 26; i++) {
            s.append((char)(A+i));
        }
        return s.toString();
    }
    private String getRandomString()
    {
        String basicString = getBasicString();
        int beginIndex = new Random().nextInt(basicString.length());
        return basicString.substring(beginIndex,beginIndex+1);
    }
}
