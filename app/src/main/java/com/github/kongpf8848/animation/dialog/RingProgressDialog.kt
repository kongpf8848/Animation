package com.github.kongpf8848.animation.dialog

import android.content.Context
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseDialogFragment

class RingProgressDialog(context: Context):BaseDialogFragment(ctx = context){

    override fun getLayoutId(): Int {
        return R.layout.dialog_ring_progress
    }

}