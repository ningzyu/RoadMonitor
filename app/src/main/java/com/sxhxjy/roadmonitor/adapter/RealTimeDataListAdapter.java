package com.sxhxjy.roadmonitor.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.entity.RealTimeData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 2016/9/19
 *
 * @author Michael Zhao
 */
public class RealTimeDataListAdapter extends RecyclerView.Adapter<RealTimeDataListAdapter.ViewHolder> implements View.OnClickListener {
    private List<RealTimeData> mList;
    private BaseFragment mFragment;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);

    public RealTimeDataListAdapter(BaseFragment fragment, ArrayList<RealTimeData> list) {
        mList = list;
        mFragment = fragment;
    }


    @Override
    public void onClick(View v) {
        int p = (int) v.getTag();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.real_time_list_item, parent, false));    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);

        holder.location.setText(mList.get(position).getName());

        holder.date.setText(sdf.format(new Date(mList.get(position).getSaveTime())) );
        holder.value.setText(mList.get(position).getXColName() + ": " + mList.get(position).getX() + mList.get(position).getTypeUnit());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, location, date, value;
        ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
//            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            location = (TextView) itemView.findViewById(R.id.location);
            date = (TextView) itemView.findViewById(R.id.date);
            value = (TextView) itemView.findViewById(R.id.value);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
