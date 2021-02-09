package com.github.kongpf8848.animation.activity.tween.translate

import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.activity.BaseActivity
import com.github.kongpf8848.animation.heart.LiveHeartTask.HeartListener
import com.github.kongpf8848.animation.heart.LiveHeartTaskManagerThread
import kotlinx.android.synthetic.main.activity_heart.*
import java.util.*

class HeartActivity : BaseActivity(), HeartListener {
    private val handle: Handler = object : Handler() {
        override fun handleMessage(message: Message) {
            heart_layout.addFavor()
        }
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        val downloadTaskManagerThread = LiveHeartTaskManagerThread()
        Thread(downloadTaskManagerThread).start()
        startAnimation()
    }

    public override fun getLayoutId(): Int {
        return R.layout.activity_heart
    }

    private fun startAnimation() {
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                addHeart()
            }
        }, 0, 100)
    }

    override fun addHeart() {
        handle.sendEmptyMessageDelayed(0, 500)
    }
}