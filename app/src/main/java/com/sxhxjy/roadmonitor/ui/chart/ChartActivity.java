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

    private LineChart lineChart;

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



        lineChart = (LineChart) findViewById(R.id.line_chart);
        assert lineChart != null;

        lineChart.setDescription("fans line chart");
        lineChart.setDescriptionColor(getResources().getColor(R.color.colorPrimary));
        lineChart.setScaleEnabled(false);


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis yAxis = lineChart.getAxisLeft();

        yAxis.setDrawZeroLine(true);
        yAxis.setDrawGridLines(false);
        yAxis.setAxisMaxValue(100f);
        yAxis.setLabelCount(11, false);

        lineChart.getAxisRight().setEnabled(false);

        ArrayList<Entry> valsFans1 = new ArrayList<Entry>();
        Entry f1e1 = new Entry(50f, 0);
        Entry f1e2 = new Entry(90.000f, 1);
        Entry f1e3 = new Entry(30.000f, 2);
        Entry f1e4 = new Entry(60.000f, 3);
        valsFans1.add(f1e1);
        valsFans1.add(f1e2);
        valsFans1.add(f1e3);
        valsFans1.add(f1e4);

        ArrayList<Entry> valsFans2 = new ArrayList<Entry>();
        Entry f2e1 = new Entry(20f, 0);
        Entry f2e2 = new Entry(60.000f, 1);
        Entry f2e3 = new Entry(70.000f, 2);
        Entry f2e4 = new Entry(40.000f, 3);
        valsFans2.add(f2e1);
        valsFans2.add(f2e2);
        valsFans2.add(f2e3);
        valsFans2.add(f2e4);

        final LineDataSet setFans1 = new LineDataSet(valsFans1, "fans one");
        setFans1.setCubicIntensity(0.5f);
        setFans1.setColor(Color.MAGENTA);
        setFans1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setFans1.setDrawCubic(false);
//        setFans1.setDrawFilled(true);

      /*  CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {
            int i = 0;

            @Override
            public void onTick(long millisUntilFinished) {

                setFans1.addEntry(new Entry(20f + i * 10, i++));
                lineChart.invalidate();
            }

            @Override
            public void onFinish() {

            }
        }.start();*/


        LineDataSet setFans2 = new LineDataSet(valsFans2, "fans two");
        setFans2.setAxisDependency(YAxis.AxisDependency.LEFT);
        setFans2.setColor(Color.YELLOW);
        setFans2.setDrawFilled(true);
        setFans2.setFillAlpha(100);
//        setFans2.setFillColor(Color.GREEN);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setFans1);
        dataSets.add(setFans2);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("1.Q"); xVals.add("2.Q"); xVals.add("3.Q"); xVals.add("4.Q");

        LineData data = new LineData(xVals, dataSets);
        lineChart.setData(data);
        lineChart.invalidate();


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
