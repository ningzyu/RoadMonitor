package com.sxhxjy.roadmonitor.ui.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import com.sxhxjy.roadmonitor.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * 2016/5/11
 *
 * @author Michael Zhao
 */
public class ChartActivity extends BaseActivity {

    private LineChart lineChart;
    private PieChart pieChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_activity);
        initToolBar("图表");
        lineChart = (LineChart) findViewById(R.id.line_chart);
        pieChart = (PieChart) findViewById(R.id.pie_chart);
        assert lineChart != null;
        assert pieChart != null;

        lineChart.setDescription("fans line chart");
        lineChart.setDescriptionColor(getResources().getColor(R.color.colorPrimary));
        lineChart.setScaleEnabled(false);


        pieChart.setDescriptionColor(getResources().getColor(R.color.colorPrimary));
        pieChart.setDescription("fans pie chart");


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


        List<Entry> pieValsFans = new ArrayList<>();
        pieValsFans.add(new Entry(10, 0));
        pieValsFans.add(new Entry(25, 1));
        pieValsFans.add(new Entry(35, 2));
        pieValsFans.add(new Entry(30, 3));

        PieDataSet pieDataSet = new PieDataSet(pieValsFans, "fans percent");
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setSliceSpace(3);
        pieDataSet.setValueFormatter(new PercentFormatter());


        PieData pieData = new PieData(xVals, pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }
}
