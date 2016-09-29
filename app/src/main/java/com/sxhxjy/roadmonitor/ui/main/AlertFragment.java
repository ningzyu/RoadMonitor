package com.sxhxjy.roadmonitor.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.AlertListAdapter;
import com.sxhxjy.roadmonitor.adapter.FilterTreeAdapter;
import com.sxhxjy.roadmonitor.adapter.MonitorListAdapter;
import com.sxhxjy.roadmonitor.adapter.SimpleListAdapter;
import com.sxhxjy.roadmonitor.base.BaseActivity;
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
    private FilterTreeAdapter filterTreeAdapter;


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


        mToolbar.inflateMenu(R.menu.filter_right);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                myPopupWindow.show();
                return true;
            }
        });

        myPopupWindow = new MyPopupWindow((BaseActivity) getActivity(), R.layout.popup_window_right);

        ExpandableListView expandableListView = (ExpandableListView) myPopupWindow.getContentView().findViewById(R.id.expandable_list_view);

        final List<FilterTreeAdapter.Group> groups = new ArrayList<>();
        List<SimpleItem> mList0 = new ArrayList<>();
        mList0.add(new SimpleItem("", "一级", true));
        mList0.add(new SimpleItem("", "二级", false));
        mList0.add(new SimpleItem("", "三级", false));
        List<SimpleItem> mList1 = new ArrayList<>();
        mList1.add(new SimpleItem("", "传感器", false));
        mList1.add(new SimpleItem("", "节点", false));
        mList1.add(new SimpleItem("", "DTU", false));
        List<SimpleItem> mList2 = new ArrayList<>();
        mList2.add(new SimpleItem("", "新告警", false));
        mList2.add(new SimpleItem("", "历史告警", false));
        FilterTreeAdapter.Group group0 = new FilterTreeAdapter.Group(mList0, "告警等级");
        FilterTreeAdapter.Group group1 = new FilterTreeAdapter.Group(mList1, "设备类型");
        FilterTreeAdapter.Group group2 = new FilterTreeAdapter.Group(mList2, "状态");
        groups.add(group0);
        groups.add(group1);
        groups.add(group2);

        filterTreeAdapter = new FilterTreeAdapter(groups);
        expandableListView.setAdapter(filterTreeAdapter);
        expandableListView.expandGroup(0);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                    for (SimpleItem simpleItem : groups.get(groupPosition).getList()) {
                        simpleItem.setChecked(false);
                    }

                filterTreeAdapter.mGroups.get(groupPosition).getList().get(childPosition).setChecked(true);
                filterTreeAdapter.notifyDataSetChanged();
                return true;
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
