package com.sxhxjy.roadmonitor.adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
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
    public List<Group> groups = new ArrayList<>();
    public FilterTreeAdapter() {
        mList0.add(new SimpleItem("", "温度检测", true));
        mList0.add(new SimpleItem("", "温度检测", false));
        mList0.add(new SimpleItem("", "温度检测", false));
        mList1.add(new SimpleItem("", "位移检测", false));
        mList1.add(new SimpleItem("", "伸缩检测", false));
        mList2.add(new SimpleItem("", "应变检测", false));
        mList2.add(new SimpleItem("", "受力检测", false));
        mList2.add(new SimpleItem("", "受力检测", false));
        mList1.add(new SimpleItem("", "挠度检测", false));
        Group group0 = new Group(mList0, "环境主题");
        Group group1 = new Group(mList1, "变形主题");
        Group group2 = new Group(mList2, "应变主题");
        groups.add(group0);
        groups.add(group1);
        groups.add(group2);

    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).list.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_tree_item, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.title);
        textView.setText(groups.get(groupPosition).getGroupName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_tree_item, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.title);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.right_image);
        textView.setText(groups.get(groupPosition).list.get(childPosition).getTitle());
        textView.setTextSize(15);
        textView.setPadding(70, 0, 0, 0);
        if (groups.get(groupPosition).list.get(childPosition).isChecked()) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public static class Group {
        List<SimpleItem> list;
        String groupName;

        public Group(List<SimpleItem> list, String groupName) {
            this.list = list;
            this.groupName = groupName;
        }

        public List<SimpleItem> getList() {
            return list;
        }

        public void setList(List<SimpleItem> list) {
            this.list = list;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }
    }
}
