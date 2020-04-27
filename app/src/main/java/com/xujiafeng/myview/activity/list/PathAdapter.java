package com.xujiafeng.myview.activity.list;

import android.content.Context;
import android.graphics.Path;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xujiafeng.myview.R;


/**
 * 描述信息：
 *
 * @author xuyu
 * @date 2020/4/3
 */
public class PathAdapter extends RecyclerView.Adapter<PathAdapter.PathViewHolder> {
    private Context mContext;
    private Path mLeftPath;
    private Path mRightPath;

    public PathAdapter(Context context, int width) {
        mContext = context;
        initLeftPath(width);
        initRightPath(width);
    }

    @NonNull
    @Override
    public PathViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PathView pathView = new PathView(mContext);
        pathView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        if (viewType == 0) {
            pathView.setPath(mLeftPath);
            pathView.setBackgroundColor(0xff134334);
        } else {
            pathView.setPath(mRightPath);
            pathView.setBackgroundColor(0xff752397);
        }
        pathView.setImage(R.mipmap.airplane2);
        return new PathViewHolder(pathView);
    }

    private void initLeftPath(int width) {
        mLeftPath = new Path();
        mLeftPath.moveTo(width / 3, 0);
        mLeftPath.lineTo(width / 3, 250);
        mLeftPath.lineTo(width / 3 * 2, 250);
        mLeftPath.lineTo(width / 3 * 2, 300);
    }

    private void initRightPath(int width) {
        mRightPath = new Path();
        mRightPath.moveTo(width / 3 * 2, 0);
        mRightPath.lineTo(width / 3 * 2, 250);
        mRightPath.lineTo(width / 3, 250);
        mRightPath.lineTo(width / 3, 300);
    }

    @Override
    public void onBindViewHolder(@NonNull PathViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    class PathViewHolder extends RecyclerView.ViewHolder {

        public PathViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
