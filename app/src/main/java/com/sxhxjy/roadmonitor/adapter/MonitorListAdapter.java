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
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.entity.Monitor;
import com.sxhxjy.roadmonitor.ui.chart.ChartActivity;
import com.sxhxjy.roadmonitor.util.ActivityUtil;

import java.util.List;

/**
 * 2016/9/10
 *
 * @author Michael Zhao
 */
public class MonitorListAdapter extends RecyclerView.Adapter<MonitorListAdapter.ViewHolder> implements View.OnClickListener {


    private List<Monitor> mList;
    private BaseFragment mFragment;
    public MonitorListAdapter(BaseFragment fragment, List<Monitor> list) {
        mList = list;
        mFragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.monitor_list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    @Override
    public void onClick(View v) {
        int p = (int) v.getTag();

        Bundle b = new Bundle();
//        b.putSerializable("item", mList.get(p));
        Intent intent = new Intent();
        intent.setClass(mFragment.getActivity(), ChartActivity.class);
        mFragment.startActivity(intent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, location, date, status;
        ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            location = (TextView) itemView.findViewById(R.id.location);
            date = (TextView) itemView.findViewById(R.id.date);
            status = (TextView) itemView.findViewById(R.id.status);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
