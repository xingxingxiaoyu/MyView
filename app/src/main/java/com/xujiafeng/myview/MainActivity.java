package com.xujiafeng.myview;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.xujiafeng.myview.activity.animator.SimpleAnimatorActivity;
import com.xujiafeng.myview.activity.automatic_ball.AutoBallActivity;
import com.xujiafeng.myview.activity.bezier_curve.BezierCurverActivity;
import com.xujiafeng.myview.activity.bitmap.BitmapActivity;
import com.xujiafeng.myview.activity.broken_line.BrokenLineActivity;
import com.xujiafeng.myview.activity.clock.ClockActivity;
import com.xujiafeng.myview.activity.ferris_wheel.FerrisWheelActivity;
import com.xujiafeng.myview.activity.identifying_code.CodeActivity;
import com.xujiafeng.myview.activity.list.ListActivity;
import com.xujiafeng.myview.activity.pay_password.PayPasswordActivity;
import com.xujiafeng.myview.activity.side_slipe.SideMenuActivity;
import com.xujiafeng.myview.activity.star.StarActivity;

/**
 * Created by Administrator on 2017/5/19.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.button_automatic_ball:
                intent.setClass(this, AutoBallActivity.class);
                break;
            case R.id.button_clock:
                intent.setClass(this, ClockActivity.class);
                break;
            case R.id.button_identifying_code:
                intent.setClass(this, CodeActivity.class);
                break;
            case R.id.button_side_menu:
                intent.setClass(this, SideMenuActivity.class);
                break;
            case R.id.button_animator:
                intent.setClass(this, SimpleAnimatorActivity.class);
                break;
            case R.id.button_ferris_wheel:
                intent.setClass(this, FerrisWheelActivity.class);
                break;
            case R.id.button_bezier_curver:
                intent.setClass(this, BezierCurverActivity.class);
                break;
            case R.id.button_pay_password:
                intent.setClass(this, PayPasswordActivity.class);
                break;
            case R.id.button_bitmap:
                intent.setClass(this, BitmapActivity.class);
                break;
            case R.id.button_broken_line:
                intent.setClass(this, BrokenLineActivity.class);
                break;
            case R.id.button_star:
                intent.setClass(this, StarActivity.class);
                break;
            case R.id.button_list:
                intent.setClass(this, ListActivity.class);
                break;
        }
        startActivity(intent);
    }
}