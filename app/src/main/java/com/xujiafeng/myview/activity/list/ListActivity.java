package com.xujiafeng.myview.activity.list;

import androidx.annotation.NonNull;
import com.xujiafeng.myview.base.BaseActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.xujiafeng.myview.R;
import com.xujiafeng.myview.activity.list.planb.ListPlanbActivity;

public class ListActivity extends BaseActivity {
    public static final String TAG = "ListActivity";
    private PathView currentPathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final PathView pathView = findViewById(R.id.path_view);
        Path path = new Path();
        path.addCircle(500, 800, 400, Path.Direction.CCW);
        pathView.setImage(R.mipmap.airplane2);
        pathView.setPath(path);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            private int i = 0;
            @Override
            public void run() {
                pathView.setProgress(i++ % 100);
                handler.postDelayed(this,50);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        int screenWidthDp = getResources().getConfiguration().screenWidthDp;
        final float scale = getResources().getDisplayMetrics().density;
        recyclerView.setAdapter(new PathAdapter(this, (int) (screenWidthDp * scale + 0.5f)));

        recyclerView.setClipChildren(false);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e(TAG, "" + dy);
                int childCount = recyclerView.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    PathView child = (PathView) recyclerView.getChildAt(i);
                    float line = (getResources().getConfiguration().screenHeightDp * scale + 0.5f) / 3;
                    Log.e(TAG, "i " + i + " child.getTop() " + child.getTop());
                    if (child.getTop() > line) {
                        int progress = (int) ((child.getTop() - line) / child.getHeight() * 100);
                        Log.e(TAG, "progress " + progress);
                        if (progress < 0 || progress > 100) {
                            Log.e(TAG, "onScrolled: error progress " + progress);
                        }
                        if (child != currentPathView) {
                            if (currentPathView != null) {
                                child.setProgress(100 - progress);

                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    currentPathView.setZ(0);
                                }
                                currentPathView.setProgress(-1);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    child.setZ(1);
                                }

                            } else {
                                child.setProgress(100 - progress);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    child.setZ(1);
                                }
                            }
                            currentPathView = child;
                        } else {
                            child.setProgress(100 - progress);
                        }
                        return;
                    }
                }

            }
        });
        findViewById(R.id.button_to_planb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, ListPlanbActivity.class));
            }
        });
    }
}
