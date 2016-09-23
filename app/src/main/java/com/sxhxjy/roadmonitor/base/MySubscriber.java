package com.sxhxjy.roadmonitor.base;

import android.util.Log;

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
        Log.e("retrofit", e.toString());
    }
}
