package com.sxhxjy.roadmonitor.adapter;

import android.graphics.Color;
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
    public List<Group> mGroups;
    public FilterTreeAdapter(List<Group> groups) {
        mGroups = groups;
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mGroups.size() == 0) return 0;
        return mGroups.get(groupPosition).list.size();
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
        textView.setText(mGroups.get(groupPosition).getGroupName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_tree_item, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.title);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.right_image);
        textView.setText(mGroups.get(groupPosition).list.get(childPosition).getTitle());
        textView.setTextSize(15);
        textView.setPadding(90, 0, 0, 0);
        if (mGroups.get(groupPosition).list.get(childPosition).isChecked()) {
            imageView.setVisibility(View.VISIBLE);
            textView.setTextColor(Color.YELLOW);
        } else {
            imageView.setVisibility(View.GONE);
            textView.setTextColor(Color.WHITE);
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
