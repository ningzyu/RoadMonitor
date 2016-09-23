package com.sxhxjy.roadmonitor.entity;

import java.util.List;

/**
 * 2016/9/23
 *
 * @author Michael Zhao
 */
public class LoginData {
    /**
     * id : 4
     * account : admin
     * password : e10adc3949ba59abbe56e057f20f883e
     * objType : TA
     * objId : 1
     * gid : 4028816456d59b480156d59dc2d40001
     * priUserGroup : {"id":"4028816456d59b480156d59dc2d40001","type":null,"level":null,"num":"sxsjtt","name":"山西省交通运输厅","description":"山西省交通运输厅","saveTime":1472463480000,"deleteState":"0","parentid":"0","provinceid":"140000","cityid":"140100","adCity":{"id":140100,"city":"太原市","father":"140000"},"adProvince":{"id":140000,"province":"山西省"},"applyType":null,"priRoleList":null,"roleIds":null,"childrenGroup":null,"parentGroup":null,"cityList":null}
     * toaTaBase : {"id":"1","nameCn":"山西和信基业科技股份有限公司","nameEn":"22","permitArea":"0","nature":"11","address":"太原市高新区","postcode":"11","registeredCapital":"11","legalPerson":"2222","legalPersonMobile":"15835511407","managerName":"11","managerMobile":"15835511803","managerPhone":"0349-5020222","warrantyIs":"1","guaranteeIs":"1","safeIs":"1","businessIs":"1","applyPerson":"111","applyTime":1459123200000,"applyPhone":"15835511459","mail":"kangxl@139.xn--com-yw3eaa","qqNum":"2531562256","wxNum":"18335184320","faxNum":"1234154965949","businessNum":"晋0125632","phone":"18335184321","approveNum":"1233","licenceNum":"4566","provinceCode":null,"cityCode":null,"areaCode":null,"checkState":"3","level":"1","remark":"嗯嗯","saveTime":1456365956000,"deleteState":"0","applyTimeBefore":"","applyTimeAfter":""}
     * name : 管理员
     * sex : 0
     * mobile : 18812345666
     * phone :
     * mail :
     * remark :
     * saveTime : 1470817521000
     * deleteState : 0
     * pvList : []
     */

    private String id;
    private String account;
    private String password;
    private String objType;
    private String objId;
    private String gid;
    /**
     * id : 4028816456d59b480156d59dc2d40001
     * type : null
     * level : null
     * num : sxsjtt
     * name : 山西省交通运输厅
     * description : 山西省交通运输厅
     * saveTime : 1472463480000
     * deleteState : 0
     * parentid : 0
     * provinceid : 140000
     * cityid : 140100
     * adCity : {"id":140100,"city":"太原市","father":"140000"}
     * adProvince : {"id":140000,"province":"山西省"}
     * applyType : null
     * priRoleList : null
     * roleIds : null
     * childrenGroup : null
     * parentGroup : null
     * cityList : null
     */

    private PriUserGroupBean priUserGroup;
    /**
     * id : 1
     * nameCn : 山西和信基业科技股份有限公司
     * nameEn : 22
     * permitArea : 0
     * nature : 11
     * address : 太原市高新区
     * postcode : 11
     * registeredCapital : 11
     * legalPerson : 2222
     * legalPersonMobile : 15835511407
     * managerName : 11
     * managerMobile : 15835511803
     * managerPhone : 0349-5020222
     * warrantyIs : 1
     * guaranteeIs : 1
     * safeIs : 1
     * businessIs : 1
     * applyPerson : 111
     * applyTime : 1459123200000
     * applyPhone : 15835511459
     * mail : kangxl@139.xn--com-yw3eaa
     * qqNum : 2531562256
     * wxNum : 18335184320
     * faxNum : 1234154965949
     * businessNum : 晋0125632
     * phone : 18335184321
     * approveNum : 1233
     * licenceNum : 4566
     * provinceCode : null
     * cityCode : null
     * areaCode : null
     * checkState : 3
     * level : 1
     * remark : 嗯嗯
     * saveTime : 1456365956000
     * deleteState : 0
     * applyTimeBefore :
     * applyTimeAfter :
     */

