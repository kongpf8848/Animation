package com.github.kongpf8848.animation.dialog

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_share.*

class ShareDialog(context: Context):BaseDialogFragment(ctx =context,
        width =WindowManager.LayoutParams.MATCH_PARENT,
        height=WindowManager.LayoutParams.WRAP_CONTENT,
        gravity = Gravity.BOTTOM
) {
    override fun getLayoutId(): Int {
        return R.layout.dialog_share
    }

    override fun initView() {
        super.initView()
        listOf(iv_qq_zone,iv_qq,iv_wechat,iv_wechat_friend,iv_weibo).forEach {
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