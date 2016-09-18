package com.sxhxjy.roadmonitor.entity;

/**
 * 2016/9/14
 *
 * @author Michael Zhao
 */
public class Station {

    /**
     * id : 40288164568be6a401568bf1e5100000
     * name : 太旧高速阳泉路段监测点
     * saveTime : 1473747914000
     * sid : 4028816456da23dd0156da2d05570003
     * priUserGroup : {"id":"4028816456da23dd0156da2d05570003","type":null,"level":null,"uid":null,"num":"TJGSYQ","name":"太旧高速阳泉路段","description":"太旧高速阳泉路段","saveTime":1472539198000,"deleteState":"0","parentid":"4028816456da23dd0156da29ee1c0000","provinceid":"140000","cityid":"140100","adCity":{"id":140100,"city":"太原市","father":"140000"},"adProvince":{"id":140000,"province":"山西省"},"priRoleList":null,"roleIds":null,"childrenGroup":null}
     * code : 01
     * state : 0
     * cid : 40288115568d152901568d421b9e0003
     * sensorMonitoringPoints : {"id":"40288115568d152901568d421b9e0003","name":"温度传感器","saveTime":1471248735000,"cstate":0,"remark":"温度传感器","cstype":"40288115568d152901568d3f0a4f0001","priDict":{"id":"40288115568d152901568d3f0a4f0001","word":"device_type","code":"1","value":"平面值","remark":"类似温度这样的平面显示值","saveTime":1471248534000},"unit":"℃","style":null}
     * cname : 温度传感器
     * ctype : 40288115568d152901568d3f0a4f0001
     * cunit : ℃
     * priDict : {"id":"40288115568d152901568d3f0a4f0001","word":"device_type","code":"1","value":"平面值","remark":"类似温度这样的平面显示值","saveTime":1471248534000}
     * xmax : 40
     * ymax : null
     * zmax : null
     * gid : null
     * selectGroupId : null
     * pointName : null
     */

    public String id;
    public String name;
    public long saveTime;
    public String sid;
    /**
     * id : 4028816456da23dd0156da2d05570003
     * type : null
     * level : null
     * uid : null
     * num : TJGSYQ
     * name : 太旧高速阳泉路段
     * description : 太旧高速阳泉路段
     * saveTime : 1472539198000
     * deleteState : 0
     * parentid : 4028816456da23dd0156da29ee1c0000
     * provinceid : 140000
     * cityid : 140100
     * adCity : {"id":140100,"city":"太原市","father":"140000"}
     * adProvince : {"id":140000,"province":"山西省"}
     * priRoleList : null
     * roleIds : null
     * childrenGroup : null
     */

    public PriUserGroup priUserGroup;
    public String code;
    public String state;
    public String cid;
    /**
     * id : 40288115568d152901568d421b9e0003
     * name : 温度传感器
     * saveTime : 1471248735000
     * cstate : 0
     * remark : 温度传感器
     * cstype : 40288115568d152901568d3f0a4f0001
     * priDict : {"id":"40288115568d152901568d3f0a4f0001","word":"device_type","code":"1","value":"平面值","remark":"类似温度这样的平面显示值","saveTime":1471248534000}
     * unit : ℃
     * style : null
     */

    public SensorMonitoringPoints sensorMonitoringPoints;
    public String cname;
    public String ctype;
    public String cunit;
    /**
     * id : 40288115568d152901568d3f0a4f0001
     * word : device_type
     * code : 1
     * value : 平面值
     * remark : 类似温度这样的平面显示值
     * saveTime : 1471248534000
     */

    public PriDict priDict;
    public int xmax;
    public Object ymax;
    public Object zmax;
    public Object gid;
    public Object selectGroupId;
    public Object pointName;

    public static class PriUserGroup {
        public String id;
        public Object type;
        public Object level;
        public Object uid;
        public String num;
        public String name;
        public String description;
        public long saveTime;
        public String deleteState;
        public String parentid;
        public String provinceid;
        public String cityid;
        /**
         * id : 140100
         * city : 太原市
         * father : 140000
         */

        public AdCity adCity;
        /**
         * id : 140000
         * province : 山西省
         */

        public AdProvince adProvince;
        public Object priRoleList;
        public Object roleIds;
        public Object childrenGroup;

        public static class AdCity {
            public int id;
            public String city;
            public String father;
        }

        public static class AdProvince {
            public int id;
            public String province;
        }
    }

    public static class SensorMonitoringPoints {
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

    public static class PriDict {
        public String id;
        public String word;
        public String code;
        public String value;
        public String remark;
        public long saveTime;
    }
}
