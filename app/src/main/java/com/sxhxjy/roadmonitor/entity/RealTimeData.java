package com.sxhxjy.roadmonitor.entity;

/**
 * 2016/9/19
 *
 * @author Michael Zhao
 */
public class RealTimeData {
    /**
     * id : 5028586
     * name : 忻报高速边坡1
     * code : a1
     * xColName : 温度
     * x : 1.76
     * yColName :
     * y : null
     * zColName :
     * z : null
     * typeCode : 1
     * typeValue : 平面值
     * typeUnit : ℃
     * saveTime : 1476806396000
     */

    private String id;
    private String name;
    private String code;
    private String xColName;
    private double x;
    private String yColName;
    private String y;
    private String zColName;
    private String z;
    private String typeCode;
    private String typeValue;
    private String typeUnit;
    private long saveTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getXColName() {
        return xColName;
    }

    public void setXColName(String xColName) {
        this.xColName = xColName;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public String getYColName() {
        return yColName;
    }

    public void setYColName(String yColName) {
        this.yColName = yColName;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getZColName() {
        return zColName;
    }

    public void setZColName(String zColName) {
        this.zColName = zColName;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getTypeUnit() {
        return typeUnit;
    }

    public void setTypeUnit(String typeUnit) {
        this.typeUnit = typeUnit;
    }

    public long getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(long saveTime) {
        this.saveTime = saveTime;
    }
}
