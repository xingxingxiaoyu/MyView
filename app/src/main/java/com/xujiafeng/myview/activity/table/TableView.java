package com.xujiafeng.myview.activity.table;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

/**
 * 描述信息：
 *
 * @author xuyu
 * @date 2020/8/7
 */
class TableView extends ViewGroup {
    public TableView(Context context) {
        super(context);
    }

    public TableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TableView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
