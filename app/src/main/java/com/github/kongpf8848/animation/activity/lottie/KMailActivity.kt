package com.github.kongpf8848.animation.activity.lottie

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isInvisible
import androidx.viewpager2.widget.ViewPager2
import com.github.kongpf8848.animation.IntroViewModel
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.adapter.IntroPagerAdapter
import com.github.kongpf8848.animation.base.BaseActivity
import com.github.kongpf8848.animation.databinding.ActivityLottieKmailBinding
import com.github.kongpf8848.animation.removeOverScroll
import kotlinx.android.synthetic.main.activity_lottie_kmail.connectButton
import kotlinx.android.synthetic.main.activity_lottie_kmail.introViewpager
import kotlinx.android.synthetic.main.activity_lottie_kmail.nextButton
import kotlinx.android.synthetic.main.activity_lottie_kmail.signInButton

open class KMailActivity: BaseActivity() {

    private val binding by lazy { ActivityLottieKmailBinding.inflate(layoutInflater) }
    private val introViewModel: IntroViewModel by viewModels()

    override fun getLayoutId(): Int {
        return R.layout.activity_lottie_kmail
    }

    override fun enableStatusBar(): Boolean {
        return false
    }

    override fun customInitStatusBar() {
        setStatusBarColorNavigationColorInt(Color.TRANSPARENT,false,Color.WHITE,true)
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        val introPagerAdapter = IntroPagerAdapter(supportFragmentManager, lifecycle)
        introViewpager.apply {
            adapter = introPagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    val showConnectButton = position == introPagerAdapter.itemCount - 1
                    nextButton.isInvisible = showConnectButton
                    connectButton.isInvisible = !showConnectButton
                    signInButton.isInvisible = !showConnectButton
                }
            })

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                removeOverScroll()
            }
        }

    }
}