package com.xuyu.myview.identifying_code;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.xuyu.myview.R;
import com.xuyu.myview.anim.ColorAnimatorUtils;

public class ThirdActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        final ImageView imageview = (ImageView) findViewById(R.id.image1);
        final ImageView imageview2 = (ImageView) findViewById(R.id.image2);
        imageview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ColorAnimatorUtils colorAnimatorUtils = ColorAnimatorUtils.newInstance();
                colorAnimatorUtils.setDuration(2000);
                colorAnimatorUtils.setOnColorChangeListener(new ColorAnimatorUtils.OnColorChangeListener()
                {
                    @Override
                    public void colorChange(int color)
                    {
                        imageview.setImageDrawable(new ColorDrawable(color));
                    }
                });
                colorAnimatorUtils.startAnimator(Color.BLUE,Color.WHITE,v,null);
            }
        });
        imageview2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ColorAnimatorUtils.newInstance().startAnimator(Color.RED,Color.BLACK,v,"BackgroundColor");
            }
        });
    }
}
