package com.xujiafeng.myview.activity.side_slipe;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by Xuyu on 2017/2/4.
 */
public class SideMenuLayout extends HorizontalScrollView
{
    public static final int DEFAULT_MARGIN_RIGHT = 100;
    private int screenWidth;
    private int menuWidth;
    private LinearLayout menuLayout;
    private FrameLayout mainLayout;

    public static final int CURRENT_LAYOUT_MENU = 0;
    public static final int CURRENT_LAYOUT_MAIN = 1;
    private int currentLayout = CURRENT_LAYOUT_MAIN;

    public void setMenuMarginRight(int menuMarginRight)
    {
        this.menuMarginRight = menuMarginRight;
    }

    private int menuMarginRight = DEFAULT_MARGIN_RIGHT;

    public SideMenuLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    private void init(Context context)
    {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        menuWidth = screenWidth - menuMarginRight;
        setHorizontalScrollBarEnabled(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        LinearLayout layout = (LinearLayout) getChildAt(0);
        menuLayout = (LinearLayout) layout.getChildAt(0);
        mainLayout = (FrameLayout) layout.getChildAt(1);
        menuLayout.getLayoutParams().width = menuWidth;
        mainLayout.getLayoutParams().width = screenWidth;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        super.onLayout(changed, l, t, r, b);
        smoothScrollTo(menuWidth, 0);
        currentLayout = CURRENT_LAYOUT_MAIN;
    }


    float startX = 0F;
    float endX = 0F;
    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {

        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                break;
            case MotionEvent.ACTION_UP:
                endX = ev.getX();
                float d = endX - startX;
                switch (currentLayout)
                {
                    case CURRENT_LAYOUT_MAIN:

                        if (d > screenWidth / 5)
                        {
                            smoothScrollTo(0, 0);
                            currentLayout = CURRENT_LAYOUT_MENU;
                        } else
                        {
                            smoothScrollTo(menuWidth, 0);
                            currentLayout = CURRENT_LAYOUT_MAIN;
                        }
                        return true;
                    case CURRENT_LAYOUT_MENU:
                        if (d < -screenWidth / 5)
                        {
                            smoothScrollTo(menuWidth, 0);
                            currentLayout = CURRENT_LAYOUT_MAIN;
                        } else
                        {
                            smoothScrollTo(0, 0);
                            currentLayout = CURRENT_LAYOUT_MENU;
                        }
                        return true;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 滑动监听
     *
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        super.onScrollChanged(l, t, oldl, oldt);
        //缩放比例
        float scale = (float) l / menuWidth;
        menuLayout.setScaleX(1 - 0.3F * scale);
        menuLayout.setScaleY(1 - 0.3F * scale);
        menuLayout.setAlpha(1 - 0.5F * scale);

        mainLayout.setScaleX(0.7F + 0.3F * scale);
        mainLayout.setScaleY(0.7F + 0.3F * scale);
        mainLayout.setTranslationX(-100F + 100F * scale);
    }
}
