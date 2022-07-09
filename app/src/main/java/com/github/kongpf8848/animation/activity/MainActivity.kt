package com.github.kongpf8848.animation.activity

import android.os.Bundle
import android.view.animation.*
import androidx.recyclerview.widget.GridLayoutManager
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.adapter.MainAdapter
import com.github.kongpf8848.animation.base.BaseToolbarActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_toolbar.*

class MainActivity : BaseToolbarActivity() {

    private val items = listOf(
            Triple("逐帧动画",R.drawable.logo_frame, FrameAnimActivity::class.java),
            Triple("补间动画", R.drawable.logo_tween,TweenAnimActivity::class.java),
            Triple("属性动画", R.drawable.logo_property,PropertyAnimActivity::class.java),
            Triple("Lottie动画", R.drawable.logo_lottie,LottieAnimActivity::class.java),
            Triple("Telegram动画",R.drawable.logo_telegram, TelegramActivity::class.java),
            Triple("转场动画", R.drawable.logo_transition,TransitionActivity::class.java),
            Triple("GIF动画",R.drawable.logo_gif, GifActivity::class.java),
            Triple("SVGA动画", R.drawable.logo_svga,SVGAActivity::class.java)
    )

    override fun enableStatusBar(): Boolean {
        return false
    }

    public override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        toolbar?.navigationIcon = null

        rv_main.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2, GridLayoutManager.VERTICAL, false)
            adapter = MainAdapter(items)
            layoutAnimation = LayoutAnimationController(AnimationSet(true).apply {
                addAnimation(AlphaAnimation(0.0f, 1.0f))
                addAnimation(TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                        -1.0f, Animation.RELATIVE_TO_SELF, 0.0f))
                duration=500
            })
        }
    }
}