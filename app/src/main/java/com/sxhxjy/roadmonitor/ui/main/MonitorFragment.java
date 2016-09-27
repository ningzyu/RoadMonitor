package com.sxhxjy.roadmonitor.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.SimpleListAdapter;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.entity.SimpleItem;
import com.sxhxjy.roadmonitor.util.ActivityUtil;
import com.sxhxjy.roadmonitor.view.MyPopupWindow;

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
    private List<SimpleItem> mListLeft = new ArrayList<>();
    private List<SimpleItem> mListRight = new ArrayList<>();
    private SimpleListAdapter mAdapter;
    private TextView mFilterTitleLeft, mFilterTitleRight;
    private RecyclerView mFilterList;
    private MyPopupWindow myPopupWindow;

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

        mFilterTitleLeft = (TextView) view.findViewById(R.id.filter_left);
        mFilterTitleRight = (TextView) view.findViewById(R.id.filter_right);

        mListLeft.add(new SimpleItem("", "南中环桥", false));
        mListLeft.add(new SimpleItem("", "南中环桥", false));
        mListLeft.add(new SimpleItem("", "南中环桥", false));
        mListRight.add(new SimpleItem("", "最近一周", false));
        mListRight.add(new SimpleItem("", "最近一周", false));
        mListRight.add(new SimpleItem("", "最近一周", false));

        mFilterTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setListData(mListLeft);
                mAdapter.notifyDataSetChanged();

                if (mFilterList.getVisibility() == View.GONE)
                    mFilterList.setVisibility(View.VISIBLE);
                else
                    mFilterList.setVisibility(View.GONE);            }
        });

        mFilterTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setListData(mListRight);
                mAdapter.notifyDataSetChanged();

                if (mFilterList.getVisibility() == View.GONE)
                    mFilterList.setVisibility(View.VISIBLE);
                else
                    mFilterList.setVisibility(View.GONE);

            }
        });

        mFilterList = (RecyclerView) view.findViewById(R.id.filter_list);
        mFilterList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SimpleListAdapter(this, mListLeft);
        mFilterList.setAdapter(mAdapter);
        mAdapter.setFilterList(mFilterList);


        mToolbar.inflateMenu(R.menu.filter_right);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                myPopupWindow.show();
                return true;
            }
        });

        myPopupWindow = new MyPopupWindow((BaseActivity) getActivity(), R.layout.popup_window_right);

    }
}
