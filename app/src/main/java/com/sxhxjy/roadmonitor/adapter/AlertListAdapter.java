package com.sxhxjy.roadmonitor.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.base.MySubscriber;
import com.sxhxjy.roadmonitor.base.UserManager;
import com.sxhxjy.roadmonitor.entity.AlertData;
import com.sxhxjy.roadmonitor.ui.main.AlertDetailActivity;
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
    private AlertDialog alertDialog;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);

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
        holder.num.setText(mList.get(position).getNum() + "次");

        if (mList.get(position).getConfirmInfo() == null) {
            holder.isConfirmed.setText("未确认");
        } else {
            holder.isConfirmed.setText("已确认");
        }
        holder.location.setText(mList.get(position).getAlarmContent());
        holder.reason.setText(mList.get(position).getGenerationReason());
        holder.date.setText(sdf.format(new Date(mList.get(position).getStime())) + "--" + sdf.format(new Date(mList.get(position).getEtime())));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        final int p = (int) v.getTag();

        alertDialog = new AlertDialog.Builder(mFragment.getActivity()).setTitle("确定警告信息").setView(mFragment.getActivity().getLayoutInflater().inflate(R.layout.dialog_view_alert, null)).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mFragment.getMessage(mFragment.getHttpService().confirmAlertMsg(mList.get(p).getId(), UserManager.getUID(), ((EditText) alertDialog.findViewById(R.id.editText)).getText().toString(), mList.get(p).getStime()+"", mList.get(p).getEtime()+""), new MySubscriber<Object>() {
                    @Override
                    public void onNext(Object o) {
                        mFragment.showToastMsg("确定警告信息成功！");
                        mList.get(p).setConfirmInfo("");
                        notifyDataSetChanged();
                    }

                    @Override
                    protected void onMyNext(Object o) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                });

            }
        }).setNegativeButton("取消", null).setNeutralButton("查看详情", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bundle b = new Bundle();
                b.putString("start", mList.get(p).getStime()+"");
                b.putString("end", mList.get(p).getEtime()+"");
                b.putString("id", mList.get(p).getId());
                ActivityUtil.startActivityForResult(mFragment.getActivity(), AlertDetailActivity.class, b, 100);
            }
        }).create();
        alertDialog.show();
        ((EditText) alertDialog.findViewById(R.id.editText)).setText(mList.get(p).getConfirmInfo());

    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView reason, location, date, status, num, isConfirmed, level;
        ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
//            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            location = (TextView) itemView.findViewById(R.id.location);
            date = (TextView) itemView.findViewById(R.id.date);
            reason = (TextView) itemView.findViewById(R.id.reason);
            num = (TextView) itemView.findViewById(R.id.num);
            isConfirmed = (TextView) itemView.findViewById(R.id.is_confirmed);
            level = (TextView) itemView.findViewById(R.id.level);
        }
    }
}
