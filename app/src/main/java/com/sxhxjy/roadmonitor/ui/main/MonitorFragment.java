package com.sxhxjy.roadmonitor.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.FilterTreeAdapter;
import com.sxhxjy.roadmonitor.adapter.SimpleListAdapter;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.base.MonitorPosition;
import com.sxhxjy.roadmonitor.base.MyApplication;
import com.sxhxjy.roadmonitor.base.MySubscriber;
import com.sxhxjy.roadmonitor.base.ParamInfo;
import com.sxhxjy.roadmonitor.entity.MonitorTypeTree;
import com.sxhxjy.roadmonitor.entity.RealTimeData;
import com.sxhxjy.roadmonitor.entity.SimpleItem;
import com.sxhxjy.roadmonitor.util.ActivityUtil;
import com.sxhxjy.roadmonitor.view.LineChartView;
import com.sxhxjy.roadmonitor.view.MyPopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 2016/9/26
 *
 * @author Michael Zhao
 */

public class MonitorFragment extends BaseFragment implements View.OnClickListener {

    public List<FilterTreeAdapter.Group> groupsOfFilterTree = new ArrayList<>();
    private String stationId;
    private TextView mTextViewCenter;
    private ImageView mImageViewLeft;
    private List<SimpleItem> mListLeft = new ArrayList<>();
    private List<SimpleItem> mListRight = new ArrayList<>();
    private SimpleListAdapter mAdapter;
    private TextView mFilterTitleLeft, mFilterTitleRight;
    private RecyclerView mFilterList;
    private MyPopupWindow myPopupWindow;
    private FilterTreeAdapter filterTreeAdapter;
    private CountDownTimer mTimer;
    private String codeId;

    // multiple position clicked
    private View.OnClickListener simpleListListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int p = (int) v.getTag();

            if (mAdapter.isMultipleChoice()) {
                // button clicked
                if (p == mAdapter.getListData().size()) {
                    StringBuilder sb = new StringBuilder();
                    for (SimpleItem simpleItem : mAdapter.getListData()) {
                        if (simpleItem.isChecked()) {
                            sb.append(simpleItem.getTitle() + "...");
                            break;
                        }
                    }
                    if (TextUtils.isEmpty(sb.toString())) {
                        showToastMsg("请至少选择一个位置！");
                        return;
                    }
                    mFilterTitleLeft.setText(sb.toString());
                    mFilterList.setVisibility(View.GONE);

                    getChartData();

                    return;
                } else {
                    mAdapter.getListData().get(p).setChecked(!mAdapter.getListData().get(p).isChecked());
                }
            } else {
                for (SimpleItem simpleItem : mAdapter.getListData()) {
                    simpleItem.setChecked(false);
                }
                mAdapter.getListData().get(p).setChecked(true);
                mFilterList.setVisibility(View.GONE);
            }


            if (mAdapter.getListData() != mListLeft) {
                mFilterTitleRight.setText(mAdapter.getListData().get(p).getTitle());
            }

            mAdapter.notifyDataSetChanged();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.monitor_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolBar(getView(), getArguments().getString("stationName"), false);
        stationId = getArguments().getString("stationId");
        cacheStation(stationId, getArguments().getString("stationName"));

        mTextViewCenter = (TextView) getView().findViewById(R.id.toolbar_title);
        mImageViewLeft = (ImageView) getView().findViewById(R.id.toolbar_left);
        mImageViewLeft.setVisibility(View.VISIBLE);
        mImageViewLeft.setImageResource(R.mipmap.history);
        mImageViewLeft.setOnClickListener(this);
        mTextViewCenter.setOnClickListener(this);
        mTextViewCenter.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.expand), null);
        mTextViewCenter.setCompoundDrawablePadding(20);

        mFilterTitleLeft = (TextView) view.findViewById(R.id.filter_left);
        mFilterTitleRight = (TextView) view.findViewById(R.id.filter_right);

        mListRight.add(new SimpleItem("", "最近一天", false));
        mListRight.add(new SimpleItem("", "最近一周", false));
        mListRight.add(new SimpleItem("", "最近一月", false));

        mFilterTitleLeft.setOnClickListener(this);
        mFilterTitleRight.setOnClickListener(this);

        mFilterList = (RecyclerView) view.findViewById(R.id.filter_list);
        mFilterList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SimpleListAdapter(this, mListLeft);
        mFilterList.setAdapter(mAdapter);
        mAdapter.setFilterList(mFilterList);

        mAdapter.setListener(simpleListListener);


        mToolbar.inflateMenu(R.menu.filter_right);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (groupsOfFilterTree.isEmpty()) getTypeTree();
                myPopupWindow.show();
                return true;
            }
        });

        myPopupWindow = new MyPopupWindow((BaseActivity) getActivity(), R.layout.popup_window_right);

        ExpandableListView expandableListView = (ExpandableListView) myPopupWindow.getContentView().findViewById(R.id.expandable_list_view);
        Button confirm = (Button) myPopupWindow.getContentView().findViewById(R.id.confirm);
        confirm.setVisibility(View.GONE);

        filterTreeAdapter = new FilterTreeAdapter(groupsOfFilterTree);
        expandableListView.setAdapter(filterTreeAdapter);
        expandableListView.expandGroup(0);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                for (FilterTreeAdapter.Group group : filterTreeAdapter.mGroups) {
                    for (SimpleItem simpleItem : group.getList()) {
                        simpleItem.setChecked(false);
                    }
                }
                filterTreeAdapter.mGroups.get(groupPosition).getList().get(childPosition).setChecked(true);
