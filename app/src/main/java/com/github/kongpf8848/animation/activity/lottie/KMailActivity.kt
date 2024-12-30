package com.github.kongpf8848.animation.activity.lottie

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isInvisible
import androidx.viewpager2.widget.ViewPager2
import com.github.kongpf8848.animation.IntroViewModel
import com.github.kongpf8848.animation.LocalSettings.AccentColor
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.adapter.IntroPagerAdapter
import com.github.kongpf8848.animation.base.BaseActivity
import com.github.kongpf8848.animation.removeOverScroll
import com.github.kongpf8848.animation.utils.UiUtils.animateColorChange
import com.github.kongpf8848.animation.views.CuteIndicator
import com.google.android.material.button.MaterialButton


open class KMailActivity : BaseActivity() {

    private val introViewModel: IntroViewModel by viewModels()
    lateinit var introViewpager:ViewPager2
    lateinit var nextButton: View
    lateinit var connectButton: MaterialButton
    lateinit var signInButton:MaterialButton
    lateinit var dotsIndicator: CuteIndicator

    override fun getLayoutId(): Int {
        return R.layout.activity_lottie_kmail
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        introViewpager=findViewById(R.id.introViewpager)
        nextButton=findViewById(R.id.nextButton)
        connectButton=findViewById(R.id.connectButton)
        signInButton=findViewById(R.id.signInButton)
        dotsIndicator=findViewById(R.id.dotsIndicator)
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