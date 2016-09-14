package com.sxhxjy.roadmonitor.base;

import java.util.List;

/**
 * 2016/6/21
 *
 * @author Michael Zhao
 */
public class UserData extends BaseEntity {

    /**
     * vcUserId : 34242
     * vcPhone : 13900011111
     * vcAccount : 13111111111
     * vcPwd : E10ADC3949BA59ABBE56E057F20F883E
     * vcName : tom
     * vcBirthday : 2016-02-13
     * vcEmail :
     * vcOrgId : 402880ec52d97ceb0152d98d431f0003
     * vcOrgName : 大马村
     * vcPost :
     * vcDept :
     * vcDeviceImei :
     * dtNewTime : 2016-08-04 21:58:41.0
     * vcPhoto : /uploads/2016-02-14/201602141751617580.jpg
     * vcUserlaln : 37.880066,112.508754
     */

    public List<Data> data;

    public static class Data {
        public String vcUserId;
        public String vcPhone;
        public String vcAccount;
        public String vcPwd;
        public String vcName;
        public String vcBirthday;
        public String vcEmail;
        public String vcOrgId;
        public String vcOrgName;
        public String vcPost;
        public String vcDept;
        public String vcDeviceImei;
        public String dtNewTime;
        public String vcPhoto;
        public String vcUserlaln;
    }
}
