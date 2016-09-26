package com.sxhxjy.roadmonitor.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.AlertListAdapter;
import com.sxhxjy.roadmonitor.adapter.MonitorListAdapter;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.base.BaseListFragment;
import com.sxhxjy.roadmonitor.base.HttpResponse;
import com.sxhxjy.roadmonitor.entity.AlertData;

import java.util.List;

import rx.Observable;

/**
 * 2016/9/19
 *
 * @author Michael Zhao
 */
public class AlertFragment extends BaseListFragment<AlertData> {

    @Override
    public Observable<HttpResponse<List<AlertData>>> getObservable() {
        return null;
    }

    @Override
    protected Class<AlertData> getItemClass() {
        return null;
    }

    @Override
    protected void init() {
        initToolBar(getView(), "数据分析", false);
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
