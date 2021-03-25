package com.github.kongpf8848.animation.activity.lottie

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.Scroller
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.airbnb.lottie.LottieAnimationView
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.adapter.ViewPagerAdapter
import com.github.kongpf8848.animation.base.BaseActivity
import com.github.kongpf8848.animation.bean.LottieConfig
import com.github.kongpf8848.animation.utils.AssetUtils
import com.github.kongpf8848.animation.utils.GuideTransformer
import com.github.kongpf8848.animation.utils.LottieUtils
import com.github.kongpf8848.animation.utils.RxJavaUtils
import com.kongpf.commonhelper.ScreenHelper
import com.tmall.ultraviewpager.UltraViewPager
import kotlinx.android.synthetic.main.pdj_guide_lottie.*
import java.lang.reflect.Field


class PdjGuideActivity : BaseActivity(), ViewPager.OnPageChangeListener {

    private var lottieConfig: LottieConfig? = null
    private var realWidth = 0
    private var realHeight = 0
    private val lottieList = mutableListOf<LottieAnimationView>()
    private val viewList = mutableListOf<View>()
    private var mState = 0
    private var currentPage=0
    private var buttonAlpha: ObjectAnimator? = null
    private var buttonTranslation: ObjectAnimator? = null
    private var translationOffset=0f
    /**
     * Drawable转换成Bitmap
     */
    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return bitmap
    }

    /**
     * 设置ViewPager滑动速率
     */
    private fun setViewPagerDuration(viewPager: ViewPager, newDuration: Int) {
        if (newDuration <= 0) return
        try {
            val field: Field = ViewPager::class.java.getDeclaredField("mScroller")
            field.isAccessible = true
            field.set(viewPager, object : Scroller(applicationContext, AccelerateInterpolator()) {
                override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
                    super.startScroll(startX, startY, dx, dy, newDuration)
                }
            })
        } catch (e1: IllegalAccessException) {
            e1.printStackTrace()
        } catch (e2: NoSuchFieldException) {
            e2.printStackTrace()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.pdj_guide_lottie
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        getLottieConfig()
    }

    override fun onDestroy() {
        super.onDestroy()
        lottieList.forEach {
            it.cancelAnimation()
        }
        buttonTranslation?.cancel()

    }

    /**
     * 获取配置信息
     */
    private fun getLottieConfig() {

        RxJavaUtils.run(object : RxJavaUtils.OnRxAndroidListener<LottieConfig>() {
            override fun doInBackground(): LottieConfig {
                return AssetUtils.readAssetFile(applicationContext, "LottieConfig.json")
            }

            override fun onSuccess(config: LottieConfig) {
                Log.d(TAG, "onSuccess() called with: config = $config")
                initView(config)
            }

            override fun onError(e: Throwable?) {
                Log.d(TAG, "onError() called with: e = $e")
            }

        })
    }

    /**
     * 初始化UI
     */
    private fun initView(config: LottieConfig) {

        this.lottieConfig = config
        this.translationOffset=ScreenHelper.dp2px(applicationContext,45f).toFloat()
        this.buttonAlpha=ObjectAnimator.ofFloat(iv_start,View.ALPHA,0.0f,1.0f).apply {
            duration=config.buttonAnimTime
        }
        this.buttonTranslation = ObjectAnimator.ofFloat(iv_start, View.TRANSLATION_Y, 0f,translationOffset).apply {
            duration=config.buttonAnimTime
        }

        realWidth = ScreenHelper.getScreenWidth(applicationContext)
        realHeight = (1334.0f * realWidth / 750.0f).toInt()

        ivJump.setOnClickListener {
            finish()
        }
        iv_start.setOnClickListener {
            finish()
        }

        lottie_title.tag=false
        lottie_title.layoutParams.width = realWidth
        lottie_title.layoutParams.height = realHeight
        lottie_bg.layoutParams.width = realWidth
        lottie_bg.layoutParams.height = realHeight

        LottieUtils.loadAssetFile(context = applicationContext, lottieImageView = lottie_bg, fileName = config.lottieBgName, repeatCount = 0, autoPlay = true)

        setViewPagerDuration(viewpager.viewPager, config.pageAnimTime.toInt())
        viewpager.setOffscreenPageLimit(config.totalPageNum)
        viewpager.setOnPageChangeListener(this@PdjGuideActivity)

        /**
         * 设置PageTransformer
         */
        viewpager.setPageTransformer(false, GuideTransformer(this@PdjGuideActivity, config.radius))

        for(position in 0 until config.totalPageNum){
            val view = LayoutInflater.from(this@PdjGuideActivity).inflate(R.layout.pdj_guide_lottie_item, null, false)
            val lottieAnimationView: LottieAnimationView = view.findViewById(R.id.pdj_guide_lottie_item)
            lottieAnimationView.layoutParams.width = realWidth
            lottieAnimationView.layoutParams.height = realHeight
            LottieUtils.loadAssetFile(context = applicationContext, lottieImageView = lottieAnimationView, fileName = config.lottieMain[position].lottieName, repeatCount = 0, autoPlay = false)
            lottieList.add(lottieAnimationView)
            viewList.add(view)
        }
        viewpager.adapter = ViewPagerAdapter(viewList)

        /**
         * 添加圆点指示符
         */
        viewpager.initIndicator()
        viewpager.indicator.setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setNormalIcon(drawableToBitmap(ContextCompat.getDrawable(applicationContext, R.drawable.guide_white_dot)!!))
                .setFocusIcon(drawableToBitmap(ContextCompat.getDrawable(applicationContext, R.drawable.guide_dark_dot)!!))
                .setIndicatorPadding(ScreenHelper.dp2px(applicationContext, 5.0F))
                .setMargin(0, 0, 0, ScreenHelper.dp2px(applicationContext, 20.0F))
        viewpager.indicator.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL)
        viewpager.indicator.build()

        /**
         * 开启动画
         */
        Looper.myQueue().addIdleHandler {
            updateAnim(0)
            false
        }
    }


    /**
     * 重置动画
     */
    private fun resetAllAnim() {
        lottieList.forEach {
            it.cancelAnimation()
            it.progress = 0.0f
        }
    }

    private fun stopAllAnimListener() {
        lottieList.forEach {
            it.removeAllAnimatorListeners()
        }
    }

    /**
     * 更新动画
     */
    private fun updateAnim(position: Int) {
        if (position >= 0 && position < lottieList.size) {
            val lottieAnimationView = lottieList[position]
            lottieAnimationView.addAnimatorListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    if (mState == 0) {
                        if (position < lottieConfig!!.totalPageNum - 1) {
                            viewpager.setCurrentItem(position + 1, true)
                        }
                    }
                }
            })
            resetAllAnim()
            lottieAnimationView.playAnimation()

            lottie_title.alpha=1.0f
            val flag=(lottie_title.tag as Boolean)
            if(!flag) {
                lottie_title.tag=true
                LottieUtils.loadAssetFile(context = applicationContext, lottieImageView = lottie_title, fileName = lottieConfig!!.lottieTitle[position].lottieName, repeatCount = 0, autoPlay = false)
                lottie_title.playAnimation()
            }

            /**
             * 当滑动到最后一页时，显示start按钮并做动画
             */
            if(position==lottieConfig!!.totalPageNum-1){
                iv_start.visibility=View.VISIBLE
                buttonAlpha?.apply {
                    cancel()
                    setFloatValues(0.0f,1.0f)
                    start()
                }
                buttonTranslation?.apply {
                    removeAllListeners()
                    cancel()
                    setFloatValues(0f,-translationOffset)
                    start()
                }
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
        Log.d(TAG, "onPageScrollStateChanged() called with: state = $state")
        this.mState = state
        if (this.mState == ViewPager.SCROLL_STATE_IDLE) {
            updateAnim(this.currentPage)
        } else {
            stopAllAnimListener()
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        Log.d(TAG, "onPageScrolled() called with: position = $position, positionOffset = $positionOffset, positionOffsetPixels = $positionOffsetPixels")
        if (positionOffset == 0.0f) {
            this.currentPage = position
        }

        if (positionOffset != 0.0F){
            if (position != this.currentPage) {
                lottie_title.alpha=positionOffset - 0.5F
            } else {
                lottie_title.alpha=0.5F - positionOffset
            }
        }

        /**
         * 当从最后一页向前滑动时,做动画并隐藏start按钮
         */
        if(this.currentPage==(lottieConfig!!.totalPageNum-1) && position==(this.currentPage-1) && positionOffset>0.0f && !buttonTranslation!!.isRunning){
            this.buttonAlpha?.apply {
                cancel()
                setFloatValues(1.0f,0.0f)
                start()
            }
            this.buttonTranslation?.apply {
                removeAllListeners()
                cancel()
                setFloatValues(-translationOffset,0.0f)
                addListener(object:AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        iv_start.visibility=View.GONE
                    }
                })
                start()
            }
        }

    }

    override fun onPageSelected(position: Int) {
        Log.d(TAG, "onPageSelected() called with: position = $position")
        //this.currentPage = position
        lottie_title.tag=false
    }

}


