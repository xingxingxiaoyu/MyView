package com.xuyu.myview.anim;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewAnimator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xuyu on 2017/3/2.
 * 描述：
 */

public class ColorAnimatorUtils
{
    public void setOnColorChangeListener(OnColorChangeListener onColorChangeListener)
    {
        this.onColorChangeListener = onColorChangeListener;
    }

    public void setDuration(long duration)
    {
        this.duration = duration;
    }

    private long duration = 1000;

    public static ColorAnimatorUtils newInstance()
    {
        ColorAnimatorUtils mColorAnimatorUtils = new ColorAnimatorUtils();
        return mColorAnimatorUtils;
    }

    private OnColorChangeListener onColorChangeListener;
    private OnProgressValue onProgressValue;

    /**
     * @param startColor 起始颜色
     * @param endColor   终止颜色
     * @param view       控件
     * @param attr       属性
     */
    public void startAnimator(final int startColor, final int endColor, final View view, final String attr)
    {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(duration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                float value = (float) animation.getAnimatedValue();
                int progressColor = getProgressColor(startColor, endColor, value);
                if (onColorChangeListener == null)
                {
                    try
                    {
                        final Method method = getMethod(view, attr);
                        if (method == null)
                        {
                            return;
                        }
                        method.invoke(view, progressColor);
                    } catch (IllegalAccessException e)
                    {
                        e.printStackTrace();
                    } catch (InvocationTargetException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    onColorChangeListener.colorChange(progressColor);
                }
            }
        });
        valueAnimator.start();
    }


    private int getProgressColor(int startColor, int endColor, float progress)
    {
        int red = getValue(startColor / 0xFFFF, endColor / 0xFFFF, progress);
        int green = getValue((startColor / 0xFF) % 0xFF, (endColor / 0xFF) % 0xFF, progress);
        int blue = getValue(startColor % 0xFFFF, endColor % 0xFFFF, progress);
        return red * 0xFFFF + green * 0xFF + blue;
    }

    private int getValue(int start, int end, float progress)
    {
        if (onProgressValue == null)
        {
            return start + (int) ((end - start) * progress);
        }
        else
        {
            return onProgressValue.getValue(start, end, progress);
        }
    }

    private Method getMethod(View view, String attr)
    {
        final Class aClass = view.getClass();
        Method method = null;
        try
        {
            method = aClass.getMethod("set" + attr, Integer.TYPE);
        } catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        return method;
    }

    /**
     * 颜色改变的监听
     */
    public interface OnColorChangeListener
    {
        void colorChange(int color);
    }

    /**
     * 在RGB三维上颜色值随进度改变的方法
     */
    public interface OnProgressValue
    {
        int getValue(int start,int end,float progress);
    }
}
