package com.xuyu.myview.list_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by: xuyu
 * <p>
 * on: 2017/3/28.
 * <p>
 * 描述：自定义ListView
 */
public class XuListView extends ViewGroup
{
    /**
     * 适配器
     */
    private XuAdapter mAdapter;

    public void setAdapter(XuAdapter adapter)
    {
        mAdapter = adapter;
    }

    public XuListView(Context context)
    {
        this(context, null);
    }

    public XuListView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public XuListView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {

    }
}
