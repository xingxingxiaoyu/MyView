package com.xuyu.myview.identifying_code;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuyu.myview.R;


/**
 * Created by Xuyu on 2017/2/6.
 */
public class IdentifyingCodeLayout extends LinearLayout implements View.OnClickListener
{

    private TextView tvTime;
    private LinearLayout layout;
    private TextView tvGetCode;
    private boolean isOverTime;
    private int time;
    public static final int DEFAULT_TIME=60;
    private EditText etCode;

    public void setTime(int time)
    {
        if (time<0||time>3000)
        {
            return;
        }
        this.time = time;
    }


    public IdentifyingCodeLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.view_identifying_code, this);
        tvTime = (TextView)view.findViewById(R.id.tv_time);
        layout = (LinearLayout)view.findViewById(R.id.layout_time);
        tvGetCode = (TextView)view.findViewById(R.id.tv_get_code);
        tvGetCode.setOnClickListener(this);
        etCode = (EditText)view.findViewById(R.id.et_code);
    }

    public String getCode()
    {
        return etCode.getText().toString();
    }
    @Override
    public void onClick(View v)
    {
        //隐藏获取验证码
        tvGetCode.setVisibility(View.GONE);
        //显示倒计时按钮
        layout.setVisibility(View.VISIBLE);
        //未设置时间，使用默认
        if (time==0)
        {
            time=DEFAULT_TIME;
        }
        tvTime.setText(String.valueOf(time));
        new Thread(new Runnable()
        {
            public void run()
            {
                while (time>0)
                {
                    handler.sendEmptyMessage(0);
                    time--;
                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                setOverTime(true);
            }
        }).start();
    }
    private Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            tvTime.setText(String.valueOf(time));
        }
    };
    public boolean isOverTime()
    {
        return isOverTime;
    }

    public void setOverTime(boolean overTime)
    {
        isOverTime = overTime;
    }

}
