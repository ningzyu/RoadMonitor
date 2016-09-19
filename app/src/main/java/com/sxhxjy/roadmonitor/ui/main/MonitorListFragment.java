package com.sxhxjy.roadmonitor.ui.main;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.MonitorListAdapter;
import com.sxhxjy.roadmonitor.base.BaseEntity;
import com.sxhxjy.roadmonitor.base.BaseListFragment;
import com.sxhxjy.roadmonitor.base.HttpResponse;
import com.sxhxjy.roadmonitor.entity.Monitor;
import com.sxhxjy.roadmonitor.ui.chart.ChartActivity;
import com.sxhxjy.roadmonitor.util.ActivityUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;

/**
 * 2016/9/10
 *
 * @author Michael Zhao
 */
public class MonitorListFragment extends BaseListFragment<Monitor> {
    private String stationId = "40288164568be6a401568bf1e5100000";


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
        initToolBar(getView(), "传感器", false);
        TextView textViewRight = (TextView) getView().findViewById(R.id.toolbar_right);
        textViewRight.setText("组织");
        textViewRight.setOnClickListener(new View.OnClickListener() {
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
            mPullRefreshLoadLayout.refreshBegin();
            onRefresh();
        }

    }
}
