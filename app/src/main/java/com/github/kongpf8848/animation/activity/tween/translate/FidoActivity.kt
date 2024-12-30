package com.github.kongpf8848.animation.activity.tween.translate

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.github.kongpf8848.animation.R

class FidoActivity : Activity() {

    lateinit var fpt_dialog_bottom_cancel:View
    lateinit var finger_imageview:ImageView
    lateinit var fpt_dialog_hint_text:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fpt_dialog)
        fpt_dialog_bottom_cancel=findViewById(R.id.fpt_dialog_bottom_cancel)
        finger_imageview=findViewById(R.id.finger_imageview)
        fpt_dialog_hint_text=findViewById(R.id.fpt_dialog_hint_text)
        fpt_dialog_bottom_cancel.setOnClickListener {
            finger_imageview.setImageResource(R.drawable.finger_wrong)
            fpt_dialog_hint_text.setTextColor(Color.RED)
            val shake = AnimationUtils.loadAnimation(this, R.anim.shake)
            fpt_dialog_hint_text.startAnimation(shake)
        }
    }

}