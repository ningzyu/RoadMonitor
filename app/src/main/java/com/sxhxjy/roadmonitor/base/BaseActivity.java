package com.sxhxjy.roadmonitor.base;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.util.ActivityUtil;


/**
 * Extends activity to add some methods
 *
 * @author Michael Zhao
 */
public class BaseActivity extends AppCompatActivity {

    /** lots of fragments maybe use same dialog */
    private ProgressDialog mProgressDialog;
    protected Toolbar mToolbar;

    public void initToolBar(String title, boolean canBack) {
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        if (mToolbar != null) {
            mToolbar.setVisibility(View.VISIBLE);
            mToolbar.setBackgroundResource(R.color.colorPrimary);
            TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
            mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
            if (title != null && mTitle != null) mTitle.setText(title);
            mToolbar.setTitle("");
            if (canBack) {
                mToolbar.setNavigationIcon(R.mipmap.navigation_icon);
                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();

//                    PhotoPopupWindow.requestCamera(this);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    public void showToastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    public void showWaitingDialog(String message) {
        if (message == null) {
            message = "加载中...";
        }
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage(message);
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        mProgressDialog.show();
    }

    public void dismissWaitingDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    /**
     * finishing activity with this method has animation ...
     */
    @Override
    public void onBackPressed() {
        ActivityUtil.finishActivityWithResult(this, RESULT_CANCELED, null);
    }

    public HttpService getHttpService() {
        return MyApplication.getMyApplication().getHttpService();
    }
}
