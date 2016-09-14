package com.sxhxjy.roadmonitor.base;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 2016/6/18
 *
 * @author Michael Zhao
 */
public abstract class MyCallBack<T extends BaseEntity> implements Callback<T> {
    private BaseActivity mActivity;
    public MyCallBack(BaseActivity baseActivity) {
        mActivity = baseActivity;
    }
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response == null || response.body() == null) {
            Log.e("retrofit", "response is Null !?\n" + call.request().toString());
            return;
        }
        if (MyConstants.IS_DEBUG)
            Log.i("retrofit", call.request().toString() + "\n" + response.body().toString());

     /*   if (response.body().getStatus() > 200) {
            mActivity.showToastMsg(response.body().getDesc());
            onMyFailure(call, response);
        } else {
            onMySuccess(call, response);
        }*/
    }

    public abstract void onMySuccess(Call<T> call, Response<T> response);

    public void onMyFailure(Call<T> call, Response<T> response) {

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (MyConstants.IS_DEBUG)
            Log.i("retrofit", "FAILURE !" + call.request().toString());
        mActivity.showToastMsg("请检查您的网络连接");
    }
}
