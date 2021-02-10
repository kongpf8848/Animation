package com.github.kongpf8848.animation.activity.viewpager

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.BaseActivity
import com.github.kongpf8848.animation.adapter.FragmentAdapter
import com.github.kongpf8848.animation.fragment.TitleFragment
import com.google.android.material.animation.ArgbEvaluatorCompat
import kotlinx.android.synthetic.main.activity_lottie_tab.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView.OnPagerTitleChangeListener
import java.util.*

class WeChatActivity : BaseActivity() {

    private val normalTextColor = Color.parseColor("#FF333333")
    private val selectedTextColor = Color.parseColor("#FF00FF00")
    private val CHANNELS = arrayOf("微信", "通讯录", "发现", "我")
    private val DRAWABLES = intArrayOf(R.drawable.weixin_normal, R.drawable.contact_list_normal, R.drawable.find_normal, R.drawable.weixin_normal)

    override fun getLayoutId(): Int {
        return R.layout.activity_lottie_tab
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        initViewPager()
        initMagicIndicator()
    }

    private fun initViewPager() {
        val fragmentList: MutableList<Fragment> = ArrayList()
        for (i in CHANNELS.indices) {
            val fragment = TitleFragment()
            fragment.arguments =  Bundle().apply {
                putString("title", CHANNELS[i])
            }
            fragmentList.add(fragment)
        }
        view_pager.adapter = FragmentAdapter(supportFragmentManager, fragmentList, Arrays.asList(*CHANNELS))
    }

    private fun initMagicIndicator() {
        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return CHANNELS.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val commonPagerTitleView = CommonPagerTitleView(context)
                val customLayout = LayoutInflater.from(context).inflate(R.layout.item_tab_wechat, null,false)
                val img = customLayout.findViewById<ImageView>(R.id.item_tab_image)
                img.setImageResource(DRAWABLES[index])
                val text = customLayout.findViewById<TextView>(R.id.item_tab_text)
                text.text = CHANNELS[index]
                text.setTextColor(normalTextColor)
                commonPagerTitleView.setContentView(customLayout)
                commonPagerTitleView.onPagerTitleChangeListener = object : OnPagerTitleChangeListener {
                    override fun onSelected(index: Int, totalCount: Int) {}
                    override fun onDeselected(index: Int, totalCount: Int) {}
                    override fun onLeave(index: Int, totalCount: Int, leavePercent: Float, leftToRight: Boolean) {
                        val color = ArgbEvaluatorCompat.getInstance().evaluate(leavePercent, selectedTextColor, normalTextColor)
                        text.setTextColor(color)
                        img.drawable.setTint(color)
                    }

                    override fun onEnter(index: Int, totalCount: Int, enterPercent: Float, leftToRight: Boolean) {
                        val color = ArgbEvaluatorCompat.getInstance().evaluate(enterPercent, normalTextColor, selectedTextColor)
                        text.setTextColor(color)
                        img.drawable.setTint(color)
                    }
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