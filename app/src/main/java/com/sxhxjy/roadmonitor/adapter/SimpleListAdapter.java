package com.sxhxjy.roadmonitor.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.entity.GroupTree;
import com.sxhxjy.roadmonitor.entity.SimpleItem;
import com.sxhxjy.roadmonitor.entity.Station;
import com.sxhxjy.roadmonitor.ui.chart.ChartActivity;
import com.sxhxjy.roadmonitor.ui.main.StationListActivity;
import com.sxhxjy.roadmonitor.util.ActivityUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 2016/9/18
 *
 * @author Michael Zhao
 */
public class SimpleListAdapter extends RecyclerView.Adapter<SimpleListAdapter.ViewHolder> implements View.OnClickListener {

    private List<GroupTree> data;
    private BaseFragment mFragment;
    private List<SimpleItem> mlist = new ArrayList<>();

    private List<GroupTree> currentGroup;
    public SimpleListAdapter(BaseFragment fragment, List<GroupTree> list) {
        this.data = list;
        mFragment = fragment;
    }

    public void foo() {
        if (data != null)
            for (GroupTree groupTree : data) {
                SimpleItem item = new SimpleItem();
                item.setTitle(groupTree.name);
                item.setId(groupTree.id);
                mlist.add(item);
            }
        currentGroup = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
        holder.title.setText(mlist.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    @Override
    public void onClick(View v) {
        int p = (int) v.getTag();
        if (currentGroup.get(p) != null) {
            mlist.clear();
        } else {

        }
        for (GroupTree childrenGroup : currentGroup.get(p).childrenGroup) {
            SimpleItem item = new SimpleItem();
            item.setTitle(childrenGroup.name);
            item.setId(childrenGroup.id);
            mlist.add(item);
        }
        currentGroup = currentGroup.get(p).childrenGroup;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, location, date, status;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
