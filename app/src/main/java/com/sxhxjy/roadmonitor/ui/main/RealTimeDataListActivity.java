package com.sxhxjy.roadmonitor.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.RealTimeDataListAdapter;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.BaseListFragment;
import com.sxhxjy.roadmonitor.base.HttpResponse;
import com.sxhxjy.roadmonitor.entity.RealTimeData;

import java.util.List;

import rx.Observable;

/**
 * 2016/9/26
 *
 * @author Michael Zhao
 */

public class RealTimeDataListActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        getFragmentManager().beginTransaction()
                .add(R.id.container, new RealTimeDataListFragment()).commit();
        initToolBar("实时数据", true);
    }

    public static class RealTimeDataListFragment extends BaseListFragment<RealTimeData> {

        @Override
        public Observable<HttpResponse<List<RealTimeData>>> getObservable() {
//            return getHttpService().getRealTimeData(getArguments().getString("monitorId"), "2016-08-01 08:00:00", "2016-08-31 23:00:00");
            return null;
        }

        @Override
        protected Class<RealTimeData> getItemClass() {
            return RealTimeData.class;
        }

        @Override
        protected void init() {

        }

        @Override
        protected String getCacheKey() {
            return null;
        }

        @Override
        protected RecyclerView.Adapter getAdapter() {
            return new RealTimeDataListAdapter(this, mList);
        }
    }


}
