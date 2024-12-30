package com.github.kongpf8848.animation.activity.tween.translate

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseActivity
import com.github.kongpf8848.animation.utils.MainHandler
import com.github.kongpf8848.animation.utils.ShakeListener

class ShakeActivity : BaseActivity() {

    private var mShakeListener: ShakeListener? = null
    private var mVibrator: Vibrator? = null
    lateinit var iv_shake:View
    lateinit var shakeUp:View
    lateinit var shakeDown:View

    override fun getLayoutId(): Int {
        return R.layout.activity_shake
    }

    override fun statusBarColor(): Int {
        return R.color.black
    }

    override fun statusBarDarkFont(): Boolean {
        return false
    }

    override fun navigationBarColor(): Int {
        return R.color.black
    }


    override fun navigationBarDarkIcon(): Boolean {
        return false
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        iv_shake=findViewById(R.id.iv_shake)
        shakeUp=findViewById(R.id.shakeUp)
        shakeDown=findViewById(R.id.shakeDown)
        iv_shake.setOnClickListener {
            startAnimiation()
        }
        mVibrator = application.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        mShakeListener = ShakeListener(this)
        .apply {
            setOnShakeListener(object : ShakeListener.OnShakeListener {
                override fun onShake() {
                    stop()
                    startAnimiation()
                    startVibrate()
                    MainHandler.getInstance().postDelayed({
                        Toast.makeText(applicationContext, "没有摇到附近的人", Toast.LENGTH_SHORT).show()
                        mVibrator?.cancel()
                        start()
                    }, 2000)
                }

            })

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mShakeListener?.stop()
    }

    private fun startAnimiation() {
        val animUp = AnimationUtils.loadAnimation(this, R.anim.shake_up)
        shakeUp.startAnimation(animUp)
        val animDown = AnimationUtils.loadAnimation(this, R.anim.shake_down)
        shakeDown.startAnimation(animDown)
    }

    private fun startVibrate() {
        mVibrator?.vibrate(longArrayOf(500, 200, 500, 200), -1)
    }

}