package com.sxhxjy.roadmonitor.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.entity.Monitor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.monitor_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
        holder.title.setText(mList.get(position).name);
        if (mList.get(position).cstate == 0) {
            holder.status.setText("异常");
            holder.status.setBackgroundResource(R.drawable.round_rectangle_alert);
        } else {
            holder.status.setText("正常");
            holder.status.setBackgroundResource(R.drawable.round_rectangle_keycolor);
        }
        holder.location.setText(mList.get(position).priDict.code + mList.get(position).unit);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        holder.date.setText(sdf.format(new Date(mList.get(position).saveTime)) );
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    @Override
    public void onClick(View v) {
        int p = (int) v.getTag();

        Intent intent = new Intent();
        intent.putExtra("monitorId", mList.get(p).id);
//        intent.setClass(mFragment.getActivity(), ChartActivity.class);
        mFragment.startActivity(intent);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, location, date, status;
        ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
//            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            location = (TextView) itemView.findViewById(R.id.location);
            date = (TextView) itemView.findViewById(R.id.date);
//            status = (TextView) itemView.findViewById(R.id.status);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
