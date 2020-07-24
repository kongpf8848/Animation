package com.github.kongpf8848.animation.adapter;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by pengf on 2016/12/15.
 */

public class ViewPagerAdapter extends PagerAdapter {
    public List<View> viewList;

    public ViewPagerAdapter(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        if (viewList != null) {
            return viewList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(View container, int position) {

        if (this.viewList.get(position).getParent() == null)
        {
            ((ViewPager)container).addView(this.viewList.get(position), 0);
        }
        return this.viewList.get(position);

    }
    @Override
    public void destroyItem(View container, int position, Object object)
    {
        ((ViewPager)container).removeView(this.viewList.get(position));
    }
}
