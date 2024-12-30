package com.github.kongpf8848.animation.activity

import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.adapter.ViewPagerAdapter
import com.github.kongpf8848.animation.base.BaseActivity
import com.github.kongpf8848.animation.splash.SplashLayoutInflater
import com.github.kongpf8848.animation.splash.SplashView
import com.github.kongpf8848.animation.views.CircleIndicatorView

class GuideActivity : BaseActivity() {

    private val viewList= mutableListOf<SplashView>()

    private val layoutIdList = intArrayOf(
            R.layout.item_guide_page_one,
            R.layout.item_guide_page_two,
            R.layout.item_guide_page_three,
            R.layout.item_guide_page_four
    )

    lateinit var iv_close:ImageView
    lateinit var view_pager:ViewPager
    lateinit var circle_indicator:CircleIndicatorView

    override fun getLayoutId(): Int {
        return R.layout.activity_guide
    }

    override fun enableStatusBar(): Boolean {
        return false
    }

    override fun customInitStatusBar() {
        initStatusBar(R.id.iv_close)
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        iv_close=findViewById(R.id.iv_close)
        view_pager=findViewById(R.id.view_pager)
        circle_indicator=findViewById(R.id.circle_indicator)
        iv_close.setOnClickListener {
            enterMain(it)
        }
        initGuide()
        initViewPager()
        Looper.myQueue().addIdleHandler {
            startAnimation(0)
            false
        }
    }

    /**
     * 初始化向导页面
     */
    private fun initGuide() {
        for (layoutId in layoutIdList) {
            val list= mutableListOf<View>()
            val splashView = SplashLayoutInflater(LayoutInflater.from(this), this,list).inflate(layoutId, null,false) as SplashView
            splashView.views=(list)
            viewList.add(splashView)
        }
    }

    /**
     * 初始化ViewPager
     */
    private fun initViewPager() {
        view_pager.adapter = ViewPagerAdapter(viewList)
        view_pager.addOnPageChangeListener(object : SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                for (index in viewList.indices) {
                    if (index == position) {
                        startAnimation(index)
                    } else {
                        resetAnimation(index)
                    }
                }
            }
        })
        circle_indicator.setUpWithViewPager(view_pager)
    }

    /***
     * 开启动画
     * @param position
     */
    private fun startAnimation(position: Int) {
        viewList[position].startAnimation()
    }

    /**
     * 重置动画
     * @param position
     */
    private fun resetAnimation(position: Int) {
        viewList[position].resetAnimation()
    }

    fun enterMain(view:View?){
        startActivity(MainActivity::class.java,true)
    }

}