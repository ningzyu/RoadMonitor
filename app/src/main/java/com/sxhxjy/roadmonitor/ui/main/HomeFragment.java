package com.sxhxjy.roadmonitor.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;

/**
 * 2016/9/26
 *  BPUBZ-HB3RQ-5SB5M-GLB4U-2A4QF-E7FT7
 * @author Michael Zhao
 */

public class HomeFragment extends BaseFragment {

    private MapView mapview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolBar(view, "首页", false);

        mapview = (MapView) view.findViewById(R.id.map_view);
        mapview.onCreate(savedInstanceState);
        TencentMap tencentMap = mapview.getMap();
        LatLng latLng = new LatLng(37.795034, 112.546477);
        tencentMap.setCenter(latLng);
        tencentMap.setZoom(17);
        Marker marker = tencentMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("清控")
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory
                        .defaultMarker())
                .draggable(true));
        marker.showInfoWindow();
    }

    @Override
    public void onDestroy() {
        mapview.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        mapview.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mapview.onResume();
        super.onResume();
    }

    @Override
    public void onStop() {
        mapview.onStop();
        super.onStop();
    }
}
