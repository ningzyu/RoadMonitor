package com.sxhxjy.roadmonitor.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.SimpleListAdapter;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.entity.SimpleItem;
import com.sxhxjy.roadmonitor.util.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 2016/9/26
 *
 * @author Michael Zhao
 */

public class MonitorFragment extends BaseFragment {

    private String stationId = "40288164568be6a401568bf1e5100000";
    private TextView mTextViewCenter;
    private ImageView mImageViewLeft;
    private List<SimpleItem> mList = new ArrayList<>();
    private SimpleListAdapter mAdapter;
    private TextView mFilterLeft, mFilterRight;
    private RecyclerView mFilterList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.monitor_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolBar(getView(), getArguments().getString("stationName"), false);
        mTextViewCenter = (TextView) getView().findViewById(R.id.toolbar_title);
        mImageViewLeft = (ImageView) getView().findViewById(R.id.toolbar_left);
        mImageViewLeft.setVisibility(View.VISIBLE);
        mImageViewLeft.setImageResource(R.mipmap.history);
        mImageViewLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.startActivityForResult(getActivity(), RealTimeDataListActivity.class);

            }
        });
        mTextViewCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StationListActivity.class);
                startActivityForResult(intent, StationListActivity.REQUEST_CODE);
            }
        });

        mFilterLeft = (TextView) view.findViewById(R.id.filter_left);
        mFilterRight = (TextView) view.findViewById(R.id.filter_right);

        mFilterLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();
                mList.add(new SimpleItem("", "最近一周", false));
                mList.add(new SimpleItem("", "最近一周", false));
                mList.add(new SimpleItem("", "最近一周", false));
                mAdapter.notifyDataSetChanged();
                mFilterList.setVisibility(View.VISIBLE);
            }
        });

        mFilterRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();
                mList.add(new SimpleItem("", "南中环桥", false));
                mList.add(new SimpleItem("", "南中环桥", false));
                mList.add(new SimpleItem("", "南中环桥", false));
                mAdapter.notifyDataSetChanged();
                mFilterList.setVisibility(View.VISIBLE);

            }
        });

        mFilterList = (RecyclerView) view.findViewById(R.id.filter_list);
        mFilterList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SimpleListAdapter(this, mList);
        mFilterList.setAdapter(mAdapter);
        mAdapter.setFilterList(mFilterList);
    }
}
