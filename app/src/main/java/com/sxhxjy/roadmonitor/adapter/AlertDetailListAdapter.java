package com.sxhxjy.roadmonitor.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.entity.AlertData;

import java.util.ArrayList;
import java.util.List;

/**
 * 2016/10/8
 *
 * @author Michael Zhao
 */

public class AlertDetailListAdapter extends RecyclerView.Adapter<AlertListAdapter.ViewHolder> implements View.OnClickListener {
    private List<AlertData> mList;
    private BaseFragment mFragment;

    public AlertDetailListAdapter(BaseFragment fragment, ArrayList<AlertData> list) {
        mFragment = fragment;
        mList = list;
    }

    @Override
    public AlertListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlertListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_detail_list_item, parent, false));
    }


    @Override
    public void onBindViewHolder(AlertListAdapter.ViewHolder holder, int position) {
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



    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, location, date, status;
        ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
//            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            location = (TextView) itemView.findViewById(R.id.location);
            date = (TextView) itemView.findViewById(R.id.date);
            status = (TextView) itemView.findViewById(R.id.level);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
