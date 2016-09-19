package com.sxhxjy.roadmonitor.ui.chart;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.RealTimeDataListAdapter;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.BaseListFragment;
import com.sxhxjy.roadmonitor.base.HttpResponse;
import com.sxhxjy.roadmonitor.entity.RealTimeData;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import rx.Observable;

/**
 * 2016/5/11
 *
 * @author Michael Zhao
 */
public class ChartActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_activity);
        initToolBar("实时数据", true);


        Fragment f = new RealTimeDataListFragment();
        String monitorId = getIntent().getStringExtra("monitorId");
        Bundle bundle = new Bundle();
        bundle.putString("monitorId", monitorId);
        f.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .add(R.id.container, f).commit();


    }

    public static class RealTimeDataListFragment extends BaseListFragment<RealTimeData> {

        @Override
        public Observable<HttpResponse<List<RealTimeData>>> getObservable() {
            return getHttpService().getRealTimeData(getArguments().getString("monitorId"), "2016-08-01 08:00:00", "2016-08-31 23:00:00");
        }

        @Override
        protected Class<RealTimeData> getItemClass() {
            return null;
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
