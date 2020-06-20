package com.xujiafeng.myview.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 描述信息：
 *
 * @author xujiafeng
 * @date 2020-04-29
 */
public class BlackFrameLayout extends FrameLayout {
    private Paint mPaint = new Paint();

    public BlackFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        float[] floats = {-1f, 0f, 0f, 0f, 255f,
                0f, -1f, 0f, 0f, 255f,
                0f, 0f, -1f, 0f, 255f,
                0f, 0f, 0f, 1f, 0f};
//        float[] floats = {-0.5f, 0f, 0f, 0f, 125,
//                0f, -0.5f, 0f, 0f, 125,
//                0f, 0f, -0.5f, 0f, 125,
//                0f, 0f, 0f, 1f, 0f};
//        float[] floats = {1, 0f, 0f, 0f, 64,
//                0f, 1, 0f, 0f, 64,
//                0f, 0f, 1, 0f, 64,
//                0f, 0f, 0f, 1f, 0f};
        ColorMatrix cm = new ColorMatrix(floats);
//        ColorMatrix cm=new ColorMatrix();
//        cm.setSaturation(0);
        mPaint.setColorFilter(new ColorMatrixColorFilter(cm));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
//        canvas.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
//        canvas.restore();
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(null, mPaint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        canvas.restore();
    }
}
