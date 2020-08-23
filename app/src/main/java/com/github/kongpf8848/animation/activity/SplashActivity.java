package com.github.kongpf8848.animation.activity;

import android.graphics.Color;
import android.os.Looper;

import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.adapter.ViewPagerAdapter;
import com.github.kongpf8848.animation.splash.SplashLayoutInflater;
import com.github.kongpf8848.animation.splash.SplashView;
import com.kongpf.commonhelper.ScreenHelper;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;

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
    protected void initData() {
        super.initData();
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
        MagicIndicator magicIndicator = this.findViewById(R.id.magic_indicator);
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

        CircleNavigator circleNavigator = new CircleNavigator(this);
        circleNavigator.setCircleColor(Color.WHITE);
        circleNavigator.setStrokeWidth(ScreenHelper.dp2px(this, 1));
        circleNavigator.setCircleCount(viewList.size());
        circleNavigator.setRadius(ScreenHelper.dp2px(this, 3));
        circleNavigator.setCircleSpacing(ScreenHelper.dp2px(this, 10));
        circleNavigator.setTouchable(false);

        magicIndicator.setNavigator(circleNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
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
