package com.sxhxjy.roadmonitor.entity;

/**
 * 2016/9/10
 *
 * @author Michael Zhao
 */
public class Monitor {
    /**
     * id : 40288115567df95201567e00a6140002
     * name : 光感传感器
     * saveTime : 1471248914000
     * cstate : 0
     * remark : 光感传感器
     * cstype : 40288115568d152901568d3f0a4f0001
     * priDict : {"id":"40288115568d152901568d3f0a4f0001","word":"device_type","code":"1","value":"平面值","remark":"类似温度这样的平面显示值","saveTime":1471248534000}
     * unit : 1
     * style : null
     */

    public String id;
    public String name;
    public long saveTime;
    public int cstate;
    public String remark;
    public String cstype;
    /**
     * id : 40288115568d152901568d3f0a4f0001
     * word : device_type
     * code : 1
     * value : 平面值
     * remark : 类似温度这样的平面显示值
     * saveTime : 1471248534000
     */

    public PriDict priDict;
    public String unit;
    public Object style;

    public static class PriDict {
        public String id;
        public String word;
        public String code;
        public String value;
        public String remark;
        public long saveTime;
    }
}
