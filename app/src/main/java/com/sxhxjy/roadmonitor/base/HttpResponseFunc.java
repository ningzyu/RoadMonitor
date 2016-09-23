package com.sxhxjy.roadmonitor.base;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import rx.functions.Func1;

/**
 * 2016/9/13
 *
 * @author Michael Zhao
 */
public class HttpResponseFunc<T> implements Func1<HttpResponse<T>, T> {
    private BaseActivity mContext;
    public HttpResponseFunc(BaseActivity baseActivity) {
        mContext = baseActivity;
    }

    @Override
    public T call(final HttpResponse<T> tHttpResponse) {

        // TODO result code
        if (tHttpResponse != null && tHttpResponse.getResultCode() == 200) {
            Log.d("retrofit", tHttpResponse.toString());
            return tHttpResponse.getData();
        } else {
            if (tHttpResponse != null) {
                mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mContext.showToastMsg(tHttpResponse.getResultMessage());
                    }
                });
                Log.e("retrofit", "FAILURE ! msg:" + tHttpResponse.getResultMessage());
            } else {
                Log.e("retrofit", "FAILURE ! msg:" + null);
            }
            return null;
        }
    }
}
