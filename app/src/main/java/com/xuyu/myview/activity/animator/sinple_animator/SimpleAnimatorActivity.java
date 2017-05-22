package com.xuyu.myview.activity.animator.sinple_animator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xuyu.myview.R;

public class SimpleAnimatorActivity extends AppCompatActivity
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
