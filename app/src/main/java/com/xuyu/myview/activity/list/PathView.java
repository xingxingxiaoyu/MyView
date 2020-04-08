package com.xuyu.myview.activity.list;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 描述信息：
 *
 * @author xuyu
 * @date 2020/4/7
 */
public class PathView extends View {
    public static final String TAG = "PathView";

    private Path mPath;
    private Paint mPaint;
    private int mProgress = -1;
    private Bitmap mBitmap;
    private Rect mRect;
    private float[] pos = new float[2];
    private float[] tan = new float[2];
    private PathMeasure mPathMeasure;
    private float mLength;
    private float mMax = 100f;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0xff123456);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(40);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    public void setPath(Path path) {
        mPath = path;
        mPathMeasure = new PathMeasure(mPath, false);
        mLength = mPathMeasure.getLength();
        postInvalidate();
    }

    public void setMax(int max) {
        mMax = max;
    }

    public void setProgress(int progress) {
        mProgress = progress;
        if (mPath != null) {
            mPathMeasure.getPosTan(progress / mMax * mLength, pos, tan);
            Log.e(TAG, "pos " + pos[0] + " " + pos[1] + " " + "tan " + tan[0] + " " + tan[1]);


        }
//        postInvalidate();
        invalidate();
    }

    public void setImage(int imageRes) {
        mBitmap = BitmapFactory.decodeResource(getResources(), imageRes);
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        mRect = new Rect(-width / 2, -height / 2, width / 2, height / 2);
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
        if (mProgress >= 0) {
            canvas.save();
            canvas.translate(pos[0], pos[1]);
            double atan = Math.atan(tan[1] / tan[0]);
            float degrees = (float) (atan * 180 / Math.PI);
            Log.e(TAG, "degrees " + degrees);
            degrees += 90;
            if (tan[0] < 0) {
                degrees += 180;
            }
            canvas.rotate(degrees);
            canvas.drawBitmap(mBitmap, null, mRect, mPaint);
            canvas.restore();
        }
    }


}
