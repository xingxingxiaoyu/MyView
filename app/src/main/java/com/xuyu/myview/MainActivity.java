package com.xuyu.myview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xuyu.myview.activity.animator_view.FifthActivity;
import com.xuyu.myview.activity.automatic_ball.FirstActivity;
import com.xuyu.myview.activity.bezier_curve.SeventhActivity;
import com.xuyu.myview.activity.bitmap.NinthActivity;
import com.xuyu.myview.activity.broken_line.TenthActivity;
import com.xuyu.myview.activity.clock.SecondActivity;
import com.xuyu.myview.activity.ferris_wheel.SixthActivity;
import com.xuyu.myview.activity.identifying_code.ThirdActivity;
import com.xuyu.myview.activity.pay_password.EighthActivity;
import com.xuyu.myview.activity.side_slipe.FourthActivity;

/**
 * Created by Administrator on 2017/5/19.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View v)
    {
        Intent intent = new Intent();
        switch (v.getId())
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
            case R.id.button_ferris_wheel:
                intent.setClass(this, SixthActivity.class);
                break;
            case R.id.button_bezier_curver:
                intent.setClass(this, SeventhActivity.class);
                break;
            case R.id.button_pay_password:
                intent.setClass(this, EighthActivity.class);
                break;
            case R.id.button_bitmap:
                intent.setClass(this, NinthActivity.class);
                break;
            case R.id.button_broken_line:
                intent.setClass(this, TenthActivity.class);
                break;
        }
        startActivity(intent);
    }
}