package com.xuyu.myview.bezier_curve.bezier_show;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xuyu.myview.R;

public class BezierShowActivity extends AppCompatActivity
{

    private BezierShowView mBezierShowView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_show);
        mBezierShowView = (BezierShowView)findViewById(R.id.bezier_show_view);
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.button_start:
                mBezierShowView.start();
                break;
            case R.id.button_start2:
                mBezierShowView.start2();
                break;
        }
    }
}
