package com.sxhxjy.roadmonitor.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.AlertListAdapter;
import com.sxhxjy.roadmonitor.adapter.MonitorListAdapter;
import com.sxhxjy.roadmonitor.adapter.SimpleListAdapter;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.base.BaseListFragment;
import com.sxhxjy.roadmonitor.base.HttpResponse;
import com.sxhxjy.roadmonitor.entity.AlertData;
import com.sxhxjy.roadmonitor.entity.SimpleItem;
import com.sxhxjy.roadmonitor.view.MyPopupWindow;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * 2016/9/19
 *
 * @author Michael Zhao
 */
public class AlertFragment extends BaseListFragment<AlertData> {
    private List<SimpleItem> mListLeft = new ArrayList<>();
    private List<SimpleItem> mListRight = new ArrayList<>();
    private SimpleListAdapter mAdapter;
    private TextView mFilterTitleLeft, mFilterTitleRight;
    private RecyclerView mFilterList;
    private MyPopupWindow myPopupWindow;


    @Override
    public Observable<HttpResponse<List<AlertData>>> getObservable() {
        return null;
    }

    @Override
    protected Class<AlertData> getItemClass() {
        return AlertData.class;
    }

    @Override
    protected void init() {
        initToolBar(getView(), "警告", false);
        getActivity().getLayoutInflater().inflate(R.layout.filter_title_alert, mAboveList);

        mFilterTitleLeft = (TextView) getView().findViewById(R.id.filter_left);
        mFilterTitleRight = (TextView) getView().findViewById(R.id.filter_right);

        mListLeft.add(new SimpleItem("", "一级", false));
        mListLeft.add(new SimpleItem("", "二级", false));
        mListLeft.add(new SimpleItem("", "三级", false));
        mListRight.add(new SimpleItem("", "最近一天", false));
        mListRight.add(new SimpleItem("", "最近一周", false));
        mListRight.add(new SimpleItem("", "最近一月", false));

        mFilterTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setListData(mListLeft);
                mAdapter.notifyDataSetChanged();

                if (mFilterList.getVisibility() == View.GONE)
                    mFilterList.setVisibility(View.VISIBLE);
                else
                    mFilterList.setVisibility(View.GONE);
            }
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

        mFilterList = (RecyclerView) getView().findViewById(R.id.filter_list);
        mFilterList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SimpleListAdapter(this, mListLeft);
        mFilterList.setAdapter(mAdapter);
        mAdapter.setFilterList(mFilterList);

        mAdapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p = (int) v.getTag();
                for (SimpleItem simpleItem : mAdapter.getListData()) {
                    simpleItem.setChecked(false);
                }
                mAdapter.getListData().get(p).setChecked(true);
                mFilterList.setVisibility(View.GONE);
                if (mAdapter.getListData() == mListLeft) {
                    mFilterTitleLeft.setText(mAdapter.getListData().get(p).getTitle());
                } else {
                    mFilterTitleRight.setText(mAdapter.getListData().get(p).getTitle());
                }
            }
        });
    }

    @Override
    protected String getCacheKey() {
        return null;
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new AlertListAdapter(this, mList);
    }
}
