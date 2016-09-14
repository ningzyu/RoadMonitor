package com.sxhxjy.roadmonitor.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Start or finish activity
 *
 * @author Michael Zhao
 */
public final class ActivityUtil {
    private static final int ENTER_ANIM = android.R.anim.slide_in_left;
    private static final int EXIT_ANIM = android.R.anim.slide_out_right;

    public static void startActivityForResult(Activity currentActivity, Class<?> nextActivity) {
        startActivityForResult(currentActivity, nextActivity, null, -1, -1, -1);
    }

    public static void startActivityForResult(Activity currentActivity, Class<?> nextActivity, Bundle extras, int requestCode) {
        startActivityForResult(currentActivity, nextActivity, extras, requestCode, -1, -1);
    }

    public static void startActivityForResult(Activity currentActivity, Class<?> nextActivity, Bundle extras, int requestCode, int enterAnim, int exitAnim) {
        Intent intent = new Intent(currentActivity, nextActivity);
        if (extras != null)
            intent.putExtras(extras);
        if (requestCode == -1)
            currentActivity.startActivity(intent);
        else
            currentActivity.startActivityForResult(intent, requestCode);
        if (enterAnim != -1 && exitAnim != -1)
            currentActivity.overridePendingTransition(enterAnim, exitAnim);
        else
            currentActivity.overridePendingTransition(ENTER_ANIM, EXIT_ANIM);
    }

    public static void finishActivityWithResult(Activity currentActivity, int resultCode, Bundle extras) {
        finishActivityWithResult(currentActivity, resultCode, extras, -1, -1);
    }

    public static void finishActivityWithResult(Activity currentActivity, int resultCode, Bundle extras, int enterAnim, int exitAnim) {
        Intent intent = null;
        if (extras != null) {
            intent = new Intent();
            intent.putExtras(extras);
        }
        currentActivity.setResult(resultCode, intent);
        currentActivity.finish();
        if (enterAnim != -1 && exitAnim != -1)
            currentActivity.overridePendingTransition(enterAnim, exitAnim);
        else
            currentActivity.overridePendingTransition(ENTER_ANIM, EXIT_ANIM);
    }
}
