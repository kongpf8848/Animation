package com.github.kongpf8848.animation.activity.viewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.BaseActivity;
import com.github.kongpf8848.animation.adapter.PageAdapter;

import java.util.ArrayList;

public class XhsActivity extends BaseActivity {

    @Override
    protected boolean enableStatusBar() {
        return false;
    }

    @Override
    protected void customInitStatusBar() {
        super.customInitStatusBar();
        setStatusBarColorInt(Color.TRANSPARENT,true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xhs;
    }

    @Override
    protected void onCreateEnd(Bundle savedInstanceState){
        super.onCreateEnd(savedInstanceState);
        ViewPager viewPager =  findViewById(R.id.view_pager);
        ImageView ivWomen = findViewById(R.id.iv_woman);
        ArrayList<Fragment> fragments = createFragments();
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pageAdapter);
        ViewPagerHelper.newInstance().startListener(viewPager, ivWomen,fragments);
    }

    private ArrayList<Fragment> createFragments() {
        ArrayList<Fragment> pageFragments = new ArrayList<>();
        pageFragments.add(PageFragment.newInstance(R.layout.view_intro_1));
        pageFragments.add(PageFragment.newInstance(R.layout.view_intro_2));
        pageFragments.add(PageFragment.newInstance(R.layout.view_intro_3));
        pageFragments.add(PageFragment.newInstance(R.layout.view_intro_4));
        pageFragments.add(PageFragment.newInstance(R.layout.view_intro_5));
        pageFragments.add(PageFragment.newInstance(R.layout.view_login));
        return pageFragments;
    }
}
