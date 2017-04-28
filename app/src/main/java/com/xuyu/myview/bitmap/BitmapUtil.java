package com.xuyu.myview.bitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by: xuyu
 * <p>
 * on: 2017/4/28.
 * <p>
 * 描述：输入类描述
 */
public class BitmapUtil
{
    public static Bitmap resetBitmap(Bitmap bitmap, int saturation)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 创建一个相同尺寸的可变的位图区,用于绘制调色后的图片
        Bitmap resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        ColorMatrix matrix = new ColorMatrix();

        // 设置饱和度
        matrix.setSaturation(saturation / 100F);
//        matrix.set

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));
        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return resultBitmap;
    }
}
