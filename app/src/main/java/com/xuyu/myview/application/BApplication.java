package com.xuyu.myview.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/5/19.
 */

public class BApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        mContext = this;
    }

    static private Context mContext;

    static public Context getContext()
    {
        return mContext;
    }
}
