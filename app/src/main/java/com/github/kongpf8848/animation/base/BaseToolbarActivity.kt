package com.github.kongpf8848.animation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_toolbar.*

abstract class BaseToolbarActivity : BaseActivity() {

    private var fl_content: FrameLayout? = null

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        setupToolbar()
    }

    override fun statusBarColor(): Int {
        return R.color.colorPrimary
    }

    override fun statusBarDarkFont(): Boolean {
        return false
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        val view = LayoutInflater.from(this).inflate(layoutResID, null)
        addContentView(view)
    }

    override fun setContentView(view: View) {
        addContentView(view)
    }

    private fun addContentView(view: View) {
        if (fl_content == null) {
            super.setContentView(R.layout.activity_toolbar)
            fl_content = findViewById(R.id.fl_content)
        }
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        fl_content!!.addView(view, layoutParams)
    }

    private fun setupToolbar() {
        toolbar.title = title
        toolbar.setNavigationOnClickListener { finish() }
    }
}