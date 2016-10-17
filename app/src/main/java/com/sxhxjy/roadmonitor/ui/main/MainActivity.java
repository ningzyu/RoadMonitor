package com.sxhxjy.roadmonitor.ui.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.CacheManager;
import com.sxhxjy.roadmonitor.base.MyApplication;
import com.sxhxjy.roadmonitor.util.ActivityUtil;
import com.sxhxjy.roadmonitor.view.TakePictureActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Main activity
 *
 * Git:https://github.com/michaelzhaofuning/RoadMonitor.git
 *
 * @author Michael Zhao
 */
public class MainActivity extends BaseActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private static final int FRAGMENTS_IN_VIEW_PAGER = 5;
    private long firstTimeOfExit = 0;
    private List<Fragment> fragments = new ArrayList<>();
    private LinearLayout bar0, bar1, bar2, bar3, bar4;
    private TextView textViewBar0, textViewBar1, textViewBar2, textViewBar3, textViewBar4;
    private ImageView imageViewBar0, imageViewBar1, imageViewBar2, imageViewBar3, imageViewBar4;

    private PagerAdapter mPagerAdapter = new FragmentPagerAdapter(getFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return FRAGMENTS_IN_VIEW_PAGER;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        String stationName = getIntent().getStringExtra("stationName");
        String stationId = getIntent().getStringExtra("stationId");
        // read from local
        if (stationId == null) {
            stationId = MyApplication.getMyApplication().getSharedPreference().getString("stationId", "");
            stationName = MyApplication.getMyApplication().getSharedPreference().getString("stationName", "");
        }

        initView();

        fragments.add(new HomeFragment());
        Fragment monitorFragment = new MonitorFragment();
        Bundle b = new Bundle();
        b.putString("stationName", stationName);
        b.putString("stationId", stationId);
        monitorFragment.setArguments(b);
        fragments.add(monitorFragment);
        fragments.add(new DataAnalysisFragment());
        fragments.add(new AlertFragment());
        fragments.add(new MyFragment());

        mViewPager.setOffscreenPageLimit(FRAGMENTS_IN_VIEW_PAGER);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setAdapter(mPagerAdapter);
        selectedBar(0);
        fragments.get(0).setUserVisibleHint(true);
    }


    private void initView() {
        bar0 = (LinearLayout) findViewById(R.id.bar0);
        bar1 = (LinearLayout) findViewById(R.id.bar1);
        bar2 = (LinearLayout) findViewById(R.id.bar2);
        bar3 = (LinearLayout) findViewById(R.id.bar3);
        bar4 = (LinearLayout) findViewById(R.id.bar4);
        bar0.setOnClickListener(this);
        bar1.setOnClickListener(this);
        bar2.setOnClickListener(this);
        bar3.setOnClickListener(this);
        bar4.setOnClickListener(this);
        textViewBar0 = (TextView) findViewById(R.id.textView_bar0);
        textViewBar1 = (TextView) findViewById(R.id.textView_bar1);
        textViewBar2 = (TextView) findViewById(R.id.textView_bar2);
        textViewBar3 = (TextView) findViewById(R.id.textView_bar3);
        textViewBar4 = (TextView) findViewById(R.id.textView_bar4);

        imageViewBar0 = (ImageView) findViewById(R.id.imageView_bar0);
        imageViewBar1 = (ImageView) findViewById(R.id.imageView_bar1);
        imageViewBar2 = (ImageView) findViewById(R.id.imageView_bar2);
        imageViewBar3 = (ImageView) findViewById(R.id.imageView_bar3);
        imageViewBar4 = (ImageView) findViewById(R.id.imageView_bar4);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

    }


    private void selectedBar(int i) {
        resetBar();
        switch (i) {
            case 0:
                mViewPager.setCurrentItem(0, false);
                textViewBar0.setTextColor(getResources().getColor(R.color.colorPrimary));
                imageViewBar0.setImageResource(R.drawable.bar01);
                break;
            case 1:
                mViewPager.setCurrentItem(1, false);
                textViewBar1.setTextColor(getResources().getColor(R.color.colorPrimary));
                imageViewBar1.setImageResource(R.drawable.bar11);
                break;
            case 2:
                imageViewBar2.setImageResource(R.drawable.bar21);
                mViewPager.setCurrentItem(2, false);
                textViewBar2.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 3:
                imageViewBar3.setImageResource(R.drawable.bar31);
                mViewPager.setCurrentItem(3, false);
                textViewBar3.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 4:
                imageViewBar4.setImageResource(R.drawable.bar41);
                mViewPager.setCurrentItem(4, false);
                textViewBar4.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            default:
        }
    }

    private void resetBar() {
        imageViewBar0.setImageResource(R.drawable.bar00);
        imageViewBar1.setImageResource(R.drawable.bar10);
        imageViewBar2.setImageResource(R.drawable.bar20);
        imageViewBar3.setImageResource(R.drawable.bar30);
        imageViewBar4.setImageResource(R.drawable.bar40);

        textViewBar0.setTextColor(getResources().getColor(R.color.default_color));
        textViewBar1.setTextColor(getResources().getColor(R.color.default_color));
        textViewBar2.setTextColor(getResources().getColor(R.color.default_color));
        textViewBar3.setTextColor(getResources().getColor(R.color.default_color));
        textViewBar4.setTextColor(getResources().getColor(R.color.default_color));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bar0:
                selectedBar(0);
                break;
            case R.id.bar1:
                selectedBar(1);
                break;
            case R.id.bar2:
                selectedBar(2);
                break;
            case R.id.bar3:
                selectedBar(3);
                break;
            case R.id.bar4:
                selectedBar(4);
                break;
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - firstTimeOfExit > 2000) {
                showToastMsg("再按一次退出程序");
                firstTimeOfExit = currentTime;
            } else {
                MyApplication.getMyApplication().exit();
            }
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i) {
            case 0:
                selectedBar(0);
                break;
            case 1:
                selectedBar(1);
                break;
            case 2:
                selectedBar(2);
                break;
            case 3:
                selectedBar(3);
                break;
            case 4:
                selectedBar(4);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public void logout(View view) {
        new AlertDialog.Builder(this).setTitle("退出登录").setMessage("确定要退出登录？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CacheManager.getInstance().remove("login");
                ActivityUtil.startActivityForResult(MainActivity.this, LoginActivity.class);
                finish();
            }
        }).setNegativeButton("取消", null).show();
    }

    public void showAlert(View view) {
        selectedBar(3);
    }

    public void test(View view) {
        ActivityUtil.startActivityForResult(this, TakePictureActivity.class);
    }

    public void changePassword(View view) {
        ActivityUtil.startActivityForResult(this, RegisterActivity.class);
    }
}
