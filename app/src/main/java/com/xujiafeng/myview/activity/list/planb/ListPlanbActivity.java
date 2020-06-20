package com.xujiafeng.myview.activity.list.planb;

import androidx.annotation.NonNull;
import com.xujiafeng.myview.base.BaseActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Path;
import android.os.Bundle;
import android.util.Log;

import com.xujiafeng.myview.R;


public class ListPlanbActivity extends BaseActivity {
    public static final String TAG = "ListPlanbActivity";
    private PathView mPathView;
    private Path path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_planb);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PathPlanbAdapter(this));


        int screenWidthDp = getResources().getConfiguration().screenWidthDp;
        final int screenHeightDp = getResources().getConfiguration().screenHeightDp;
        final float scale = getResources().getDisplayMetrics().density;
        int screenWidth = (int) (screenWidthDp * scale + 0.5f);
        mPathView = findViewById(R.id.path_view);
        path = new Path();
        path.moveTo(screenWidth / 3, 0);
        for (int i = 0; i < 50; i++) {
            path.lineTo(screenWidth / 3, 250 + 300 * i * 2);
            path.lineTo(screenWidth / 3 * 2, 250 + 300 * i * 2);
            path.lineTo(screenWidth / 3 * 2, 300 + 300 * i * 2);

            path.lineTo(screenWidth / 3 * 2, 250 + 300 * (i * 2 + 1));
            path.lineTo(screenWidth / 3, 250 + 300 * (i * 2 + 1));
            path.lineTo(screenWidth / 3, 300 + 300 * (i * 2 + 1));

        }
        mPathView.setPath(path);
        final int max = 10000;
        mPathView.setMax(max);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int scrollY = 0;

            long time = System.currentTimeMillis();

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                path.offset(dx, -dy);
                mPathView.setPath(path);
                scrollY += dy;
                int progress = (int) (scrollY / (300 * 100 - screenHeightDp * scale) * max);
                mPathView.setProgress(progress);
                Log.e(TAG, "onScrolled: scrollY " + scrollY);
                Log.e(TAG, "onScrolled: progress " + progress);
                Log.e(TAG, "onScrolled: progress " + progress);
                long currentTimeMillis = System.currentTimeMillis();
                int dtime = (int) (currentTimeMillis - time);
                Log.e(TAG, "onScrolled: dtime " + dtime + " dy " + dy);

                mPathView.setAnimationSpeed(dy / dtime);
                time = currentTimeMillis;


            }
        });

    }
}
