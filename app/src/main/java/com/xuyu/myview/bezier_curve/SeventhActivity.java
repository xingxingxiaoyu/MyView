package com.xuyu.myview.bezier_curve;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.xuyu.myview.R;

public class SeventhActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh);
        BezierCurveLine bezierCurveLine = new BezierCurveLine(this);
        bezierCurveLine.setPoint(new Point(0,100),new Point(400,100),new Point(20,100),new Point(30,100));
        addContentView(bezierCurveLine,
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));

        BezierCurveLine bezierCurveLine2 = new BezierCurveLine(this);
        bezierCurveLine2.setPaintColor(Color.BLUE);
        bezierCurveLine2.setPoint(new Point(0,200),new Point(400,200),new Point(100,20),new Point(30,400));
        addContentView(bezierCurveLine2,
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));

        BezierCurveLine bezierCurveLine3 = new BezierCurveLine(this);
        bezierCurveLine3.setPaintColor(Color.GREEN);
        bezierCurveLine3.setPoint(new Point(0,300),new Point(400,400),new Point(100,300),new Point(600,400));
        addContentView(bezierCurveLine3,
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
