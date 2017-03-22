package com.xuyu.myview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xuyu.myview.SideSlipe.FourthActivity;
import com.xuyu.myview.animator_view.FifthActivity;
import com.xuyu.myview.automaticball.FirstActivity;
import com.xuyu.myview.identifying_code.ThirdActivity;
import com.xuyu.myview.clock.SecondActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOnClick();
    }

    private void setOnClick()
    {
        findViewById(R.id.button_automatic_ball).setOnClickListener(this);
        findViewById(R.id.button_clock).setOnClickListener(this);
        findViewById(R.id.button_identifying_code).setOnClickListener(this);
        findViewById(R.id.button_side_menu).setOnClickListener(this);
        findViewById(R.id.button_animator).setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent();
        switch(v.getId())
        {
            case R.id.button_automatic_ball:
                intent.setClass(this, FirstActivity.class);
                break;
            case R.id.button_clock:
                intent.setClass(this, SecondActivity.class);
                break;
            case R.id.button_identifying_code:
                intent.setClass(this, ThirdActivity.class);
                break;
            case R.id.button_side_menu:
                intent.setClass(this, FourthActivity.class);
                break;
            case R.id.button_animator:
                intent.setClass(this, FifthActivity.class);
                break;
        }
        startActivity(intent);
    }
}
