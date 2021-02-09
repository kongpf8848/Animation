package com.github.kongpf8848.animation.activity.tween.translate

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.github.kongpf8848.animation.R
import kotlinx.android.synthetic.main.fpt_dialog.*

class FidoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fpt_dialog)
        fpt_dialog_bottom_cancel.setOnClickListener {
            finger_imageview.setImageResource(R.drawable.finger_wrong)
            fpt_dialog_hint_text.setTextColor(Color.RED)
            val shake = AnimationUtils.loadAnimation(this, R.anim.shake)
            fpt_dialog_hint_text.startAnimation(shake)
        }
    }

}