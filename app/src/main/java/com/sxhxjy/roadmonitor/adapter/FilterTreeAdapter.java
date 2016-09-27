package com.sxhxjy.roadmonitor.adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.entity.SimpleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 2016/9/27
 *
 * @author Michael Zhao
 */

public class FilterTreeAdapter extends BaseExpandableListAdapter {
    private List<SimpleItem> mList0 = new ArrayList<>();
    private List<SimpleItem> mList1 = new ArrayList<>();
    private List<SimpleItem> mList2 = new ArrayList<>();
    private List<String> groups = new ArrayList<>();
    public FilterTreeAdapter() {
        mList0.add(new SimpleItem("", "温度检测", false));
        mList0.add(new SimpleItem("", "温度检测", false));
        mList0.add(new SimpleItem("", "温度检测", false));
        mList1.add(new SimpleItem("", "位移检测", false));
        mList1.add(new SimpleItem("", "伸缩检测", false));
        mList2.add(new SimpleItem("", "应变检测", false));
        mList2.add(new SimpleItem("", "受力检测", false));
        mList2.add(new SimpleItem("", "受力检测", false));
        mList1.add(new SimpleItem("", "挠度检测", false));

        groups.add("环境主题");
        groups.add("变形主题");
        groups.add("应变主题");

    }

    @Override
    public int getGroupCount() {
        return 3;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 3;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.title);
        textView.setText(groups.get(groupPosition));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.title);
        if (groupPosition == 0)
            textView.setText(mList0.get(childPosition).getTitle());
        if (groupPosition == 1)
            textView.setText(mList1.get(childPosition).getTitle());
        if (groupPosition == 2)
            textView.setText(mList2.get(childPosition).getTitle());
        textView.setTextColor(parent.getResources().getColor(R.color.default_text_color));

        textView.setPadding(50, 0, 0, 0);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
