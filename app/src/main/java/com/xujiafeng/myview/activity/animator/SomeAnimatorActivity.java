package com.xujiafeng.myview.activity.animator;

import android.content.Intent;
import com.xujiafeng.myview.base.BaseActivity;
import android.os.Bundle;
import android.view.View;

import com.xujiafeng.myview.R;
import com.xujiafeng.myview.activity.animator.person_animator.PersonAnimatorActivity;
import com.xujiafeng.myview.activity.animator.sinple_animator.SimpleAnimatorActivity;

public class SomeAnimatorActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
    }

    public void onClick(View view)
    {
        Intent intent = new Intent();
        switch(view.getId())
        {
            case R.id.tv_person:
                intent.setClass(this, PersonAnimatorActivity.class);
                break;
            case R.id.tv_simple:
                intent.setClass(this, com.xujiafeng.myview.activity.animator.sinple_animator.SimpleAnimatorActivity.class);
                break;
        }
        startActivity(intent);
    }
}
