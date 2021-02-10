package com.github.kongpf8848.animation.adapter

import android.view.View
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class ViewPagerAdapter(val viewList: List<View>) : PagerAdapter() {

    override fun getCount(): Int {
        return viewList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: View, position: Int): Any {
        if (viewList[position].parent == null) {
            (container as ViewPager).addView(viewList[position], 0)
        }
        return viewList[position]
    }

    override fun destroyItem(container: View, position: Int, `object`: Any) {
        (container as ViewPager).removeView(viewList[position])
    }

}