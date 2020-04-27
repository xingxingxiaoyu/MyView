package com.xujiafeng.myview.activity.bitmap.identify;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xujiafeng.myview.R;

import java.util.Random;


public class IdentifyColorView extends View {
    private int level;
    private Random random;
    private Rect mRect;
    private Paint mNormalPaint;
    private Paint mKeyPaint;
    private int keyY;
    private int keyX;

    public IdentifyColorView(Context context) {
        super(context);
        init(null, 0);
    }

    public IdentifyColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public IdentifyColorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.IdentifyColorView, defStyle, 0);

        level = a.getInt(
                R.styleable.IdentifyColorView_level, 1);
        a.recycle();

        random = new Random();
        mRect = new Rect();
        mNormalPaint = new Paint();
        mKeyPaint = new Paint();
        syncColor();

    }

    private void syncColor() {
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        mNormalPaint.setColor(Color.argb(255, red, green, blue));

        int space = 100 / level + 5;
        int keyRed = red + random.nextInt(2 * space + 1) - space;
        if (keyRed < 0) {
            keyRed = 0;
        }
        if (keyRed > 255) {
            keyRed = 255;
        }
        int keyGreen = green + random.nextInt(2 * space + 1) - space;
        if (keyGreen < 0) {
            keyGreen = 0;
        }
        if (keyGreen > 255) {
            keyGreen = 255;
        }
        int keyBlue = blue + random.nextInt(2 * space + 1) - space;
        if (keyBlue < 0) {
            keyBlue = 0;
        }
        if (keyBlue > 255) {
            keyBlue = 255;
        }
        mKeyPaint.setColor(Color.argb(255, keyRed, keyGreen, keyBlue));
    }


    public void setLevel(int level) {
        this.level = level;
        syncColor();
        postInvalidate();
    }

    int width;
    int height;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int n = level + 2;
        width = getWidth() / n - 1;
        height = getHeight() / n - 1;
        keyX = random.nextInt(n);
        keyY = random.nextInt(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mRect.set(i * width + i, j * height + j, (i + 1) * width + i, (j + 1) * height + j);
                if (i == keyX && j == keyY) {
                    canvas.drawRect(mRect, mKeyPaint);
                } else {
                    canvas.drawRect(mRect, mNormalPaint);
                }
            }
        }
    }

    public boolean isTrue(float x, float y) {
        if (x >= keyX * width + keyX && x <= (keyX + 1) * width + keyX &&
                y >= keyY * height + keyY && y <= (keyY + 1) * height + keyY) {
            return true;
        }
        return false;
    }
}
