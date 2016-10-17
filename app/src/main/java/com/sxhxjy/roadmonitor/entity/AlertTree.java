package com.sxhxjy.roadmonitor.entity;

import java.util.List;

/**
 * 2016/10/17
 *
 * @author Michael Zhao
 */

public class AlertTree {
    /**
     * id : 4028812c57a344a30157a3749a380004
     * value : 一级
     */

    private List<AlarmLevelBean> alarmLevel;
    /**
     * id : 4028812c57c25e6c0157c26652d20000
     * value : 新告警
     */

    private List<AlarmStateBean> alarmState;
    /**
     * id : 4028812c57a344a30157a37630c20008
     * value : DTU
     */

    private List<AlarmTypeBean> alarmType;

    public List<AlarmLevelBean> getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(List<AlarmLevelBean> alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public List<AlarmStateBean> getAlarmState() {
        return alarmState;
    }

    public void setAlarmState(List<AlarmStateBean> alarmState) {
        this.alarmState = alarmState;
    }

    public List<AlarmTypeBean> getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(List<AlarmTypeBean> alarmType) {
        this.alarmType = alarmType;
    }

    public static class AlarmLevelBean {
        private String id;
        private String value;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class AlarmStateBean {
        private String id;
        private String value;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class AlarmTypeBean {
        private String id;
        private String value;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
