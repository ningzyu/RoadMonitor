package com.sxhxjy.roadmonitor.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.base.HttpResponse;
import com.sxhxjy.roadmonitor.base.MyApplication;
import com.sxhxjy.roadmonitor.entity.GroupTree;
import com.sxhxjy.roadmonitor.entity.SimpleItem;
import com.sxhxjy.roadmonitor.entity.Station;
import com.sxhxjy.roadmonitor.util.ActivityUtil;

import java.security.AlgorithmParameterGenerator;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 2016/9/18
 *
 * @author Michael Zhao
 */
public class SimpleListAdapter extends RecyclerView.Adapter<SimpleListAdapter.ViewHolder> implements View.OnClickListener {

    private List<GroupTree> data;
    private BaseFragment mFragment;
    private List<SimpleItem> mList = new ArrayList<>();
    private boolean isStation;

    private List<GroupTree> currentGroup;
    public SimpleListAdapter(BaseFragment fragment) {
        mFragment = fragment;
    }

    public void inject(List<GroupTree> list) {
        this.data = list;
        if (data != null)
            for (GroupTree groupTree : data) {
                SimpleItem item = new SimpleItem();
                item.setTitle(groupTree.name);
                item.setId(groupTree.id);
                mList.add(item);
            }
        currentGroup = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
        holder.title.setText(mList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        int p = (int) v.getTag();
        if (isStation) {
            Intent intent = new Intent();
            intent.putExtra("stationId", mList.get(p).getId());
            intent.putExtra("stationName", mList.get(p).getTitle());
            mFragment.getActivity().setResult(Activity.RESULT_OK, intent);
            mFragment.getActivity().finish();
        }

        if (currentGroup.get(p).childrenGroup != null) {
            mList.clear();
            for (GroupTree childrenGroup : currentGroup.get(p).childrenGroup) {
                SimpleItem item = new SimpleItem();
                item.setTitle(childrenGroup.name);
                item.setId(childrenGroup.id);
                mList.add(item);
            }
            currentGroup = currentGroup.get(p).childrenGroup;
            notifyDataSetChanged();
        } else {
            getStations(mList.get(p).getId());
        }
    }

    public void getStations(String groupId) {
        MyApplication.getMyApplication().getHttpService().getStations(groupId).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Func1<HttpResponse<List<Station>>, List<Station>>() {
                    @Override
                    public List<Station> call(HttpResponse<List<Station>> listHttpResponse) {
                        return listHttpResponse.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Station>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("retrofit" , e.toString());
                    }

                    @Override
                    public void onNext(List<Station> stations) {
                        mList.clear();
                        for (Station station : stations) {
                            SimpleItem item = new SimpleItem();
                            item.setTitle(station.name);
                            item.setId(station.id);
                            mList.add(item);
                        }
                        notifyDataSetChanged();
                        isStation = true;
                    }
                });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, location, date, status;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
