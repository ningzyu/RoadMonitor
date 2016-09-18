package com.sxhxjy.roadmonitor.ui.main;

import android.support.v7.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.sxhxjy.roadmonitor.adapter.MonitorListAdapter;
import com.sxhxjy.roadmonitor.adapter.StationListAdapter;
import com.sxhxjy.roadmonitor.base.BaseListFragment;
import com.sxhxjy.roadmonitor.base.HttpResponse;
import com.sxhxjy.roadmonitor.entity.Station;

import retrofit2.Response;
import rx.Observable;

/**
 * 2016/9/18
 *
 * @author Michael Zhao
 */
public class StationListFragment extends BaseListFragment<Station> {

    @Override
    public Observable<HttpResponse<Station>> getObservable() {
        return getHttpService().getStations(mPageIndex, PAGE_SIZE, "");
    }

    @Override
    protected Class<Station> getItemClass() {
        return null;
    }

    @Override
    protected JsonArray getJsonArray(Response<Station> response) {
        return null;
    }

    @Override
    protected void initActionBar() {
        initToolBar(getView(), "监测点");
    }

    @Override
    protected String getCacheKey() {
        return null;
    }

    @Override
    public Station newEntity() {
        return null;
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new StationListAdapter(this, mList);
    }
}
