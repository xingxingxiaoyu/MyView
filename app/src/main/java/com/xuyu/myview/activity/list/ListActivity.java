package com.xuyu.myview.activity.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.xuyu.myview.R;

public class ListActivity extends AppCompatActivity {
    int i = 0;
    private PathView currentPathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
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
                Log.e("====", "" + dy);
                int childCount = recyclerView.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    PathView child = (PathView) recyclerView.getChildAt(i);
                    float line = (getResources().getConfiguration().screenHeightDp * scale + 0.5f) / 3;
                    Log.e("====", "i " + i + " child.getTop() " + child.getTop());
                    if (child.getTop() > line) {
                        if (child != currentPathView) {
                            if (currentPathView != null) {
                                currentPathView.setProgress(-1);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    currentPathView.setZ(0);
                                }
                            }
                            currentPathView = child;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                currentPathView.setZ(1);
                            }
                        }
                        int progress = (int) ((child.getTop() - line) / child.getHeight() * 100);
                        Log.e("====", "progress " + progress);
                        child.setProgress(100 - progress);
                        return;
                    }
                }

            }
        });
    }
}
