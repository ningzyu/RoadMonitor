package com.sxhxjy.roadmonitor.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.entity.SimpleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 2016/9/26
 *
 * @author Michael Zhao
 */
public class SimpleListAdapter extends RecyclerView.Adapter<SimpleListAdapter.ViewHolder> {
    private BaseFragment mFragment;
    private List<SimpleItem> mList = new ArrayList<>();
    private RecyclerView mFilterList;
    private View.OnClickListener mListener;


    public SimpleListAdapter(BaseFragment fragment, List<SimpleItem> list) {
        mFragment = fragment;
        mList = list;
    }

    @Override
    public SimpleListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SimpleListAdapter.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(mListener);
        holder.itemView.setTag(position);
        holder.title.setText(mList.get(position).getTitle());
        if (mList.get(position).isChecked()) {
            holder.title.setTextColor(mFragment.getResources().getColor(R.color.colorPrimary));
        } else {
            holder.title.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setListener(View.OnClickListener mListener) {
        this.mListener = mListener;
    }

    public void setFilterList(RecyclerView mFilterList) {
        this.mFilterList = mFilterList;
    }

    public void setListData(List<SimpleItem> list) {
        this.mList = list;
    }

    public List<SimpleItem> getListData() {
        return mList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView arrow;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            arrow = (ImageView) itemView.findViewById(R.id.right_arrow);
            arrow.setColorFilter(itemView.getResources().getColor(R.color.default_color));
        }
    }
}



