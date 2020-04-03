package com.xuyu.myview.activity.bitmap;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xuyu.myview.R;
import com.xuyu.myview.activity.bitmap.bitmap_edit.BitmapEditActivity;

public class NinthActivity extends AppCompatActivity
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
            case R.id.button:
                intent.setClass(this, BitmapEditActivity.class);
                break;
        }
        startActivity(intent);
    }
}
