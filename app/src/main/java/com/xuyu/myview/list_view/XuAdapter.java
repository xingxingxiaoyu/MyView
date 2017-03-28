package com.xuyu.myview.list_view;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by: xuyu
 * <p>
 * on: 2017/3/28.
 * <p>
 * 描述：自定义ListView的适配器
 */
public class XuAdapter<T>
{
    /**
     * 实体类的集合
     */
    private List<T> mList;
    /**
     * 上下文
     */
    private Context mContext;
    private LayoutInflater mInflater;

    public XuAdapter(Context context, List<T> list)
    {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<T> getList()
    {
        return mList;
    }

    public void setList(List<T> list)
    {
        mList = list;
    }




}
