package com.github.kongpf8848.animation.activity

import android.os.Bundle
import android.view.animation.*
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.github.kongpf8848.animation.BuildConfig
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.adapter.MainAdapter
import com.github.kongpf8848.animation.base.BaseToolbarActivity
import com.github.kongpf8848.animation.utils.LogUtils
import com.github.kongpf8848.animation.views.CircleIndicatorView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError


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

    lateinit var rv_main: RecyclerView
    lateinit var ad_container: FrameLayout

    override fun enableStatusBar(): Boolean {
        return false
    }

    public override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        rv_main=findViewById(R.id.rv_main)
        ad_container=findViewById(R.id.ad_container)
        toolbar.navigationIcon = null
        loadAd()

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

    private fun loadAd(){
        if(BuildConfig.DEBUG){
            return
        }
        val adView = AdView(this).apply {
            adSize = AdSize.BANNER
            adUnitId="ca-app-pub-2515116623705756/6112792218"
        }
        adView.adListener=object: AdListener() {
            override fun onAdClicked() {
                LogUtils.d("JACK8", "onAdClicked")
            }

            override fun onAdClosed() {
                LogUtils.d("JACK8", "onAdClosed")
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                LogUtils.d("JACK8", "onAdFailedToLoad:$adError")
            }

            override fun onAdImpression() {
                LogUtils.d("JACK8", "onAdClosed")
            }

            override fun onAdLoaded() {
                LogUtils.d("JACK8", "onAdLoaded")
                if (!isDestroyed && !isFinishing) {
                    ad_container.removeAllViews()
                    ad_container.addView(adView)
                }
            }

            override fun onAdOpened() {
                LogUtils.d("JACK8", "onAdOpened")
            }
        }
        adView.loadAd(AdRequest.Builder().build())
    }
}