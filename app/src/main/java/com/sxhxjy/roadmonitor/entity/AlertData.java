package com.sxhxjy.roadmonitor.entity;

/**
 * 2016/9/26
 *
 * @author Michael Zhao
 */
public class AlertData {


    /**
     * alarmContent : 传感器数据中断
     * confirmInfo : 确认人:管理员;确认内容:a;确认时间:2016-10-18 16:07:05
     * etime : 1476179548000
     * generationReason : 传感器：传感器数据异常中断
     * id : 4028812c57ad71b10157ade146e80006
     * level : 一级
     * num : 1
     * stationName : 忻报高速边坡1
     * stime : 1476179548000
     * type : 传感器
     */

    private String alarmContent;
    private String confirmInfo;
    private long etime;
    private String generationReason;
    private String id;
    private String level;
    private String num;
    private String stationName;
    private long stime;
    private String type;

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    public String getConfirmInfo() {
        return confirmInfo;
    }

    public void setConfirmInfo(String confirmInfo) {
        this.confirmInfo = confirmInfo;
    }

    public long getEtime() {
        return etime;
    }

    public void setEtime(long etime) {
        this.etime = etime;
    }

    public String getGenerationReason() {
        return generationReason;
    }

    public void setGenerationReason(String generationReason) {
        this.generationReason = generationReason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public long getStime() {
        return stime;
    }

    public void setStime(long stime) {
        this.stime = stime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
