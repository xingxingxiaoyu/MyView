package com.xuyu.myview.animator_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by: xuyu
 * <p>
 * on: 2017/3/17.
 * <p>
 * 描述：输入类描述
 */
public class AnimatorLayout extends RelativeLayout
{
    private Context mContext;
    private Button mButton;
    private boolean isRunning = false;

    private String TAG = "AnimatorView";
    private AnimatorView mAnimatorView;

    public AnimatorLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init()
    {
        mButton = new Button(mContext);
        mButton.setText("开始");
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        addView(mButton, lp);
        mAnimatorView = new AnimatorView(mContext);
        addView(mAnimatorView, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                isRunning = !isRunning;
                if (isRunning)
                {
                    mButton.setText("暂停");
                    mAnimatorView.startAnimator();
                }
                else
                {
                    mButton.setText("开始");
                    mAnimatorView.endAnimator();
                }
            }
        });
    }

    private class AnimatorView extends View
    {
        private Paint mPaint;

        private AnimatorState mAnimatorState;
        private Paint mPaint2;
        private StringBuilder mStringBuilder;
        private char[] mChars = "Xuyu is the best people in the world!".toCharArray();

        public AnimatorView(Context context)
        {
            super(context);
            init();
        }

        private void init()
        {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setColor(Color.RED);
            mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint2.setColor(Color.RED);
            mAnimatorState = new AnimatorState();
        }

        Handler mHandler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);
                mAnimatorState.newState();
                invalidate();
            }
        };

        private void endAnimator()
        {

        }

        private void startAnimator()
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while (isRunning)
                    {
                        mHandler.sendEmptyMessage(1);
                        try
                        {
                            Thread.sleep(40L);
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            //头
            canvas.drawCircle(150, 100, mAnimatorState.radius, mPaint);
            Path path = new Path();
            //身体
            path.moveTo(150, 100 + mAnimatorState.radius);
            path.lineTo(75, 100 + mAnimatorState.radius + 100);
            path.lineTo(225, 100 + mAnimatorState.radius + 100);
            path.close();
            canvas.drawPath(path, mPaint);
            //脚
            canvas.drawLine(110, 100 + mAnimatorState.radius + 100, 90, 100 + mAnimatorState.radius + 200, mPaint);
            canvas.drawLine(190, 100 + mAnimatorState.radius + 100, 210, 100 + mAnimatorState.radius + 200, mPaint);
            //手
            canvas.drawLine(130, 100 + mAnimatorState.radius + 80 / 3, 150 + 120 * cos(mAnimatorState.angle), 100 + mAnimatorState.radius + 120 * sin(mAnimatorState.angle), mPaint);
            canvas.drawLine(170, 100 + mAnimatorState.radius + 80 / 3, 150 + 120 * cos(180 - mAnimatorState.angle), 100 + mAnimatorState.radius + 120 * sin(180 - mAnimatorState.angle), mPaint);

            Path path1 = new Path();
            path1.moveTo(150 + 120 * cos(mAnimatorState.angle), 100 + mAnimatorState.radius + 120 * sin(mAnimatorState.angle));
            for (int i = 0; i <= 100; i++)
            {
                float angle = mAnimatorState.angle + (540 - 2 * mAnimatorState.angle) * i / 100;
                path1.lineTo(150 + 120 * cos(angle), 100 + mAnimatorState.radius + 120 * sin(angle));
            }
            mStringBuilder=new StringBuilder();
            int length = mChars.length;
            for (int i = 0; i < length; i++)
            {
                mStringBuilder.append(mChars[i]);
                for (int j = 0; j < 1; j++)
                {
                    mStringBuilder.append(" ");
                }
            }
            canvas.drawTextOnPath(mStringBuilder.toString(), path1, 10, 10, mPaint);
            mPaint2.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path1, mPaint2);
        }

        float cos(float angle)
        {
            return (float) Math.cos(angle * Math.PI / 180);
        }

        float sin(float angle)
        {
            return (float) Math.sin(angle * Math.PI / 180);
        }

        class AnimatorState
        {
            float radius = 30;
            float angle = 137;
            boolean isAdd = true;
            private float speed = 2;

            public void newState()
            {
                if (isAdd)
                {
                    if (angle > 220)
                    {
                        isAdd = !isAdd;
                    }
                    else
                    {
                        angle += speed;
                    }
                }
                else
                {
                    if (angle < 137)
                    {
                        isAdd = !isAdd;
                    }
                    else
                    {
                        angle -= speed;
                    }
                }
            }

        }
    }


   /* public void getList(String str, String childStr)
    {
        char[] chars = str.toCharArray();
        char[] chars1 = childStr.toCharArray();
        int length = chars.length;
        int length1 = chars1.length;
        //用于存储子字符串每一个字符出现的位置
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>(length1);
        for (int i = 0; i < length1; i++)
        {
            lists.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length1; j++)
            {
                if (chars[i] == chars1[j])
                //                    第j个位置加上一个i
                {
                    lists.get(j).add(i);
                }
            }
        }
    }*/

}
