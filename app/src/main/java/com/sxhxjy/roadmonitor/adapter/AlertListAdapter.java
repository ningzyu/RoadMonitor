package com.sxhxjy.roadmonitor.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.base.CacheManager;
import com.sxhxjy.roadmonitor.entity.AlertData;
import com.sxhxjy.roadmonitor.entity.Monitor;
import com.sxhxjy.roadmonitor.ui.chart.ChartActivity;
import com.sxhxjy.roadmonitor.ui.main.AlertDetailActivity;
import com.sxhxjy.roadmonitor.ui.main.AlertFragment;
import com.sxhxjy.roadmonitor.ui.main.LoginActivity;
import com.sxhxjy.roadmonitor.ui.main.MainActivity;
import com.sxhxjy.roadmonitor.util.ActivityUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 2016/9/26
 *
 * @author Michael Zhao
 */
public class AlertListAdapter extends RecyclerView.Adapter<AlertListAdapter.ViewHolder> implements View.OnClickListener {
    private List<AlertData> mList;
    private BaseFragment mFragment;
    public AlertListAdapter(BaseFragment fragment, ArrayList<AlertData> list) {
        mFragment = fragment;
        mList = list;
    }
    @Override
    public AlertListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlertListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_list_item, parent, false));
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

        new AlertDialog.Builder(mFragment.getActivity()).setTitle("确定警告信息").setView(mFragment.getActivity().getLayoutInflater().inflate(R.layout.dialog_view_alert, null)).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("取消", null).setNeutralButton("查看详情", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityUtil.startActivityForResult(mFragment.getActivity(), AlertDetailActivity.class);
            }
        }).show();


    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, location, date, status;
        ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
//            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            location = (TextView) itemView.findViewById(R.id.location);
            date = (TextView) itemView.findViewById(R.id.date);
            status = (TextView) itemView.findViewById(R.id.status);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
