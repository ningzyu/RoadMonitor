package com.sxhxjy.roadmonitor.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.MonitorListAdapter;
import com.sxhxjy.roadmonitor.base.BaseListFragment;
import com.sxhxjy.roadmonitor.base.HttpResponse;
import com.sxhxjy.roadmonitor.entity.Monitor;

import java.util.List;

import rx.Observable;

/**
 * 2016/9/10
 *
 * @author Michael Zhao
 */
public class MonitorListFragment extends BaseListFragment<Monitor> {
    private String stationId = "40288164568be6a401568bf1e5100000";
    private TextView mTextViewCenter;


    @Override
    public Observable<HttpResponse<List<Monitor>>> getObservable() {
        return getHttpService().getMonitors(stationId);
    }

    @Override
    protected Class<Monitor> getItemClass() {
        return null;
    }


    @Override
    protected void init() {
        initToolBar(getView(), getArguments().getString("stationName"), false);
        mTextViewCenter = (TextView) getView().findViewById(R.id.toolbar_title);
        mTextViewCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StationListActivity.class);
                startActivityForResult(intent, StationListActivity.REQUEST_CODE);
            }
        });

    }

    @Override
    protected String getCacheKey() {
        return null;
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new MonitorListAdapter(this, mList);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == StationListActivity.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            stationId = data.getStringExtra("stationId");
            mTextViewCenter.setText(data.getStringExtra("stationName"));
            mPullRefreshLoadLayout.refreshBegin();
        }
    }
}
