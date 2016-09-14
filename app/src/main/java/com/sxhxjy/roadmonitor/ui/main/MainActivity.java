package com.sxhxjy.roadmonitor.ui.main;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
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

import org.json.JSONObject;

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
    private static final int FRAGMENTS_IN_VIEW_PAGER = 3;
    private long firstTimeOfExit = 0;
    private List<Fragment> fragments = new ArrayList<>();
    private LinearLayout bar0, bar1, bar2, bar3, bar4;
    private TextView textViewBar0, textViewBar1, textViewBar2, textViewBar3;
    private ImageView imageViewBar0, imageViewBar1, imageViewBar2, imageViewBar3;

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

        initView();

        fragments.add(new MonitorListFragment());
        fragments.add(new MonitorListFragment());
        fragments.add(new MonitorListFragment());

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
        bar0.setOnClickListener(this);
        bar1.setOnClickListener(this);
        bar2.setOnClickListener(this);
        bar3.setOnClickListener(this);
        textViewBar0 = (TextView) findViewById(R.id.textView_bar0);
        textViewBar1 = (TextView) findViewById(R.id.textView_bar1);
        textViewBar2 = (TextView) findViewById(R.id.textView_bar2);
        textViewBar3 = (TextView) findViewById(R.id.textView_bar3);
        imageViewBar0 = (ImageView) findViewById(R.id.imageView_bar0);
        imageViewBar1 = (ImageView) findViewById(R.id.imageView_bar1);
        imageViewBar2 = (ImageView) findViewById(R.id.imageView_bar2);
        imageViewBar3 = (ImageView) findViewById(R.id.imageView_bar3);

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
        /*    case 3:
                imageViewBar3.setImageResource(R.drawable.bar31);
                mViewPager.setCurrentItem(3);
                textViewBar3.setTextColor(getResources().getColor(R.color.key_color));
                break;*/

            default:
        }
    }

    private void resetBar() {
        imageViewBar0.setImageResource(R.drawable.bar00);
        imageViewBar1.setImageResource(R.drawable.bar10);
        imageViewBar2.setImageResource(R.drawable.bar20);
//        imageViewBar3.setImageResource(R.drawable.bar30);

        textViewBar0.setTextColor(getResources().getColor(R.color.default_color));
        textViewBar1.setTextColor(getResources().getColor(R.color.default_color));
        textViewBar2.setTextColor(getResources().getColor(R.color.default_color));
        textViewBar3.setTextColor(getResources().getColor(R.color.default_color));
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
//                break;
            case 4:
                selectedBar(4);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

}
