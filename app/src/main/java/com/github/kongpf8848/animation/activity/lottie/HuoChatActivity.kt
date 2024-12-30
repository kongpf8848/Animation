package com.github.kongpf8848.animation.activity.lottie

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseActivity
import com.github.kongpf8848.animation.fragment.ChatFragment
import com.github.kongpf8848.animation.fragment.CommunityFragment
import com.github.kongpf8848.animation.fragment.MarketFragment
import com.github.kongpf8848.animation.fragment.MyFragment
import com.github.kongpf8848.animation.utils.LogUtils

/**
 * 火信
 */
class HuoChatActivity : BaseActivity() {

    private var lastFragment: Fragment? = null

    private val mNormalIconIds = intArrayOf(
            R.drawable.ic_tab_chats,
            R.drawable.ic_tab_markets,
            R.drawable.ic_tab_community,
            R.drawable.ic_tab_me
    )
    private val mSelectedFileNames = arrayOf(
            "ic_tab_chat.json",
            "ic_tab_market.json",
            "ic_tab_community.json",
            "ic_tab_me.json"
    )

    lateinit var iv_navi_chats: LottieAnimationView
    lateinit var iv_navi_market: LottieAnimationView
    lateinit var iv_navi_community: LottieAnimationView
    lateinit var iv_navi_me: LottieAnimationView

    companion object {
        const val TAB_CHAT = 0
        const val TAB_MARKET = 1
        const val TAB_COMMUNITY = 2
        const val TAB_ME = 3
        const val LAST_FRAGMENT = "LAST_FRAGMENT"
        const val POSITION_NO = -1
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_huo_chat
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        iv_navi_chats=findViewById(R.id.iv_navi_chats)
        iv_navi_market=findViewById(R.id.iv_navi_market)
        iv_navi_community=findViewById(R.id.iv_navi_community)
        iv_navi_me=findViewById(R.id.iv_navi_me)
        if (savedInstanceState != null) {
            lastFragment = supportFragmentManager.getFragment(
                    savedInstanceState,
                    LAST_FRAGMENT
            )
        }
        if (lastFragment != null) {
            val lastPosition = getFragmentPosition(lastFragment)
            if (lastPosition != POSITION_NO) {
                onTabSelected(position = lastPosition)
            }
            LogUtils.d("onCreateEnd,lastPosition:$lastPosition,lastFragment=$lastFragment")
        } else {
            LogUtils.d("onCreateEnd,switchFragment:0")
            switchFragment(TAB_CHAT)

        }

    }


    /**
     * 获取Fragment对应的位置
     *
     * @param fragment
     * @return
     */
    private fun getFragmentPosition(fragment: Fragment?): Int {
        var position = POSITION_NO
        if (fragment != null) {
            val tag = fragment.tag
            if (!TextUtils.isEmpty(tag)) {
                try {
                    position = tag!!.toInt()
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }
            }
        }
        return position
    }


    /**
     * 切换Fragment到指定位置
     *
     * @param position
     */
    private fun switchFragment(position: Int) {
        LogUtils.d("switchFragment:$position")
        var lastPosition = POSITION_NO
        if (lastFragment != null) {
            lastPosition = getFragmentPosition(lastFragment)
            if (lastPosition == position) {
                onTabReselected(position)
                return
            }
        }
        val ft = supportFragmentManager.beginTransaction()
        var newFragment = supportFragmentManager.findFragmentByTag(position.toString())
        if (newFragment == null) {
            LogUtils.d("initFragment:$position")
            when (position) {
                TAB_CHAT -> newFragment = ChatFragment()
                TAB_MARKET -> newFragment = MarketFragment()
                TAB_COMMUNITY -> newFragment = CommunityFragment()
                TAB_ME -> newFragment = MyFragment()
            }
        }
        if (newFragment != null && !newFragment.isAdded) {
            ft.add(R.id.fl_container, newFragment, "${position}")
        }
        if (lastFragment != null) {
            ft.hide(lastFragment!!)
            onTabUnselected(lastPosition)
        }
        if (newFragment != null) {
            ft.show(newFragment)
            onTabSelected(position)
        }
        ft.commitAllowingStateLoss()
        lastFragment = newFragment
    }

    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.ll_navi_chats -> {
                switchFragment(TAB_CHAT)
            }
            R.id.ll_navi_market -> {
                switchFragment(TAB_MARKET)
            }
            R.id.ll_navi_community -> {
                switchFragment(TAB_COMMUNITY)
            }
            R.id.ll_navi_me -> {
                switchFragment(TAB_ME)
            }
        }
    }

    /**
     * 从未选中->选中
     */
    private fun onTabSelected(position: Int) {
        LogUtils.d(TAG, "onTabSelected() called with: position = $position")
        getLottieAnimationView(position)?.apply {
            cancelAnimation()
            setAnimation(mSelectedFileNames[position])
            playAnimation()
        }
    }

    /**
     * 从选中->未选中
     */
    private fun onTabUnselected(position: Int) {
        LogUtils.d(TAG, "onTabUnselected() called with: position = $position")
        getLottieAnimationView(position)?.setImageResource(mNormalIconIds[position])
    }

    /**
     * 重复选中
     */
    private fun onTabReselected(position: Int) {
        LogUtils.d(TAG, "onTabReselected() called with: position = $position")
    }

    private fun getLottieAnimationView(position: Int):LottieAnimationView?{
        return when (position) {
            TAB_CHAT -> {
                iv_navi_chats
            }
            TAB_MARKET -> {
                iv_navi_market
            }
            TAB_COMMUNITY -> {
                iv_navi_community
            }
            TAB_ME -> {
                iv_navi_me
            }
            else -> null
        }
    }
}