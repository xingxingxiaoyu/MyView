package com.xuyu.myview.bitmap.bitmap_edit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.xuyu.myview.R;

public class BitmapEditActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener
{

    private SeekBar mSeekBar_1;
    private SeekBar mSeekBar_2;
    private SeekBar mSeekBar_3;
    private ImageView mImageView;
    private int saturation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_edit);

        mImageView = (ImageView) findViewById(R.id.image);
        initSeekBar();
    }

    private void initSeekBar()
    {
        mSeekBar_1 = (SeekBar) findViewById(R.id.seekbar_1);
        mSeekBar_2 = (SeekBar) findViewById(R.id.seekbar_2);
        mSeekBar_3 = (SeekBar) findViewById(R.id.seekbar_3);

        mSeekBar_1.setOnSeekBarChangeListener(this);
        mSeekBar_2.setOnSeekBarChangeListener(this);
        mSeekBar_3.setOnSeekBarChangeListener(this);

        mSeekBar_1.setMax(200);
        mSeekBar_1.setProgress(100);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
        switch (seekBar.getId())
        {
            case R.id.seekbar_1:
                saturation = mSeekBar_1.getProgress();
                break;
            case R.id.seekbar_2:
                break;
            case R.id.seekbar_3:
                break;
        }
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.psb);
        mImageView.setImageBitmap(BitmapUtil.resetBitmap(bitmap, saturation));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar)
    {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar)
    {

    }
}
