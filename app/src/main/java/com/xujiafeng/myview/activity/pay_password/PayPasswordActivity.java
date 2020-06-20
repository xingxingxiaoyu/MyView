package com.xujiafeng.myview.activity.pay_password;

import com.xujiafeng.myview.base.BaseActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xujiafeng.myview.R;

public class PayPasswordActivity extends BaseActivity
{
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth);
        mTextView = (TextView)findViewById(R.id.textview);
        findViewById(R.id.button_show_dialog).setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                new PayPasswordTool().showPayPassworsDialog(PayPasswordActivity.this, new PayPasswordTool.InputFinishListener()
                {
                    @Override
                    public void inputFinish(String password)
                    {
                        mTextView.setText(password);
                    }
                });
            }
        });
    }
}