    private ToaTaBaseBean toaTaBase;
    private String name;
    private String sex;
    private String mobile;
    private String phone;
    private String mail;
    private String remark;
    private long saveTime;
    private String deleteState;
    private List<?> pvList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public PriUserGroupBean getPriUserGroup() {
        return priUserGroup;
    }

    public void setPriUserGroup(PriUserGroupBean priUserGroup) {
        this.priUserGroup = priUserGroup;
    }

    public ToaTaBaseBean getToaTaBase() {
        return toaTaBase;
    }

    public void setToaTaBase(ToaTaBaseBean toaTaBase) {
        this.toaTaBase = toaTaBase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }

    public List<?> getPvList() {
        return pvList;
    }

    public void setPvList(List<?> pvList) {
        this.pvList = pvList;
    }

    public static class PriUserGroupBean {
        private String id;
        private Object type;
        private Object level;
        private String num;
        private String name;
        private String description;
        private long saveTime;
        private String deleteState;
        private String parentid;
        private String provinceid;
        private String cityid;
        /**
         * id : 140100
         * city : 太原市
         * father : 140000
         */

        private AdCityBean adCity;
        /**
         * id : 140000
         * province : 山西省
         */

        private AdProvinceBean adProvince;
        private Object applyType;
        private Object priRoleList;
        private Object roleIds;
        private Object childrenGroup;
        private Object parentGroup;
        private Object cityList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
            this.level = level;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public long getSaveTime() {
            return saveTime;
        }

        public void setSaveTime(long saveTime) {
            this.saveTime = saveTime;
        }

        public String getDeleteState() {
            return deleteState;
        }

        public void setDeleteState(String deleteState) {
            this.deleteState = deleteState;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getProvinceid() {
            return provinceid;
        }

        public void setProvinceid(String provinceid) {
            this.provinceid = provinceid;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public AdCityBean getAdCity() {
            return adCity;
        }

        public void setAdCity(AdCityBean adCity) {
            this.adCity = adCity;
        }

        public AdProvinceBean getAdProvince() {
            return adProvince;
        }

        public void setAdProvince(AdProvinceBean adProvince) {
            this.adProvince = adProvince;
        }

        public Object getApplyType() {
            return applyType;
        }

        public void setApplyType(Object applyType) {
            this.applyType = applyType;
        }

        public Object getPriRoleList() {
            return priRoleList;
        }

        public void setPriRoleList(Object priRoleList) {
            this.priRoleList = priRoleList;
        }

        public Object getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(Object roleIds) {
            this.roleIds = roleIds;
        }

        public Object getChildrenGroup() {
            return childrenGroup;
        }

        public void setChildrenGroup(Object childrenGroup) {
            this.childrenGroup = childrenGroup;
        }

        public Object getParentGroup() {
            return parentGroup;
        }

        public void setParentGroup(Object parentGroup) {
            this.parentGroup = parentGroup;
        }

        public Object getCityList() {
            return cityList;
        }

        public void setCityList(Object cityList) {
            this.cityList = cityList;
        }

        public static class AdCityBean {
            private int id;
            private String city;
            private String father;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getFather() {
                return father;
            }

            public void setFather(String father) {
                this.father = father;
            }
        }

        public static class AdProvinceBean {
            private int id;
            private String province;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }
        }
    }

    public static class ToaTaBaseBean {
        private String id;
        private String nameCn;
        private String nameEn;
        private String permitArea;
        private String nature;
        private String address;
        private String postcode;
        private String registeredCapital;
        private String legalPerson;
        private String legalPersonMobile;
        private String managerName;
        private String managerMobile;
        private String managerPhone;
        private String warrantyIs;
        private String guaranteeIs;
        private String safeIs;
        private String businessIs;
        private String applyPerson;
        private long applyTime;
        private String applyPhone;
        private String mail;
        private String qqNum;
        private String wxNum;
        private String faxNum;
        private String businessNum;
        private String phone;
        private String approveNum;
        private String licenceNum;
        private Object provinceCode;
        private Object cityCode;
        private Object areaCode;
        private String checkState;
        private String level;
        private String remark;
        private long saveTime;
        private String deleteState;
        private String applyTimeBefore;
        private String applyTimeAfter;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNameCn() {
            return nameCn;
        }

        public void setNameCn(String nameCn) {
            this.nameCn = nameCn;
        }

