package com.github.kongpf8848.animation.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.github.kongpf8848.xsdk.ui.activity.BaseActivity;
import com.github.kongpf8848.animation.bean.GuideEntity;
import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.adapter.ViewPagerAdapter;
import com.kongpf.commonhelper.ScreenHelper;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengf on 2016/12/15.
 */

public class SplashActivity extends BaseActivity implements View.OnClickListener {

    private boolean bFromAbout = false;
    private List<View> viewList = new ArrayList<View>();
    private List<GuideEntity.GuideItem> guideItemList = new ArrayList<GuideEntity.GuideItem>();
    private LayoutInflater layoutInflater;

    private MagicIndicator mMagicIndicator;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private int height;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            super.onCreate(savedInstanceState);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void initData() {
        super.initData();
        this.height= ScreenHelper.getScreenHeight(this);
        this.bFromAbout = getIntent().getBooleanExtra("key_is_about", false);
        layoutInflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        init();

        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                GuideEntity.a(guideItemList.get(viewPager.getCurrentItem()));
                return false;
            }
        });
    }


    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        g();
    }

    private void g()
    {

       if (height < 1280)
        {
            int i = (int)(0.6F * height) - ScreenHelper.dp2px(this, 388.0F);
            RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)this.viewPager.getLayoutParams();
            if (localLayoutParams != null)
            {
                localLayoutParams.setMargins(0, i, 0, 0);
                this.viewPager.setLayoutParams(localLayoutParams);
            }
        }
    }

    private void init()
    {
        initGuide1();
        initGuide2();
        initGuide3();
        initGuide4();
        this.mMagicIndicator = (MagicIndicator) this.findViewById(R.id.magic_indicator);
        this.viewPager = (ViewPager)this.findViewById(R.id.pager);
        this.adapter = new ViewPagerAdapter(this.viewList);
        this.viewPager.setAdapter(this.adapter);
        this.viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position)
            {
                  for(int i=0;i<viewList.size();i++)
                  {
                      if(i==position)
                      {
                          GuideEntity.a(guideItemList.get(i));
                      }
                      else
                      {
                          GuideEntity.b(guideItemList.get(i));
                      }
                  }
            }

        });

        CircleNavigator circleNavigator=new CircleNavigator(this);
        circleNavigator.setCircleColor(Color.WHITE);
        circleNavigator.setStrokeWidth(ScreenHelper.dp2px(this,1));
        circleNavigator.setCircleCount(4);
        circleNavigator.setRadius(ScreenHelper.dp2px(this,3));
        circleNavigator.setCircleSpacing(ScreenHelper.dp2px(this,10));
        circleNavigator.setTouchable(false);

        mMagicIndicator.setNavigator(circleNavigator);
        ViewPagerHelper.bind(mMagicIndicator, viewPager);

    }
    private GuideEntity.GuideItem initGuide1()
    {
        GuideEntity.GuideItem guideItem=new GuideEntity.GuideItem();
        guideItem.viewGroup=(ViewGroup)this.layoutInflater.inflate(R.layout.item_guide_page_one,null);
        guideItem.pic = guideItem.viewGroup.findViewById(R.id.pic);
        guideItem.text = guideItem.viewGroup.findViewById(R.id.text);
        guideItem.views=new ArrayList<View>();
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.sun));

        this.guideItemList.add(guideItem);
        this.viewList.add(guideItem.viewGroup);

        return guideItem;

    }
    private GuideEntity.GuideItem initGuide2()
    {
        GuideEntity.GuideItem guideItem=new GuideEntity.GuideItem();
        guideItem.viewGroup=(ViewGroup)this.layoutInflater.inflate(R.layout.item_guide_page_two,null);
        guideItem.pic=guideItem.viewGroup.findViewById(R.id.pic);
        guideItem.text=guideItem.viewGroup.findViewById(R.id.text);

        guideItem.views=new ArrayList<View>();
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.ZhongKeDa));
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.DongNanDaXue));
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.HuNanDaXue));
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.HaGongDa));
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.ZheJiangDaXue));
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.BeiJingDaXue));
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.ShangHaiJiaoDa));
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.WuHanDaXue));

        this.guideItemList.add(guideItem);
        this.viewList.add(guideItem.viewGroup);
        return guideItem;
    }
    private GuideEntity.GuideItem initGuide3()
    {
        GuideEntity.GuideItem guideItem=new GuideEntity.GuideItem();
        guideItem.viewGroup=(ViewGroup)this.layoutInflater.inflate(R.layout.item_guide_page_three,null);
        guideItem.pic=guideItem.viewGroup.findViewById(R.id.pic);
        guideItem.text=guideItem.viewGroup.findViewById(R.id.text);
        guideItem.certificate=guideItem.viewGroup.findViewById(R.id.certificate);

        guideItem.views=new ArrayList<View>();
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.balloon));

        this.guideItemList.add(guideItem);
        this.viewList.add(guideItem.viewGroup);
        return guideItem;
    }
    private GuideEntity.GuideItem initGuide4()
    {
        GuideEntity.GuideItem guideItem=new GuideEntity.GuideItem();
        guideItem.viewGroup=(ViewGroup)this.layoutInflater.inflate(R.layout.item_guide_page_four,null);
        guideItem.pic=guideItem.viewGroup.findViewById(R.id.pic);
        guideItem.text=guideItem.viewGroup.findViewById(R.id.text);

        guideItem.views=new ArrayList<View>();
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.lines));
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.discusssion));
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.homework));
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.teacher));
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.time));
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.video));
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.student_left));
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.student_middle));
        guideItem.views.add(guideItem.viewGroup.findViewById(R.id.student_right));

        this.guideItemList.add(guideItem);
        this.viewList.add(guideItem.viewGroup);

        if (!this.bFromAbout)
        {
            guideItem.viewGroup.setOnClickListener(this);
        }
        else
        {
            guideItem.viewGroup.findViewById(R.id.enter_button).setVisibility(View.GONE);
        }

        return guideItem;
    }

    @Override
    public void onClick(View v) {
        startActivity(MainActivity.class);
        finish();
    }
}
