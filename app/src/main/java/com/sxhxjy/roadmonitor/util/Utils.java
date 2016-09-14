package com.sxhxjy.roadmonitor.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.widget.EditText;


import com.sxhxjy.roadmonitor.base.MyApplication;

import java.text.DecimalFormat;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     * 获取当前版本标示号
     *
     * @param mContext
     * @return
     */
    public static int getCurrentVersionCode(Context mContext) {
        try {
            return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取当前版本号
     *
     * @param mContext
     * @return
     */
    public static String getCurrentVersionName(Context mContext) {
        try {
            return mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 判断是否是合法的身份证号
     *
     * @param string
     * @return
     */
    public static boolean isICCard(String string) {
        // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
        Pattern pattern = Pattern.compile("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)*");
        if (string == null || string.trim().length() == 0)
            return false;
        return pattern.matcher(string).matches();
    }

    /**
     * 检查是否包含特殊字符
     *
     * @param string
     * @return
     */
    public static boolean checkSpecialCharacter(String string) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(string);
        return m.find();
    }


    public static boolean checkPwd(String string) {
        String regEx = "^[A-Za-z0-9_\\-`=\\[\\];',\\.\\/~!@#$%^&*()_\\+|{}:\"<>?]+$";
        Pattern p = Pattern.compile(regEx);
        return p.matcher(string).matches();
    }

    public static boolean checkPhone(String string) {
        String regEx = "^1\\d{10}$";
        Pattern p = Pattern.compile(regEx);
        return p.matcher(string).matches();
    }

    public static boolean checkCode(String string) {
        String regEx = "^\\d+$";
        Pattern p = Pattern.compile(regEx);
        return p.matcher(string).matches();
    }
    /**
     * 拨打电话
     *
     * @param activity
     * @param phone
     */
    public static void callPhone(Activity activity, String phone) {
        Uri uri = Uri.parse("tel:" + phone);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        activity.startActivity(intent);
    }

    /**
     * 发送短信
     *
     * @param activity
     * @param phone
     * @param msg
     */
    public static void sendMessage(Activity activity, String phone, String msg) {
        Uri uri = Uri.parse("smsto:" + phone);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", msg);
        activity.startActivity(intent);
    }


    /**
     * 将传入的金额（单位元）格式化为元
     * 格式如：1  1.1 1.01 1,000
     *
     * @param amount
     * @return
     */
    public static String formatMoneyByYuan(double amount) {
        DecimalFormat formater = new DecimalFormat("###,##0.##");
        String result = formater.format(amount);
        return result;
    }


    /**
     * 处理数字
     *
     * @param number
     * @return
     */
    public static String formatNumber(double number) {
        DecimalFormat formater = new DecimalFormat("#0.##");
        String result = formater.format(number);
        return result;
    }

    /**
     * 通用处理金额输入
     *
     * @param edit
     */
    public static void formatInput(EditText edit) {
        String str = edit.getText().toString();
        String newstr = "" + str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.contains(".")) {
            int index = str.indexOf(".");

            if (index == 0) {
                newstr = "0" + str;
            }
            int lastIndex = str.lastIndexOf(".");
            if (index != lastIndex) {
                newstr = str.substring(0, lastIndex)
                        + str.substring(lastIndex + 1);
            }

            String[] temp2 = newstr.split("\\.");
            if (temp2.length == 2) {
                if (temp2[1].length() > 2) {
                    newstr = temp2[0] + "." + temp2[1].substring(0, 2);
                }
            }
            if (!newstr.equals(str)) {
                edit.setText(newstr);
                CharSequence text = edit.getText();
                if (text instanceof Spannable) {
                    Spannable spanText = (Spannable) text;
                    Selection.setSelection(spanText, text.length());
                }
            }
        } else {
            if (str.charAt(0) == '0' && str.length() > 1) {
                edit.setText(str.substring(1));
                CharSequence text = edit.getText();
                if (text instanceof Spannable) {
                    Spannable spanText = (Spannable) text;
                    Selection.setSelection(spanText, text.length());
                }
            }
        }
    }

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        s = s.replace("-", "");
        s = s.toLowerCase();
        return s;
    }

    public static String numberToChinese(int src) {
        String res = null;
        switch (src) {
            case 0:
                res = "零";
                break;
            case 1:
                res = "一";
                break;
            case 2:
                res = "二";
                break;
            case 3:
                res = "三";
                break;
            case 4:
                res = "四";
                break;
            case 5:
                res = "五";
                break;
            case 6:
                res = "六";
                break;
            case 7:
                res = "七";
                break;
            case 8:
                res = "八";
                break;
            case 9:
                res = "九";
                break;
            case 10:
                res = "十";
                break;
            case 11:
                res = "十一";
                break;
            case 12:
                res = "十二";
                break;
            default:
                break;
        }
        return res;
    }

    /**
     * 获取唯一标识
     */
    public static String getDeviceId() {
        try {
            TelephonyManager tm = (TelephonyManager) MyApplication.getMyApplication().getSystemService(Context.TELEPHONY_SERVICE);
            String DEVICE_ID = tm.getDeviceId();
            if (!TextUtils.isEmpty(DEVICE_ID)) {
                return DEVICE_ID;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String SerialNumber = Build.SERIAL;
        if (!TextUtils.isEmpty(SerialNumber)) {
            return SerialNumber;
        }
        return getUUID();
    }

    /**
     *  判断是否有网络
     */
    public static boolean haveNetworkConnection(Context context) {
        boolean haveConnected = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network_info = cm.getActiveNetworkInfo();
        if (network_info != null && network_info.isConnected()) {
            haveConnected = true;
        }
        return haveConnected;
    }

}
