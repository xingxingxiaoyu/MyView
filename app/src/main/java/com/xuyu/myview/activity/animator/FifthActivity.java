package com.xuyu.myview.activity.animator;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xuyu.myview.R;
import com.xuyu.myview.activity.animator.person_animator.PersonAnimatorActivity;
import com.xuyu.myview.activity.animator.sinple_animator.SimpleAnimatorActivity;

public class FifthActivity extends AppCompatActivity
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
                intent.setClass(this, SimpleAnimatorActivity.class);
                break;
        }
        startActivity(intent);
    }
}
