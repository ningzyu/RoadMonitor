package com.sxhxjy.roadmonitor.base;

import android.util.Log;
import android.widget.Toast;

import rx.Subscriber;

/**
 * 2016/9/23
 *
 * @author Michael Zhao
 */

public abstract class MySubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(MyApplication.getMyApplication(), "请检查你的网络连接!", Toast.LENGTH_LONG).show();
        Log.e("retrofit", e.toString());
    }

    @Override
    public void onNext(T t) {
        if (t != null)
            onMyNext(t);
    }

    protected abstract void onMyNext(T t);
}
