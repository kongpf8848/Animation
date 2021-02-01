package com.github.kongpf8848.animation.activity;

import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.adapter.ViewPagerAdapter;
import com.github.kongpf8848.animation.splash.SplashLayoutInflater;
import com.github.kongpf8848.animation.splash.SplashView;
import com.github.kongpf8848.animation.widget.CircleIndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengf on 2016/12/15.
 */

public class SplashActivity extends BaseActivity {

    private List<SplashView> viewList = new ArrayList<>();
    private int[] layoutIdList = new int[]{
            R.layout.item_guide_page_one,
            R.layout.item_guide_page_two,
            R.layout.item_guide_page_three,
            R.layout.item_guide_page_four
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected boolean enableStatusBar() {
        return false;
    }

    @Override
    protected void customInitStatusBar() {
        initStatusBar(R.id.btn_close);
    }

    @Override
    protected void onCreateEnd(Bundle savedInstanceState){
        super.onCreateEnd(savedInstanceState);
        initGuide();
        initViewPager();
        Looper.myQueue().addIdleHandler(() -> {
             startAnimation(0);
            return false;
        });
    }

    /**
     * 初始化向导页面
     */
    private void initGuide() {
        LayoutInflater inflater=LayoutInflater.from(this);
        for (int layoutId : layoutIdList) {
            final List<View>list=new ArrayList<>();
            SplashView splashView = (SplashView)
                    new SplashLayoutInflater(inflater, this, () -> list)
                    .inflate(layoutId, null);
            splashView.setViews(list);
            this.viewList.add(splashView);
        }
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        CircleIndicatorView circle_indicator = this.findViewById(R.id.circle_indicator);
        ViewPager viewPager = this.findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this.viewList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                for (int index = 0; index < viewList.size(); index++) {
                    if (index == position) {
                        startAnimation(index);
                    } else {
                        resetAnimation(index);
                    }
                }
            }

        });
        circle_indicator.setUpWithViewPager(viewPager);
    }

    /***
     * 开启动画
     * @param position
     */
    private void startAnimation(int position){
       viewList.get(position).startAnimation();
    }

    /**
     * 重置动画
     * @param position
     */
    private void resetAnimation(int position){
        viewList.get(position).resetAnimation();
    }

    /**
     * 进入主页面
     */
    public void enterMain(View v) {
        startActivity(MainActivity.class);
        finish();
    }
}
