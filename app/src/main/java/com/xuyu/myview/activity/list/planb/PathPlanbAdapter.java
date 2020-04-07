package com.xuyu.myview.activity.list.planb;

import android.content.Context;
import android.graphics.Path;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xuyu.myview.R;
import com.xuyu.myview.activity.list.PathView;


/**
 * 描述信息：
 *
 * @author xuyu
 * @date 2020/4/3
 */
public class PathPlanbAdapter extends RecyclerView.Adapter<PathPlanbAdapter.PathViewHolder> {
    private Context mContext;

    public PathPlanbAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public PathViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = new View(mContext);
        view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        if (viewType == 0) {
            view.setBackgroundColor(0xff134334);
        } else {
            view.setBackgroundColor(0xff752397);
        }
        return new PathViewHolder(view);
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
