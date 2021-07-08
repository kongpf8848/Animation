package com.github.kongpf8848.animation.activity.svga

import android.os.Bundle
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseActivity
import com.github.kongpf8848.animation.fragment.TitleFragment
import com.github.kongpf8848.animation.utils.LogUtils
import com.github.kongpf8848.animation.views.ZiroomTabView
import kotlinx.android.synthetic.main.activity_svga_ziroom.*

class ZiroomActivity : BaseActivity() {

    private var lastFragment: Fragment? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_svga_ziroom
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        ll_tab_view.setOnTabSelectedListener(object:ZiroomTabView.ZiroomTabSelectedListener{
            override fun onTabSelected(position: Int) {
                LogUtils.d(TAG, "onTabSelected() called with: position = $position")
                switchFragment(position)
            }

            override fun onTabUnselected(position: Int) {
                LogUtils.d(TAG, "onTabUnselected() called with: position = $position")
            }

            override fun onTabReselected(position: Int) {
                LogUtils.d(TAG, "onTabReselected() called with: position = $position")
            }
        })
        if (savedInstanceState != null) {
            lastFragment = supportFragmentManager.getFragment(
                    savedInstanceState,
                    "LAST_FRAGMENT"
            )
        }
        if (lastFragment != null) {
            val lastPosition = getFragmentPosition(lastFragment)
            if (lastPosition != -1) {
                ll_tab_view.setSelectedPosition(position = lastPosition,invokeListener =false)
            }
            LogUtils.d("onCreateEnd,lastPosition:$lastPosition,lastFragment=$lastFragment")
        } else {
            Looper.myQueue().addIdleHandler {
                LogUtils.d("onCreateEnd,switchFragment:0")
                ll_tab_view.setSelectedPosition(0)
                false
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (lastFragment != null) {
            supportFragmentManager.putFragment(
                    outState,
                    "LAST_FRAGMENT",
                    lastFragment!!
            )
            LogUtils.d(TAG,"onSaveInstanceState:$lastFragment")
        }
        super.onSaveInstanceState(outState)
    }

    /**
     * 获取Fragment对应的位置
     *
     * @param fragment
     * @return
     */
    private fun getFragmentPosition(fragment: Fragment?): Int {
        var position = -1
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
        LogUtils.d(TAG,"switchFragment:$position")
        var lastPosition = -1
        if (lastFragment != null) {
            lastPosition = getFragmentPosition(lastFragment)
            if (lastPosition == position) {
                return
            }
        }
        val ft = supportFragmentManager.beginTransaction()
        var newFragment = supportFragmentManager.findFragmentByTag(position.toString())
        if (newFragment == null) {
            when (position) {
                0 -> newFragment = TitleFragment.getInstance(getString(R.string.ziroom_tab_home))
                1 -> newFragment = TitleFragment.getInstance(getString(R.string.ziroom_tab_search))
                2 -> newFragment = TitleFragment.getInstance(getString(R.string.ziroom_tab_service))
                3 -> newFragment = TitleFragment.getInstance(getString(R.string.ziroom_tab_lvju))
                4 -> newFragment = TitleFragment.getInstance(getString(R.string.ziroom_tab_my))
            }
        }
        if (newFragment != null && !newFragment.isAdded) {
            ft.add(R.id.fl_container, newFragment, "${position}")
        }
        if (lastFragment != null) {
            ft.hide(lastFragment!!)
        }
        if (newFragment != null) {
            ft.show(newFragment)
        }
        ft.commitAllowingStateLoss()
        lastFragment = newFragment
    }

}

