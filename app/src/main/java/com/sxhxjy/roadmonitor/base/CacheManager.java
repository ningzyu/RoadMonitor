package com.sxhxjy.roadmonitor.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Uses SQLite database to cache keys and values which usually are data collection
 *
 * @author Michael Zhao
 */
public final class CacheManager extends SQLiteOpenHelper {
    // keys go here ...
    public static final String KEY_USER_INFO = "user";
    public static final String FILTER_CONFIG = "filter";
    public static final String SEARCH_RECORD = "search";



    protected static final String TABLE_NAME = "table_cache";
    private static final String DB_NAME = "cache_db";
    private static final int DB_VERSION = 1;
    /**
     * Singleton instance
     */
    private static CacheManager mManager = new CacheManager(MyApplication.getMyApplication());

    public CacheManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static CacheManager getInstance() {
        return mManager;
    }

    /**
     * | user | key | value |
     * | ...  | ... | ...   |
     * | ...  | ... | ...   |
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + " (user VARCHAR, key VARCHAR, value VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void closeDB() {
        if (mManager != null) {
            try {
                SQLiteDatabase db = mManager.getWritableDatabase();
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mManager = null;
        }
    }

    public void set(String key, String value) {
        if (UserManager.isLogin()) {
            // if exists, remove it firstly and add
            if (get(key) != null) {
                remove(key);
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("user", UserManager.getUID() + "");
            contentValues.put("key", key);
            contentValues.put("value", value);
            getWritableDatabase().insert(TABLE_NAME, null, contentValues);
            if (MyConstants.IS_DEBUG)
                Log.i("CacheManager", "key:" + key + " value:" + value + " has set !");
        }
    }

    public String get(String key) {
        String result = "";
        if (UserManager.isLogin()) {
            Cursor cursor = getWritableDatabase().query(TABLE_NAME, new String[]{"value"},
                    "user=? AND key=?", new String[]{UserManager.getUID() + "", key}, null, null, null);
            if (!cursor.moveToFirst())
                return null;
            result = cursor.getString(cursor.getColumnIndex("value"));
            cursor.close();
            return result;
        } else
            return result;
    }

    public void remove(String key) {
        if (UserManager.isLogin())
            getWritableDatabase().delete(TABLE_NAME, "user=? AND key=?",
                    new String[]{UserManager.getUID() + "", key});
    }

    public void removeAll() {
        if (UserManager.isLogin())
            getWritableDatabase().delete(TABLE_NAME, "user=?", new String[]{UserManager.getUID() + ""});
        else
            Log.e(TABLE_NAME, "user is NOT login ...");
    }


}
