package com.xujiafeng.myview.activity.animator.sinple_animator;

import com.xujiafeng.myview.base.BaseActivity;
import android.os.Bundle;
import android.view.View;

import com.xujiafeng.myview.R;
import com.xujiafeng.myview.base.BaseActivity;

public class SimpleAnimatorActivity extends BaseActivity
{

    private SimpleAnimatorView mSimpleAnimatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_animator);
        mSimpleAnimatorView = (SimpleAnimatorView)findViewById(R.id.simple_animator_view);
        findViewById(R.id.button_start).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mSimpleAnimatorView.startAnimator();
            }
        });
    }
}
