package com.sxhxjy.roadmonitor.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseFragment;

/**
 * 2016/9/19
 *
 * @author Michael Zhao
 */
public class AlertFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.alert_fragment, null);
    }
}
