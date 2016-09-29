package com.sxhxjy.roadmonitor.ui.main;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.view.View;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseActivity;

/**
 * 2016/9/29
 *
 * @author Michael Zhao
 */

public class AddDataContrastActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data_contrast_activity);
        initToolBar("数据关联", true);
    }

    public void monitorType(View view) {

    }

    public void monitorLocation(View view) {

    }

    public void timeStart(View view) {
        new TimePickerDialog(this, null, 11, 11, true).show();
    }

    public void timeEnd(View view) {
        new DatePickerDialog(this, null, 2016, 10, 2).show();
    }
}
