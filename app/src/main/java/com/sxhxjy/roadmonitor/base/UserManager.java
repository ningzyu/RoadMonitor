package com.sxhxjy.roadmonitor.base;

import android.app.Activity;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.sxhxjy.roadmonitor.entity.User;
import com.sxhxjy.roadmonitor.ui.main.LoginActivity;
import com.sxhxjy.roadmonitor.ui.main.MainActivity;
import com.sxhxjy.roadmonitor.util.ActivityUtil;


/**
 * Manage users with CacheManager
 *
 * @author Michael Zhao
 */
public final class UserManager {
    private static final String IS_LOG_IN = "isIn";
    private static final String UID = "uid";
    private static final String TAG = "UserManager";
    private static final String USER = "user";
    private static User mUser = null;

    /**
     * If user has logged in, will return User; otherwise will return User from Cache
     */
    public static User getUser() {
        if (isLogin()) {
            if (mUser == null) {
                String s = CacheManager.getInstance().get(USER);
                mUser = JSON.parseObject(s, User.class);
            }
            return mUser;
        } else {
            if (mUser == null) {
                mUser = new User();
            }
            return mUser;
        }
    }

    public static String getUID() {
        return MyApplication.getMyApplication().getSharedPreference().getString("uid", "0");
    }

    public static void loginUser(BaseActivity activity, UserData userData) {
        User user = new User();
        user.setAvatar(userData.data.get(0).vcPhoto);
        user.setName(userData.data.get(0).vcName);
        user.setUid(userData.data.get(0).vcUserId);
        MyApplication.getMyApplication().getSharedPreference().edit().putBoolean(IS_LOG_IN, true).apply();
        MyApplication.getMyApplication().getSharedPreference().edit().putString(UID, user.getUid()).apply();
        CacheManager.getInstance().set(USER, JSON.toJSONString(user));
        ActivityUtil.startActivityForResult(activity, MainActivity.class);
        activity.finish();
    }

    public static void logoutUser(Activity activity) {
        MyApplication.getMyApplication().getSharedPreference().edit().putBoolean(IS_LOG_IN, false).apply();
        if (MyConstants.IS_DEBUG) {
            Log.i(TAG, "log out successful !");
        }
        ActivityUtil.startActivityForResult(activity, LoginActivity.class);
        activity.finish();
    }

    public static boolean isLogin() {
        return MyApplication.getMyApplication().getSharedPreference().getBoolean(IS_LOG_IN, false);
    }

}
