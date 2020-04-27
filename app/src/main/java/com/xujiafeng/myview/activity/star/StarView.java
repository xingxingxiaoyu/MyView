package com.xujiafeng.myview.activity.star;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * 描述信息：
 *
 * @author xuyu
 * @date 2019/11/23
 */
public class StarView extends View {
    public StarView(Context context) {
        super(context);
    }

    public StarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        int width = getWidth();
//        int height = getHeight();
//        int radius = Math.min(width, height) / 2;
//        Path path = new Path();
//        canvas.translate(radius, radius);
//        double startDegrees = -Math.PI / 2;
//        double changeDegress = Math.PI * 4 / 5;
//        path.moveTo((float) (radius * Math.cos(startDegrees)), (float) (radius * Math.sin(startDegrees)));
//        for (int i = 0; i < 10; i++) {
//            startDegrees += changeDegress;
////            path.lineTo((float) (radius * Math.cos(startDegrees)), (float) (radius * Math.sin(startDegrees)));
//            path.quadTo((float) (radius * Math.cos(startDegrees - Math.PI * 2 / 5)), (float) (radius * Math.sin(startDegrees - Math.PI * 2 / 5)),
//                    (float) (radius * Math.cos(startDegrees)), (float) (radius * Math.sin(startDegrees)));
//        }
//        canvas.drawPath(path, new Paint());
//        super.onDraw(canvas);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2;
        Path path = new Path();
        canvas.translate(radius, radius);
        double startDegrees = -Math.PI / 2;
        double changeDegress = Math.PI * 20 / 21;
        path.moveTo((float) (radius * Math.cos(startDegrees)), (float) (radius * Math.sin(startDegrees)));
        for (int i = 0; i < 42; i++) {
            startDegrees += changeDegress;
//            path.lineTo((float) (radius * Math.cos(startDegrees)), (float) (radius * Math.sin(startDegrees)));
            path.quadTo((float) (radius * Math.cos(startDegrees - Math.PI * 5 / 21)), (float) (radius * Math.sin(startDegrees - Math.PI * 5 / 21)),
                    (float) (radius * Math.cos(startDegrees)), (float) (radius * Math.sin(startDegrees)));
        }
        canvas.drawPath(path, new Paint());
        super.onDraw(canvas);
    }
}
