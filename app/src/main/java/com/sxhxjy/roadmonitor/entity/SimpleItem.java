package com.sxhxjy.roadmonitor.entity;

/**
 * 2016/9/18
 *
 * @author Michael Zhao
 */
public class SimpleItem {
    String id;
    String title;
    boolean checked;

    public SimpleItem() {}

    public SimpleItem(String id, String title, boolean checked) {
        this.id = id;
        this.title = title;
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