//                codeId = filterTreeAdapter.mGroups.get(groupPosition).getList().get(childPosition).getId();
                myPopupWindow.dismiss();
                filterTreeAdapter.notifyDataSetChanged();
                getMessage(getHttpService().getPositions(filterTreeAdapter.mGroups.get(groupPosition).getList().get(childPosition).getId(), MyApplication.getMyApplication().getSharedPreference().getString("gid", "")), new MySubscriber<List<MonitorPosition>>() {
                    @Override
                    protected void onMyNext(List<MonitorPosition> monitorPositions) {
                        mListLeft.clear();
                        for (MonitorPosition position : monitorPositions) {
                            SimpleItem simpleItem = new SimpleItem(position.getId(), position.getName(), false);
                            simpleItem.setCode(position.code);
                            mListLeft.add(simpleItem);
                        }
                    }
                });
                return true;
            }
        });


        getParamInfo();

    }

    private void getChartData() {
        if (mTimer != null)
            mTimer.cancel();
        LineChartView lineChartView = (LineChartView) getView().findViewById(R.id.chart);
        lineChartView.getLines().clear();
        mTimer = new CountDownTimer(100000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                for (final SimpleItem simpleItem : mListLeft) {
                    if (simpleItem.isChecked()) {
                        codeId = simpleItem.getCode();
                        getMessage(getHttpService().getRealTimeData(simpleItem.getCode(), System.currentTimeMillis() - 10000, System.currentTimeMillis()), new MySubscriber<List<RealTimeData>>() {
                            @Override
                            protected void onMyNext(List<RealTimeData> realTimeDatas) {
                                LineChartView lineChartView = (LineChartView) getView().findViewById(R.id.chart);
                                lineChartView.addPoints(LineChartView.convert(realTimeDatas), simpleItem.getTitle(), Color.MAGENTA);
                            }
                        });
                    }
                }
            }

            @Override
            public void onFinish() {

            }
        };
        mTimer.start();
    }

    private void getTypeTree(){
        getMessage(getHttpService().getMonitorTypeTree(), new MySubscriber<List<MonitorTypeTree>>() {
            @Override
            protected void onMyNext(List<MonitorTypeTree> monitorTypeTrees) {
                for (MonitorTypeTree monitorTypeTree : monitorTypeTrees) {
                    List<SimpleItem> list = new ArrayList<SimpleItem>();
                    if (monitorTypeTree.getChildrenPoint() != null) {
                        for (MonitorTypeTree.ChildrenPointBean childrenPointBean : monitorTypeTree.getChildrenPoint()) {
                            list.add(new SimpleItem(childrenPointBean.getId(), childrenPointBean.getName(), false));
                        }
                    }
                    FilterTreeAdapter.Group group = new FilterTreeAdapter.Group(list, monitorTypeTree.getName());
                    groupsOfFilterTree.add(group);
                }
                filterTreeAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getParamInfo() {
        getMessage(getHttpService().getParamInfo(stationId), new MySubscriber<ParamInfo>() {
            @Override
            protected void onMyNext(ParamInfo paramInfo) {
                ((TextView) getView().findViewById(R.id.position)).setText("位置：" + MyApplication.getMyApplication().getSharedPreference().getString("stationName", ""));
                ((TextView) getView().findViewById(R.id.min)).setText("最小值：" + paramInfo.getXmin() + "");
                ((TextView) getView().findViewById(R.id.max)).setText("最大值：" + paramInfo.getXmax() + "");
                ((TextView) getView().findViewById(R.id.threshold1)).setText("一级阈值：" + paramInfo.getxOneThreshold() + "");
                ((TextView) getView().findViewById(R.id.threshold2)).setText("二级阈值：" + paramInfo.getxTwoThreshold() + "");
                ((TextView) getView().findViewById(R.id.threshold3)).setText("三级阈值：" + paramInfo.getxThreeThreshold() + "");
                ((TextView) getView().findViewById(R.id.threshold4)).setText("四级阈值：" + paramInfo.getxFourThreshold() + "");

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == StationListActivity.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            stationId = data.getStringExtra("stationId");
            mTextViewCenter.setText(data.getStringExtra("stationName"));
            cacheStation(stationId, data.getStringExtra("stationName"));
            getParamInfo();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_title:
                Intent intent = new Intent(getActivity(), StationListActivity.class);
                startActivityForResult(intent, StationListActivity.REQUEST_CODE);
                break;
            case R.id.toolbar_left:
                Bundle b = new Bundle();
                b.putString("type", codeId);
                ActivityUtil.startActivityForResult(getActivity(), RealTimeDataListActivity.class, b, 100);
                break;

            case R.id.filter_left:
                if (groupsOfFilterTree.isEmpty()) {
                    showToastMsg("请先选择监测类型");
                    return;
                }
                mAdapter.setListData(mListLeft);
                mAdapter.setMultipleChoice(true);
                mAdapter.notifyDataSetChanged();


                if (mFilterList.getVisibility() == View.GONE)
                    mFilterList.setVisibility(View.VISIBLE);
                else
                    mFilterList.setVisibility(View.GONE);
                break;
            case R.id.filter_right:
                mAdapter.setListData(mListRight);
                mAdapter.setMultipleChoice(false);
                mAdapter.notifyDataSetChanged();


                if (mFilterList.getVisibility() == View.GONE)
                    mFilterList.setVisibility(View.VISIBLE);
                else
                    mFilterList.setVisibility(View.GONE);

                break;
        }
    }

    public void cacheStation(String stationId, String stationName) {
        MyApplication.getMyApplication().getSharedPreference().edit().putString("stationId", stationId).apply();
        MyApplication.getMyApplication().getSharedPreference().edit().putString("stationName", stationName).apply();
    }
}
