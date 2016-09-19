package com.sxhxjy.roadmonitor.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.open.DividerItemDecoration;
import com.sxhxjy.roadmonitor.view.PullRefreshLoadLayout;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 2016/5/18
 *
 * @author Michael Zhao
 */
public abstract class BaseListFragment<I> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, PullRefreshLoadLayout.OnLoadMoreListener, PullRefreshLoadLayout.OnRefreshListener {
    protected final static int PAGE_SIZE = 20;
    protected RecyclerView mRecyclerView;
    protected LinearLayout mAboveList;
    protected LinearLayout mContainer;
    protected FrameLayout mEmptyLayout;
    protected FrameLayout mLoadingLayout;
    protected ViewGroup mNetworkErrorLayout;

    protected RecyclerView.Adapter mAdapter;
    protected int mPageIndex = 1;
    protected ArrayList<I> mList = new ArrayList<>();
    protected RelativeLayout mActionBar;
    public boolean FAKE;
    private Gson gson = new Gson();

    protected SwipeRefreshLayout swipeRefreshLayout;

    protected RecyclerView.ItemDecoration dividerItemDecoration;
    protected FrameLayout mGlobalContainer;
    protected LinearLayoutManager linearLayoutManager;
    protected PullRefreshLoadLayout mPullRefreshLoadLayout;

    private Func1<HttpResponse<List<I>>, List<I>> func1 = new HttpResponseFunc<>();

    private Subscriber<List<I>> subscriber = new Subscriber<List<I>>() {
        @Override
        public void onStart() {
        }

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.e("retrofit", e.toString());
            mPullRefreshLoadLayout.refreshEnd();
            mPullRefreshLoadLayout.loadMoreEnd();
            showToastMsg("请检查您的网络连接");

        }

        @Override
        public void onNext(List<I> es) {
            mList.clear();
            mList.addAll(es);
            mAdapter.notifyDataSetChanged();
            mPullRefreshLoadLayout.refreshEnd();
            mPullRefreshLoadLayout.loadMoreEnd();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_recycler_view_activity, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAboveList = (LinearLayout) view.findViewById(R.id.above_list);
        mEmptyLayout = (FrameLayout) view.findViewById(R.id.empty_layout);
        mToolbar = (Toolbar) view.findViewById(R.id.tool_bar);
        mEmptyLayout.findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        mLoadingLayout = (FrameLayout) view.findViewById(R.id.loading_layout);
        mNetworkErrorLayout = (ViewGroup) view.findViewById(R.id.network_error_container);
        mNetworkErrorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMessage();
            }
        });

        mPullRefreshLoadLayout = (PullRefreshLoadLayout) view.findViewById(R.id.pull_refresh_load_layout);
        mPullRefreshLoadLayout.setOnRefreshListener(this);
        mPullRefreshLoadLayout.setOnLoadMoreListener(this);


   /*     swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        assert swipeRefreshLayout != null;
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light);
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(this);*/

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mContainer = (LinearLayout) view.findViewById(R.id.container);
        mGlobalContainer = (FrameLayout) view.findViewById(R.id.list_container);

        init();
        initRecyclerView();
    }

    private void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = getAdapter();
        dividerItemDecoration = new DividerItemDecoration();
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        if (getObservable() != null) {
            getMessage();
        } else {
            if (FAKE) {
                if (MyConstants.IS_DEBUG)
                    Log.e("BaseList", "Server interface unavailable, fake data !");
                for (int i = 0; i < 20; i++) {
                    try {
                        mList.add(getItemClass().newInstance()); // type erasure
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    @Override
    public void onRefresh() {
        mPageIndex = 1;
        getMessage();
    }

    @Override
    public void onLoadMore() {
        mPageIndex++;
        getMessage();
    }

    @Override
    protected void loadOnce() {
        super.loadOnce();
        getMessage();
    }

    public void getMessage() {
        if (getObservable() == null) {

            mPullRefreshLoadLayout.refreshEnd();
            mPullRefreshLoadLayout.loadMoreEnd();
            return;
        }

        getObservable().subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(func1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);




       /* if (getRetrofitCall() == null) return;

//        showWaitingDialog(null);
        dismissNetworkErrorLayout();
        showLoadingLayout();
        getRetrofitCall().enqueue(new Callback<E>() {
            @Override
            public void onResponse(Call<E> call, Response<E> response) {
                if (response == null || response.body() == null) {
                    Log.e("retrofit", "response is Null !?");
                    showNetworkErrorLayout();
                    return;
                }

                if (MyConstants.IS_DEBUG)
                    Log.i("retrofit", call.request().toString() + "\n" + response.body().toString());
                dismissLoadingLayout();
                swipeRefreshLayout.setRefreshing(false);

                if (mPageIndex == 1) {
                    mList.clear();
                }

                List<I> temp = null;
                if (getJsonArray(response) != null && getItemClass() != null) {
                     temp = JSON.parseArray(getJsonArray(response).toString(), getItemClass());
                }

                *//*JsonArray a = getJsonArray(response);
                if (a != null) {
                    for (JsonElement e : a) {
                        I i = gson.fromJson(e, new TypeToken<I>() {}.getType());

                        mList.add(i);
                    }
                }*//*
                if (temp != null)
                    mList.addAll(temp);
        *//*        if (response.body().size() < PAGE_SIZE)
                    mRecyclerView.disableLoadmore();
                else
                    mRecyclerView.reenableLoadmore();*//*

                injectData();

                mAdapter.notifyDataSetChanged();
                showListViewOrEmptyView();

                // cache first page
                if (mPageIndex == 1 && getCacheKey() != null) {
                    CacheManager.getInstance().set(getCacheKey(), gson.toJson(mList));
                }
//                mRecyclerView.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<E> call, Throwable t) {
                dismissLoadingLayout();
                swipeRefreshLayout.setRefreshing(false);
                showToastMsg("请检查您的网络连接");
                mPageIndex--;
//                mRecyclerView.setRefreshing(false);
                // if network is disconnected and mList is empty, show cache data
                if (mList.isEmpty()) {
                    if (getCacheKey() != null) {
                        String cache = CacheManager.getInstance().get(getCacheKey());
                        if (!TextUtils.isEmpty(cache)) {
                            List<I> list = JSON.parseArray(cache, getItemClass());
                            mList.addAll(list);
                            mAdapter.notifyDataSetChanged();
                            showListViewOrEmptyView();
                        } else {
                            showNetworkErrorLayout();
                        }
                    } else {
                        showNetworkErrorLayout();
                    }
                }
            }
        });*/
    }

    public void showListViewOrEmptyView() {
        if (mList.isEmpty()) {
            mEmptyLayout.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
            mNetworkErrorLayout.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyLayout.setVisibility(View.GONE);
            mNetworkErrorLayout.setVisibility(View.GONE);
        }
    }

    public void showNetworkErrorLayout() {
        mNetworkErrorLayout.setVisibility(View.VISIBLE);
    }

    public void dismissNetworkErrorLayout() {
        mNetworkErrorLayout.setVisibility(View.GONE);
    }

    public void showLoadingLayout() {
        mLoadingLayout.setVisibility(View.VISIBLE);
    }

    public void dismissLoadingLayout() {
        mLoadingLayout.setVisibility(View.GONE);
    }

     /* Override following method to make subclass have different behavior */


    public abstract Observable<HttpResponse<List<I>>> getObservable();

    protected abstract Class<I> getItemClass();

    protected abstract void init();

    protected abstract String getCacheKey();

    protected abstract RecyclerView.Adapter getAdapter();
}
