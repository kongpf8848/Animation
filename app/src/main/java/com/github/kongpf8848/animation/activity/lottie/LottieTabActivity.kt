package com.github.kongpf8848.animation.activity.lottie

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.BaseActivity
import com.github.kongpf8848.animation.adapter.FragmentAdapter
import com.github.kongpf8848.animation.fragment.TitleFragment
import com.github.kongpf8848.animation.views.AnimationRadioView
import kotlinx.android.synthetic.main.activity_lottie_tab.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView
import java.util.*

abstract class LottieTabActivity : BaseActivity() {

    abstract fun getTabs(): Map<String, String>
    abstract fun getTabLayoutId():Int

    override fun getLayoutId(): Int {
        return R.layout.activity_lottie_tab
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {

        val tabs = getTabs()

        val fragmentList: MutableList<Fragment> = ArrayList()
        for ((key, _) in tabs) {
            val fragment = TitleFragment()
            fragment.arguments = Bundle().apply {
                putString("title", key)
            }
            fragmentList.add(fragment)
        }
        view_pager.adapter = FragmentAdapter(supportFragmentManager, fragmentList, tabs.keys.toList())

        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return tabs.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val commonPagerTitleView = CommonPagerTitleView(context)
                val customLayout = LayoutInflater.from(context).inflate(getTabLayoutId(), null, false)
                val tabText: TextView? = customLayout.findViewById(R.id.item_tab_text)
                val tabImage: AnimationRadioView? = customLayout.findViewById(R.id.item_tab_image)
                tabText?.text=tabs.keys.elementAt(index)
                tabImage?.setAnimation(tabs.values.elementAt(index))
                commonPagerTitleView.setContentView(customLayout)
                commonPagerTitleView.onPagerTitleChangeListener = object : CommonPagerTitleView.OnPagerTitleChangeListener {
                    override fun onSelected(index: Int, totalCount: Int) {
                        tabImage?.isChecked = true
                        tabText?.typeface = Typeface.DEFAULT_BOLD
                    }

                    override fun onDeselected(index: Int, totalCount: Int) {
                        tabImage?.isChecked = false
                        tabText?.typeface = Typeface.DEFAULT
                    }

                    override fun onLeave(index: Int, totalCount: Int, leavePercent: Float, leftToRight: Boolean) {}
                    override fun onEnter(index: Int, totalCount: Int, enterPercent: Float, leftToRight: Boolean) {}
                }
                commonPagerTitleView.setOnClickListener { view_pager.currentItem = index }
                return commonPagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator? {
                return null
            }
        }
        magic_indicator.navigator = commonNavigator
        ViewPagerHelper.bind(magic_indicator, view_pager)
    }


}