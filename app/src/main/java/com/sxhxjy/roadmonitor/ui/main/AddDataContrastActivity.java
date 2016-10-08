package com.sxhxjy.roadmonitor.ui.main;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.util.ActivityUtil;
import com.sxhxjy.roadmonitor.view.DeleteView;
import com.sxhxjy.roadmonitor.view.MyLinearLayout;

import java.text.Format;

/**
 * 2016/9/29
 *
 * @author Michael Zhao
 */

public class AddDataContrastActivity extends BaseActivity {

    private View addTimeMultiple;
    private View addTimeSingle;
    private LinearLayout timeContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data_contrast_activity);
        initToolBar("数据对比", true);

        mToolbar.inflateMenu(R.menu.confirm_right);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ActivityUtil.finishActivityWithResult(AddDataContrastActivity.this, -1, null);
                return true;
            }
        });

        addTimeMultiple = findViewById(R.id.add_time_multiple);
        timeContent = (LinearLayout) findViewById(R.id.container);
        addTimeSingle = findViewById(R.id.add_time_single);
    }

    public void monitorType(final View view) {
        final String[] aType = {"温度检测", "应变检测", "挠度检测"};

        new AlertDialog.Builder(this).setTitle("选择监测因素").setSingleChoiceItems(aType, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyLinearLayout myLinearLayout = (MyLinearLayout) view;
                myLinearLayout.setContent(aType[which]);
                dialog.dismiss();
            }
        }).create().show();
    }

    public void monitorLocation(final View view) {
        final String[] aType = {"南中环桥", "祥云桥", "漪汾桥"};
        final boolean[] aTypeChecked = new boolean[]{true, false, false};

        new AlertDialog.Builder(this).setTitle("选择监测点位置").setMultiChoiceItems(aType, aTypeChecked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder sb = new StringBuilder();
                int checked = 0;
                for (int i = 0; i < aTypeChecked.length; i++) {
                    if (aTypeChecked[i]) {
                        checked++;
                        sb.append(aType[i] + "  ");
                    }
                }
                MyLinearLayout myLinearLayout = (MyLinearLayout) view;
                myLinearLayout.setContent(sb.toString());
                if (checked > 1) {
                    addTimeSingle.setVisibility(View.GONE);
                    addTimeMultiple.setVisibility(View.VISIBLE);
                } else {
                    addTimeSingle.setVisibility(View.VISIBLE);
                    addTimeMultiple.setVisibility(View.GONE);
                }
            }
        }).create().show();
    }

    public void timeStart(View view) {
        chooseTime((MyLinearLayout) view);
    }

    public void timeEnd(View view) {
        chooseTime((MyLinearLayout) view);
    }

    public StringBuilder chooseTime(final MyLinearLayout myLinearLayout) {
        final StringBuilder sb = new StringBuilder();

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                sb.append(year);
                sb.append("-");
                sb.append(monthOfYear + 1);
                sb.append("-");
                sb.append(dayOfMonth);
                sb.append("  ");
                new TimePickerDialog(AddDataContrastActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        sb.append(hourOfDay < 10 ? "0" + hourOfDay : hourOfDay);
                        sb.append(":");
                        sb.append(minute < 10 ? "0" + minute : minute);
                        myLinearLayout.setContent(sb.toString());
                    }
                }, 0, 0, true).show();
            }
        }, 2016, 0, 1).show();

        return sb;
    }

    public void addTime(View view) {
        final StringBuilder sb = new StringBuilder();

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                sb.append(year);
                sb.append("-");
                sb.append(monthOfYear + 1);
                sb.append("-");
                sb.append(dayOfMonth);
                sb.append("  ");
                new TimePickerDialog(AddDataContrastActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        sb.append(hourOfDay < 10 ? "0" + hourOfDay : hourOfDay);
                        sb.append(":");
                        sb.append(minute < 10 ? "0" + minute : minute);
                        sb.append("  ----  ");
                        new DatePickerDialog(AddDataContrastActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                sb.append(year);
                                sb.append("-");
                                sb.append(monthOfYear + 1);
                                sb.append("-");
                                sb.append(dayOfMonth);
                                sb.append("  ");
                                new TimePickerDialog(AddDataContrastActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        sb.append(hourOfDay < 10 ? "0" + hourOfDay : hourOfDay);
                                        sb.append(":");
                                        sb.append(minute < 10 ? "0" + minute : minute);
                                        timeContent.addView(new DeleteView(AddDataContrastActivity.this, sb.toString(), timeContent));                              }
                                }, 0, 0, true).show();
                            }
                        }, 2016, 0, 1).show();

                    }
                }, 0, 0, true).show();
            }
        }, 2016, 0, 1).show();


    }
}
