package com.xujiafeng.myview.activity.bitmap.identify;

import com.xujiafeng.myview.base.BaseActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xujiafeng.myview.R;

public class IdentifyColorActivity extends BaseActivity {

    private int level = 1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_color);
        final TextView text_level = findViewById(R.id.text_level);
        final IdentifyColorView game_view = findViewById(R.id.game_view);
        game_view.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (game_view.isTrue(event.getX(), event.getY())) {
                    level++;
                    game_view.setLevel(level);
                    text_level.setText("level:" + level);
                    if (level == 5) {
                        Toast.makeText(IdentifyColorActivity.this, "不错啊！", Toast.LENGTH_SHORT).show();
                    } else if (level == 10) {
                        Toast.makeText(IdentifyColorActivity.this, "还可以！", Toast.LENGTH_SHORT).show();
                    } else if (level == 15) {
                        Toast.makeText(IdentifyColorActivity.this, "好厉害啊！", Toast.LENGTH_SHORT).show();
                    } else if (level == 20) {
                        Toast.makeText(IdentifyColorActivity.this, "加油，突破极限！", Toast.LENGTH_SHORT).show();
                    } else if (level == 30) {
                        Toast.makeText(IdentifyColorActivity.this, "无敌了啊！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    level = 1;
                    game_view.setLevel(level);
                    text_level.setText("level:" + level);
                }
                return false;
            }
        });
    }
}
