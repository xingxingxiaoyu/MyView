package com.xuyu.myview.activity.list.planb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xuyu.myview.R;

/**
 * 描述信息：
 *
 * @author xuyu
 * @date 2020/4/7
 */
public class PathView extends View {
    public static final String TAG = "PathView";

    //取值范围0-100
    private float animationProgress = 0;
    //取值范围100-400 每秒钟动画改变量
    private float animationSpeed = 20;

    private long lastTime = System.currentTimeMillis();
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            long currentTimeMillis = System.currentTimeMillis();
            int dtime = (int) (currentTimeMillis - lastTime);
            animationProgress += dtime * animationSpeed / 1000;
//            Log.e(TAG, "handleMessage: dtime " + dtime);
//            Log.e(TAG, "handleMessage: animationProgress " + animationProgress);
            animationProgress = animationProgress % 100;
            lastTime = currentTimeMillis;
            invalidate();
        }
    };

    private Path mPath;
    private Paint mPaint;
    private int mProgress = -1;
    private Bitmap mShipBitmap;
    private Bitmap mQuantBitmap;
    private Bitmap mPeopleBitmap;
    private Rect mShipRect;
    private Rect mPeopleRect;
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
        initShip();
    }

    private void initShip() {
        mShipBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.rowing_01_a);
        mQuantBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.rowing_02);
        mPeopleBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.rowing_04);
        mShipRect = getBitmapRect(mShipBitmap);
        mPeopleRect = getBitmapRect(mPeopleBitmap);

    }

    private Rect getBitmapRect(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        return new Rect(-width / 2, -height / 2, width / 2, height / 2);
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
        invalidate();
    }

    public void setAnimationSpeed(float animationSpeed) {
        this.animationSpeed = animationSpeed + 50;
        if (animationSpeed > 400) {
            animationSpeed = 400f;
        }

    }

    Matrix peopleMatrix = new Matrix();
    Matrix quantMatrix1 = new Matrix();
    Matrix quantMatrix2 = new Matrix();

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
            if (tan[0] < 0) {
                degrees += 180;
            }
            canvas.rotate(degrees);
            canvas.drawBitmap(mShipBitmap, null, mShipRect, mPaint);

            float abs = Math.abs(animationProgress - 50);
            peopleMatrix.setTranslate(abs / 50 * -20 - mPeopleBitmap.getWidth() / 2, -mPeopleBitmap.getHeight() / 2);
//            Log.e(TAG, "onDraw:matrix " + peopleMatrix);
//            Log.e(TAG, "onDraw:animationProgress " + animationProgress);
            canvas.drawBitmap(mPeopleBitmap, peopleMatrix, mPaint);

            quantMatrix1.setTranslate(-mQuantBitmap.getWidth() / 2 - 10, -mQuantBitmap.getHeight() / 2);
            quantMatrix1.postRotate(45 + 90 * abs / 50);
            quantMatrix1.postTranslate(-20, 0);
            canvas.drawBitmap(mQuantBitmap, quantMatrix1, mPaint);
            quantMatrix2.setTranslate(-mQuantBitmap.getWidth() / 2 - 10, -mQuantBitmap.getHeight() / 2);
            quantMatrix2.postRotate(-45 - 90 * abs / 50);
            quantMatrix2.postTranslate(-20, 0);
            canvas.drawBitmap(mQuantBitmap, quantMatrix2, mPaint);

            canvas.restore();
        }
        mHandler.sendEmptyMessage(1);
    }


}
