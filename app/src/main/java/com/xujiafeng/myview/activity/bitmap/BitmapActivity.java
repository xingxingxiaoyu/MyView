package com.xujiafeng.myview.activity.bitmap;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xujiafeng.myview.R;
import com.xujiafeng.myview.activity.bitmap.bitmap_edit.BitmapEditActivity;
import com.xujiafeng.myview.activity.bitmap.identify.IdentifyColorActivity;

public class BitmapActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninth);
    }

    public void onClick(View view)
    {
        Intent intent = new Intent();
        switch(view.getId())
        {
            case R.id.button_bitmap_edit:
                intent.setClass(this, BitmapEditActivity.class);
                break;
            case R.id.button_identify_color:
                intent.setClass(this, IdentifyColorActivity.class);
                break;
        }
        startActivity(intent);
    }
}
