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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 2016/10/8
 *
 * @author Michael Zhao
 */

public class AlertDetailListAdapter extends RecyclerView.Adapter<AlertDetailListAdapter.ViewHolder> implements View.OnClickListener {
    private List<AlertData> mList;
    private BaseFragment mFragment;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);

    public AlertDetailListAdapter(BaseFragment fragment, ArrayList<AlertData> list) {
        mFragment = fragment;
        mList = list;
    }

    @Override
    public AlertDetailListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlertDetailListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_detail_list_item, parent, false));
    }


    @Override
    public void onBindViewHolder(AlertDetailListAdapter.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
        holder.location.setText(mList.get(position).getStationName());
        holder.content.setText(mList.get(position).getAlarmContent());
        holder.date.setText(simpleDateFormat.format(new Date(mList.get(position).getGenerationTime())));
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
        TextView title, location, date, content;
        ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
//            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            location = (TextView) itemView.findViewById(R.id.location);
            date = (TextView) itemView.findViewById(R.id.date);
            content = (TextView) itemView.findViewById(R.id.content);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
