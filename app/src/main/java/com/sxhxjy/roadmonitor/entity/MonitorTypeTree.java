package com.sxhxjy.roadmonitor.entity;

import java.util.List;

/**
 * 2016/10/10
 *
 * @author Michael Zhao
 */

public class MonitorTypeTree {
    /**
     * id : 4028812c57acbc450157ad3c3e850008
     * fatherId : 0
     * name : 偏移测试仪
     * fieldOne : 偏移量
     * fieldTwo :
     * fieldThree :
     * saveTime : 1476080189000
     * cstate : 0
     * remark :
     * cstype : 40288115568d152901568d3f0a4f0001
     * priDict : {"id":"40288115568d152901568d3f0a4f0001","word":"device_type","code":"1","value":"平面值","remark":"类似温度这样的平面显示值","saveTime":1471248534000}
     * unit : mm
     * style : null
     * parentPoint : null
     * childrenPoint : [{"id":"4028812c57acbc450157ad3d28040009","fatherId":"4028812c57acbc450157ad3c3e850008","name":"平面偏移测试仪","fieldOne":"平面偏移量","fieldTwo":"","fieldThree":"","saveTime":1476080248000,"cstate":0,"remark":"","cstype":"40288115568d152901568d3f0a4f0001","priDict":{"id":"40288115568d152901568d3f0a4f0001","word":"device_type","code":"1","value":"平面值","remark":"类似温度这样的平面显示值","saveTime":1471248534000},"unit":"mm","style":null,"parentPoint":null,"childrenPoint":null}]
     */

    private String id;
    private String fatherId;
    private String name;
    private String fieldOne;
    private String fieldTwo;
    private String fieldThree;
    private long saveTime;
    private int cstate;
    private String remark;
    private String cstype;
    /**
     * id : 40288115568d152901568d3f0a4f0001
     * word : device_type
     * code : 1
     * value : 平面值
     * remark : 类似温度这样的平面显示值
     * saveTime : 1471248534000
     */

    private PriDictBean priDict;
    private String unit;
    private Object style;
    private Object parentPoint;
    /**
     * id : 4028812c57acbc450157ad3d28040009
     * fatherId : 4028812c57acbc450157ad3c3e850008
     * name : 平面偏移测试仪
     * fieldOne : 平面偏移量
     * fieldTwo :
     * fieldThree :
     * saveTime : 1476080248000
     * cstate : 0
     * remark :
     * cstype : 40288115568d152901568d3f0a4f0001
     * priDict : {"id":"40288115568d152901568d3f0a4f0001","word":"device_type","code":"1","value":"平面值","remark":"类似温度这样的平面显示值","saveTime":1471248534000}
     * unit : mm
     * style : null
     * parentPoint : null
     * childrenPoint : null
     */

    private List<ChildrenPointBean> childrenPoint;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldOne() {
        return fieldOne;
    }

    public void setFieldOne(String fieldOne) {
        this.fieldOne = fieldOne;
    }

    public String getFieldTwo() {
        return fieldTwo;
    }

    public void setFieldTwo(String fieldTwo) {
        this.fieldTwo = fieldTwo;
    }

    public String getFieldThree() {
        return fieldThree;
    }

    public void setFieldThree(String fieldThree) {
        this.fieldThree = fieldThree;
    }

    public long getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(long saveTime) {
        this.saveTime = saveTime;
    }

    public int getCstate() {
        return cstate;
    }

    public void setCstate(int cstate) {
        this.cstate = cstate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCstype() {
        return cstype;
    }

    public void setCstype(String cstype) {
        this.cstype = cstype;
    }

    public PriDictBean getPriDict() {
        return priDict;
    }

    public void setPriDict(PriDictBean priDict) {
        this.priDict = priDict;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Object getStyle() {
        return style;
    }

    public void setStyle(Object style) {
        this.style = style;
    }

    public Object getParentPoint() {
        return parentPoint;
    }

    public void setParentPoint(Object parentPoint) {
        this.parentPoint = parentPoint;
    }

    public List<ChildrenPointBean> getChildrenPoint() {
        return childrenPoint;
    }

    public void setChildrenPoint(List<ChildrenPointBean> childrenPoint) {
        this.childrenPoint = childrenPoint;
    }

    public static class PriDictBean {
        private String id;
        private String word;
        private String code;
        private String value;
        private String remark;
        private long saveTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getSaveTime() {
            return saveTime;
        }

        public void setSaveTime(long saveTime) {
            this.saveTime = saveTime;
        }
    }

    public static class ChildrenPointBean {
        private String id;
        private String fatherId;
        private String name;
        private String fieldOne;
        private String fieldTwo;
        private String fieldThree;
        private long saveTime;
        private int cstate;
        private String remark;
        private String cstype;
        /**
         * id : 40288115568d152901568d3f0a4f0001
         * word : device_type
         * code : 1
         * value : 平面值
         * remark : 类似温度这样的平面显示值
         * saveTime : 1471248534000
         */

        private PriDictBean priDict;
        private String unit;
        private Object style;
        private Object parentPoint;
        private Object childrenPoint;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFatherId() {
            return fatherId;
        }

        public void setFatherId(String fatherId) {
            this.fatherId = fatherId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFieldOne() {
            return fieldOne;
        }

        public void setFieldOne(String fieldOne) {
            this.fieldOne = fieldOne;
        }

        public String getFieldTwo() {
            return fieldTwo;
        }

        public void setFieldTwo(String fieldTwo) {
            this.fieldTwo = fieldTwo;
        }

        public String getFieldThree() {
            return fieldThree;
        }

        public void setFieldThree(String fieldThree) {
            this.fieldThree = fieldThree;
        }

        public long getSaveTime() {
            return saveTime;
        }

        public void setSaveTime(long saveTime) {
            this.saveTime = saveTime;
        }

        public int getCstate() {
            return cstate;
        }

        public void setCstate(int cstate) {
            this.cstate = cstate;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCstype() {
            return cstype;
        }

        public void setCstype(String cstype) {
            this.cstype = cstype;
        }

        public PriDictBean getPriDict() {
            return priDict;
        }

        public void setPriDict(PriDictBean priDict) {
            this.priDict = priDict;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Object getStyle() {
            return style;
        }

        public void setStyle(Object style) {
            this.style = style;
        }

        public Object getParentPoint() {
            return parentPoint;
        }

        public void setParentPoint(Object parentPoint) {
            this.parentPoint = parentPoint;
        }

        public Object getChildrenPoint() {
            return childrenPoint;
        }

        public void setChildrenPoint(Object childrenPoint) {
            this.childrenPoint = childrenPoint;
        }

        public static class PriDictBean {
            private String id;
            private String word;
            private String code;
            private String value;
            private String remark;
            private long saveTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public long getSaveTime() {
                return saveTime;
            }

            public void setSaveTime(long saveTime) {
                this.saveTime = saveTime;
            }
        }
    }
}
