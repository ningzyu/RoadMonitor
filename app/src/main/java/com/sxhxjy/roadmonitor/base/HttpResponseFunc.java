package com.sxhxjy.roadmonitor.base;

import android.util.Log;

import java.util.List;

import rx.functions.Func1;

/**
 * 2016/9/13
 *
 * @author Michael Zhao
 */
public class HttpResponseFunc<T> implements Func1<HttpResponse<T>, List<T>> {

    @Override
    public List<T> call(HttpResponse<T> tHttpResponse) {

        // TODO result code
        if (tHttpResponse != null && tHttpResponse.getResultCode() == 200) {
            Log.d("retrofit", tHttpResponse.toString());
            return tHttpResponse.getData().getContent();
        } else {
            if (tHttpResponse != null) {
                Log.e("retrofit", "FAILURE ! msg:" + tHttpResponse.getResultMessage());
            } else {
                Log.e("retrofit", "FAILURE ! msg:" + null);
            }
            return null;
        }
    }
}
