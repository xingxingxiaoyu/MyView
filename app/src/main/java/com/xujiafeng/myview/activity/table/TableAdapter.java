package com.xujiafeng.myview.activity.table;

import android.view.View;
import android.view.ViewGroup;


/**
 * 描述信息：
 *
 * @author xuyu
 * @date 2020/8/7
 */
public abstract class TableAdapter {

    public abstract ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindViewHolder(ViewHolder holder, int xPosition, int yPosition);

    public abstract int getXItemCount();

    public abstract int getYItemCount();

    static class ViewHolder {
        public final View itemView;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
        }
    }
}