        public String getNameEn() {
            return nameEn;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public String getPermitArea() {
            return permitArea;
        }

        public void setPermitArea(String permitArea) {
            this.permitArea = permitArea;
        }

        public String getNature() {
            return nature;
        }

        public void setNature(String nature) {
            this.nature = nature;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getRegisteredCapital() {
            return registeredCapital;
        }

        public void setRegisteredCapital(String registeredCapital) {
            this.registeredCapital = registeredCapital;
        }

        public String getLegalPerson() {
            return legalPerson;
        }

        public void setLegalPerson(String legalPerson) {
            this.legalPerson = legalPerson;
        }

        public String getLegalPersonMobile() {
            return legalPersonMobile;
        }

        public void setLegalPersonMobile(String legalPersonMobile) {
            this.legalPersonMobile = legalPersonMobile;
        }

        public String getManagerName() {
            return managerName;
        }

        public void setManagerName(String managerName) {
            this.managerName = managerName;
        }

        public String getManagerMobile() {
            return managerMobile;
        }

        public void setManagerMobile(String managerMobile) {
            this.managerMobile = managerMobile;
        }

        public String getManagerPhone() {
            return managerPhone;
        }

        public void setManagerPhone(String managerPhone) {
            this.managerPhone = managerPhone;
        }

        public String getWarrantyIs() {
            return warrantyIs;
        }

        public void setWarrantyIs(String warrantyIs) {
            this.warrantyIs = warrantyIs;
        }

        public String getGuaranteeIs() {
            return guaranteeIs;
        }

        public void setGuaranteeIs(String guaranteeIs) {
            this.guaranteeIs = guaranteeIs;
        }

        public String getSafeIs() {
            return safeIs;
        }

        public void setSafeIs(String safeIs) {
            this.safeIs = safeIs;
        }

        public String getBusinessIs() {
            return businessIs;
        }

        public void setBusinessIs(String businessIs) {
            this.businessIs = businessIs;
        }

        public String getApplyPerson() {
            return applyPerson;
        }

        public void setApplyPerson(String applyPerson) {
            this.applyPerson = applyPerson;
        }

        public long getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(long applyTime) {
            this.applyTime = applyTime;
        }

        public String getApplyPhone() {
            return applyPhone;
        }

        public void setApplyPhone(String applyPhone) {
            this.applyPhone = applyPhone;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getQqNum() {
            return qqNum;
        }

        public void setQqNum(String qqNum) {
            this.qqNum = qqNum;
        }

        public String getWxNum() {
            return wxNum;
        }

        public void setWxNum(String wxNum) {
            this.wxNum = wxNum;
        }

        public String getFaxNum() {
            return faxNum;
        }

        public void setFaxNum(String faxNum) {
            this.faxNum = faxNum;
        }

        public String getBusinessNum() {
            return businessNum;
        }

        public void setBusinessNum(String businessNum) {
            this.businessNum = businessNum;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getApproveNum() {
            return approveNum;
        }

        public void setApproveNum(String approveNum) {
            this.approveNum = approveNum;
        }

        public String getLicenceNum() {
            return licenceNum;
        }

        public void setLicenceNum(String licenceNum) {
            this.licenceNum = licenceNum;
        }

        public Object getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(Object provinceCode) {
            this.provinceCode = provinceCode;
        }

        public Object getCityCode() {
            return cityCode;
        }

        public void setCityCode(Object cityCode) {
            this.cityCode = cityCode;
        }

        public Object getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(Object areaCode) {
            this.areaCode = areaCode;
        }

        public String getCheckState() {
            return checkState;
        }

        public void setCheckState(String checkState) {
            this.checkState = checkState;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
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

        public String getDeleteState() {
            return deleteState;
        }

        public void setDeleteState(String deleteState) {
            this.deleteState = deleteState;
        }

        public String getApplyTimeBefore() {
            return applyTimeBefore;
        }

        public void setApplyTimeBefore(String applyTimeBefore) {
            this.applyTimeBefore = applyTimeBefore;
        }

        public String getApplyTimeAfter() {
            return applyTimeAfter;
        }

        public void setApplyTimeAfter(String applyTimeAfter) {
            this.applyTimeAfter = applyTimeAfter;
        }
    }
}
