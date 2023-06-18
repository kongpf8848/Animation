package com.github.kongpf8848.animation.activity.lottie

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.graphics.ColorUtils
import androidx.core.view.isInvisible
import androidx.viewpager2.widget.ViewPager2
import com.github.kongpf8848.animation.IntroViewModel
import com.github.kongpf8848.animation.LocalSettings.AccentColor
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.adapter.IntroPagerAdapter
import com.github.kongpf8848.animation.base.BaseActivity
import com.github.kongpf8848.animation.databinding.ActivityLottieKmailBinding
import com.github.kongpf8848.animation.removeOverScroll
import com.github.kongpf8848.animation.utils.UiUtils.animateColorChange
import kotlinx.android.synthetic.main.activity_lottie_kmail.*


open class KMailActivity : BaseActivity() {

    private val introViewModel: IntroViewModel by viewModels()

    override fun getLayoutId(): Int {
        return R.layout.activity_lottie_kmail
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

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    dotsIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }
            })

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                removeOverScroll()
            }
        }

        nextButton.setOnClickListener { introViewpager.currentItem += 1 }

        dotsIndicator.apply {
            setUp(introPagerAdapter.itemCount)
        }

        introViewModel.updatedAccentColor.observe(this@KMailActivity) { (newAccentColor, oldAccentColor) ->
            updateUi(newAccentColor, oldAccentColor)
        }


    }

    private fun updateUi(newAccentColor: AccentColor, oldAccentColor: AccentColor) {
        animatePrimaryColorElements(newAccentColor, oldAccentColor)
        animateSecondaryColorElements(newAccentColor, oldAccentColor)
    }

    private fun animatePrimaryColorElements(
        newAccentColor: AccentColor,
        oldAccentColor: AccentColor
    ) {
        val newPrimary = newAccentColor.getPrimary(this)
        val oldPrimary = oldAccentColor.getPrimary(this)
        val ripple = newAccentColor.getRipple(this)

        animateColorChange(oldPrimary, newPrimary) { color ->
            val singleColorStateList = ColorStateList.valueOf(color)
            dotsIndicator.setSelectedColor(color)
            connectButton.setBackgroundColor(color)
            nextButton.backgroundTintList = singleColorStateList
            signInButton.setTextColor(color)
            signInButton.rippleColor = ColorStateList.valueOf(ripple)
        }
    }

    private fun animateSecondaryColorElements(
        newAccentColor: AccentColor,
        oldAccentColor: AccentColor
    ) {
        val newSecondaryBackground = newAccentColor.getOnboardingSecondaryBackground(this)
        val oldSecondaryBackground = oldAccentColor.getOnboardingSecondaryBackground(this)
        animateColorChange(oldSecondaryBackground, newSecondaryBackground) { color ->
            window.statusBarColor = color
        }
    }
}