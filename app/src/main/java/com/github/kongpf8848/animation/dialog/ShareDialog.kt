package com.github.kongpf8848.animation.dialog

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseDialogFragment

class ShareDialog(context: Context) : BaseDialogFragment(
    ctx = context,
    width = WindowManager.LayoutParams.MATCH_PARENT,
    height = WindowManager.LayoutParams.WRAP_CONTENT,
    gravity = Gravity.BOTTOM
) {
    lateinit var iv_qq_zone: View
    lateinit var iv_qq: View
    lateinit var iv_wechat: View
    lateinit var iv_wechat_friend: View
    lateinit var iv_weibo: View
    override fun getLayoutId(): Int {
        return R.layout.dialog_share
    }

    override fun initView() {
        super.initView()
        iv_qq_zone = rootView!!.findViewById(R.id.iv_qq_zone)
        iv_qq = rootView!!.findViewById(R.id.iv_qq)
        iv_wechat = rootView!!.findViewById(R.id.iv_wechat)
        iv_wechat_friend = rootView!!.findViewById(R.id.iv_wechat_friend)
        iv_weibo = rootView!!.findViewById(R.id.iv_weibo)
        listOf(iv_qq_zone, iv_qq, iv_wechat, iv_wechat_friend, iv_weibo).forEach {
            doAnimation(it)
        }
    }

    private fun doAnimation(view: View) {

        val objectAnimator1 = ObjectAnimator.ofFloat(view, View.ROTATION, 0.0f, 45.0f).apply {
            duration = 250
            startDelay = 400
        }

        val objectAnimator2 = ObjectAnimator.ofFloat(view, View.ROTATION, 45.0f, -45.0f).apply {
            duration = 500
        }

        val objectAnimator3 = ObjectAnimator.ofFloat(view, View.ROTATION, -45.0f, 0.0f).apply {
            duration = 250
        }
        AnimatorSet().apply {
            playSequentially(objectAnimator1, objectAnimator2, objectAnimator3)
            start()
        }

    }
}