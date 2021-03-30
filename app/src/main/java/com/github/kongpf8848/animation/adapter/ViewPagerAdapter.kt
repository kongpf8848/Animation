package com.github.kongpf8848.animation.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class ViewPagerAdapter(val viewList: List<View>) : PagerAdapter() {

    private val TAG = "ViewPagerAdapter"

    override fun getCount(): Int {
        Log.d(TAG, "getCount() called")
        return viewList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        Log.d(TAG, "isViewFromObject() called with: view = $view, object = $`object`")
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        Log.d(TAG, "instantiateItem() called with: container = $container, position = $position")
        container.addView(viewList[position])
        return viewList[position]
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        Log.d(TAG, "destroyItem() called with: container = $container, position = $position, object = $`object`")
        container.removeView(`object` as View)
    }

}