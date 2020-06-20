package com.xujiafeng.myview.activity.clock;

import com.xujiafeng.myview.base.BaseActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.xujiafeng.myview.R;

public class ClockActivity extends BaseActivity
{

    private SeekBar mSeekBarHour;
    private SeekBar mSeekBarSecond;
    private SeekBar mSeekBarMinute;
    private TextView mTextViewHour;
    private TextView mTextViewMinute;
    private TextView mTextViewSecond;
    private ClockView mClockView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mClockView = (ClockView) findViewById(R.id.clockView);
        mClockView.start();
        mClockView.setOnTimeChangeListener(new ClockView.OnTimeChangeListener()
        {
            @Override
            public void onTimeChange(int hour, int minute, int second)
            {
                mSeekBarHour.setProgress(hour);
                mTextViewHour.setText(String.valueOf(hour));
                mSeekBarMinute.setProgress(minute);
                mTextViewMinute.setText(String.valueOf(minute));
                mSeekBarSecond.setProgress(second);
                mTextViewSecond.setText(String.valueOf(second));
            }
        });

        mSeekBarHour = (SeekBar) findViewById(R.id.seekBar_hour);
        mSeekBarHour.setMax(12 - 1);
        mSeekBarMinute = (SeekBar) findViewById(R.id.seekBar_minute);
        mSeekBarMinute.setMax(60 - 1);
        mSeekBarSecond = (SeekBar) findViewById(R.id.seekBar_second);
        mSeekBarSecond.setMax(60 - 1);
        mTextViewHour = (TextView) findViewById(R.id.tv_hour);
        mTextViewMinute = (TextView) findViewById(R.id.tv_minute);
        mTextViewSecond = (TextView) findViewById(R.id.tv_second);

        mSeekBarHour.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                mClockView.setHour(progress);
                mTextViewHour.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                mClockView.stop();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                mClockView.start();
            }
        });
        mSeekBarMinute.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                mClockView.setMinute(progress);
                mTextViewMinute.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                mClockView.stop();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                mClockView.start();
            }
        });
        mSeekBarSecond.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                mClockView.setSecond(progress);
                mTextViewSecond.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                mClockView.stop();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                mClockView.start();
            }
        });

    }
}
