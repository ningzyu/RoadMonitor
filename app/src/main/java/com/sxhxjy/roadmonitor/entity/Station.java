package com.sxhxjy.roadmonitor.entity;

/**
 * 2016/9/14
 *
 * @author Michael Zhao
 */
public class Station {
    /**
     * id : 40288164569758f00156976e23180002
     * name : 大同公司高速路监测点
     * saveTime : 1473748946000
     * sid : 4028816456da23dd0156da2b59400001
     * priUserGroup : {"id":"4028816456da23dd0156da2b59400001","type":null,"level":null,"uid":"","num":"DTGS","name":"大同公司","description":"大同公司","saveTime":1472539089000,"deleteState":"0","parentid":"4028816456d5a2400156d5aaa38a0000","provinceid":"140000","cityid":"140100","adCity":{"id":140100,"city":"太原市","father":"140000"},"adProvince":{"id":140000,"province":"山西省"},"priRoleList":null,"roleIds":null}
     * code : 02
     * state : 0
     * cid : 40288115567df95201567e00a6140002
     * sensorMonitoringPoints : {"id":"40288115567df95201567e00a6140002","name":"光感传感器","saveTime":1471248914000,"cstate":0,"remark":"光感传感器","cstype":"40288115568d152901568d3f0a4f0001","priDict":{"id":"40288115568d152901568d3f0a4f0001","word":"device_type","code":"1","value":"平面值","remark":"类似温度这样的平面显示值","saveTime":1471248534000},"unit":"1","style":null}
     * cname : 光感传感器
     * ctype : 40288115568d152901568d3f0a4f0001
     * cunit : 1
     * priDict : {"id":"40288115568d152901568d3f0a4f0001","word":"device_type","code":"1","value":"平面值","remark":"类似温度这样的平面显示值","saveTime":1471248534000}
     * xmax : 30.0
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
     * id : 4028816456da23dd0156da2b59400001
     * type : null
     * level : null
     * uid :
     * num : DTGS
     * name : 大同公司
     * description : 大同公司
     * saveTime : 1472539089000
     * deleteState : 0
     * parentid : 4028816456d5a2400156d5aaa38a0000
     * provinceid : 140000
     * cityid : 140100
     * adCity : {"id":140100,"city":"太原市","father":"140000"}
     * adProvince : {"id":140000,"province":"山西省"}
     * priRoleList : null
     * roleIds : null
     */

    public PriUserGroup priUserGroup;
    public String code;
    public String state;
    public String cid;
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
    public double xmax;
    public Object ymax;
    public Object zmax;
    public Object gid;
    public Object selectGroupId;
    public Object pointName;

    public static class PriUserGroup {
        public String id;
        public Object type;
        public Object level;
        public String uid;
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
