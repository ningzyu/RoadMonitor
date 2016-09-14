package com.sxhxjy.roadmonitor.ui.main;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.sxhxjy.roadmonitor.adapter.MonitorListAdapter;
import com.sxhxjy.roadmonitor.base.BaseEntity;
import com.sxhxjy.roadmonitor.base.BaseListFragment;
import com.sxhxjy.roadmonitor.base.HttpResponse;
import com.sxhxjy.roadmonitor.entity.Monitor;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;

/**
 * 2016/9/10
 *
 * @author Michael Zhao
 */
public class MonitorListFragment extends BaseListFragment<BaseEntity, Monitor> {


    @Override
    public Call<BaseEntity> getRetrofitCall() {
        return null;
    }

    @Override
    public Observable<HttpResponse<BaseEntity>> getObservable() {
        return null;
    }

    @Override
    protected Class<Monitor> getItemClass() {
        return null;
    }

    @Override
    protected JsonArray getJsonArray(Response<BaseEntity> response) {
        return null;
    }

    @Override
    protected void initActionBar() {
        initToolBar(getView(), "传感器");
    }

    @Override
    protected String getCacheKey() {
        return null;
    }

    @Override
    public Monitor newEntity() {
        return null;
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new MonitorListAdapter(this, mList);
    }
}
