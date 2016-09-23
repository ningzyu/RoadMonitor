package com.sxhxjy.roadmonitor.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.SimpleListAdapter;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.BaseListFragment;
import com.sxhxjy.roadmonitor.base.CacheManager;
import com.sxhxjy.roadmonitor.base.HttpResponse;
import com.sxhxjy.roadmonitor.entity.GroupTree;
import com.sxhxjy.roadmonitor.entity.LoginData;
import com.sxhxjy.roadmonitor.entity.SimpleItem;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 2016/9/18
 *
 * @author Michael Zhao
 */
public class StationListActivity extends BaseActivity {
    public static final int REQUEST_CODE = 47;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        getFragmentManager().beginTransaction()
                .add(R.id.container, new StationListFragment()).commit();
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

    /**
     * 2016/9/18
     *
     * @author Michael Zhao
     */
    public static class StationListFragment extends BaseListFragment<SimpleItem> {

        @Override
        public Observable<HttpResponse<List<SimpleItem>>> getObservable() {
            return null;
        }

        @Override
        public void getMessage() {
            getHttpService().getGroups(new Gson().fromJson(CacheManager.getInstance().get("login"), LoginData.class).getGid()).subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .map(new Func1<HttpResponse<List<GroupTree>>, List<GroupTree>>() {
                        @Override
                        public List<GroupTree> call(HttpResponse<List<GroupTree>> listHttpResponse) {
                            return listHttpResponse.getData();
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<GroupTree>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(List<GroupTree> groupTrees) {
                            ((SimpleListAdapter) mAdapter).inject(groupTrees);
                            mAdapter.notifyDataSetChanged();
                        }
                    });
        }



        @Override
        protected Class<SimpleItem> getItemClass() {
            return null;
        }


        @Override
        protected void init() {
            initToolBar(getView(), "选择监测点", true);
            getMessage();
            mPullRefreshLoadLayout.enableRefresh(false);
        }

        @Override
        protected String getCacheKey() {
            return null;
        }

        @Override
        protected RecyclerView.Adapter getAdapter() {
            return new SimpleListAdapter(this);
        }
    }
}
