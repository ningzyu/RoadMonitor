package com.sxhxjy.roadmonitor.base;

import android.util.Log;

import rx.functions.Func1;

/**
 * 2016/9/13
 *
 * @author Michael Zhao
 */
public class HttpResponseFunc<T> implements Func1<HttpResponse<T>, T> {

    @Override
    public T call(HttpResponse<T> tHttpResponse) {

        // TODO result code
        if (tHttpResponse != null && tHttpResponse.getResultCode() == 200) {
            return tHttpResponse.getData();
        } else {
            Log.e("retrofit", tHttpResponse.toString());
            return null;
        }
    }
}
