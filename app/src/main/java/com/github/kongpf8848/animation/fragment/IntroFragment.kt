
package com.github.kongpf8848.animation.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.github.kongpf8848.animation.IlluColors.changeIllustrationColors
import com.github.kongpf8848.animation.IntroViewModel
import com.github.kongpf8848.animation.LocalSettings
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.databinding.FragmentIntroBinding
import com.github.kongpf8848.animation.repeatFrame
import com.github.kongpf8848.animation.utils.UiUtils.animateColorChange
import com.google.android.material.color.DynamicColors
import com.google.android.material.tabs.TabLayout
import com.github.kongpf8848.animation.LocalSettings.AccentColor
import com.github.kongpf8848.animation.enumValueFrom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroFragment : Fragment() {
    private lateinit var binding: FragmentIntroBinding
    private val introViewModel: IntroViewModel by activityViewModels()

    private val localSettings by lazy { LocalSettings.getInstance(requireContext()) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentIntroBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)=with(binding) {
        super.onViewCreated(view, savedInstanceState)

        val position=requireArguments().getInt("position")
        when (position) {
            0 -> introViewModel.updatedAccentColor.value?.let { (newAccentColor) ->
                pinkBlueSwitch.isVisible = true

                if (!DynamicColors.isDynamicColorAvailable()) {
                    pinkBlueTabLayout.removeTab(pinkBlueTabLayout.getTabAt(AccentColor.SYSTEM.introTabIndex)!!)
                }

                val selectedTab = pinkBlueTabLayout.getTabAt(newAccentColor.introTabIndex)
                pinkBlueTabLayout.selectTab(selectedTab)
                setTabSelectedListener()

                iconLayout.apply {
                    setAnimation(R.raw.illu_onboarding_1)
                    repeatFrame(54, 138)
                }
            }
            1 -> {
                waveBackground.setImageResource(R.drawable.ic_back_wave_2)
                title.setText(R.string.onBoardingTitle2)
                description.setText(R.string.onBoardingDescription2)
                iconLayout.apply {
                    setAnimation(R.raw.illu_onboarding_2)
                    repeatFrame(108, 253)
                }
            }
            2 -> {
                waveBackground.setImageResource(R.drawable.ic_back_wave_3)
                title.setText(R.string.onBoardingTitle3)
                description.setText(R.string.onBoardingDescription3)
                iconLayout.apply {
                    setAnimation(R.raw.illu_onboarding_3)
                    repeatFrame(111, 187)
                }
            }
            3 -> {
                waveBackground.setImageResource(R.drawable.ic_back_wave_4)
                title.setText(R.string.onBoardingTitle4)
                description.setText(R.string.onBoardingDescription4)
                iconLayout.apply {
                    setAnimation(R.raw.illu_onboarding_4)
                    repeatFrame(127, 236)
                }
            }
        }

        updateUiWhenThemeChanges(position)

        setUi(localSettings.accentColor, localSettings.accentColor, position)
    }

    private fun setTabSelectedListener() = with(binding) {
        pinkBlueTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val newSelectedAccentColor = (AccentColor::introTabIndex enumValueFrom pinkBlueTabLayout.selectedTabPosition)!!
                val oldSelectedAccentColor = localSettings.accentColor
                localSettings.accentColor = newSelectedAccentColor
                triggerUiUpdateWhenAnimationEnd(newSelectedAccentColor, oldSelectedAccentColor)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })
    }

    private fun triggerUiUpdateWhenAnimationEnd(newAccentColor: AccentColor, oldAccentColor: AccentColor) {
        lifecycleScope.launch(Dispatchers.IO) {
            val duration = 300L
            delay(duration)
            introViewModel.updatedAccentColor.postValue(newAccentColor to oldAccentColor)
        }
    }

    private fun updateUiWhenThemeChanges(position: Int) {
        introViewModel.updatedAccentColor.observe(viewLifecycleOwner) { (newAccentColor, oldAccentColor) ->
            setUi(newAccentColor, oldAccentColor, position)
        }
    }

    private fun setUi(newAccentColor: AccentColor, oldAccentColor: AccentColor, position: Int) {
        updateEachPageUi(newAccentColor, oldAccentColor)

        binding.iconLayout.changeIllustrationColors(position, newAccentColor)

        if (position == ACCENT_COLOR_PICKER_PAGE) updateAccentColorPickerPageUi(newAccentColor, oldAccentColor)
    }

    private fun updateEachPageUi(newAccentColor: AccentColor, oldAccentColor: AccentColor) {
        val newColor = newAccentColor.getOnboardingSecondaryBackground(requireContext())
        val oldColor = oldAccentColor.getOnboardingSecondaryBackground(requireContext())
        animateColorChange(oldColor, newColor) { color ->
            binding.waveBackground.imageTintList = ColorStateList.valueOf(color)
        }
    }

    private fun updateAccentColorPickerPageUi(newAccentColor: AccentColor, oldAccentColor: AccentColor) {
        animateTabIndicatorColor(newAccentColor, oldAccentColor)
    }

    private fun animateTabIndicatorColor(newAccentColor: AccentColor, oldAccentColor: AccentColor) = with(binding) {
        val newPrimary = newAccentColor.getPrimary(requireContext())
        val oldPrimary = oldAccentColor.getPrimary(requireContext())
        animateColorChange(oldPrimary, newPrimary, applyColor = pinkBlueTabLayout::setSelectedTabIndicatorColor)
    }

    private companion object {
        const val ACCENT_COLOR_PICKER_PAGE = 0
    }
}
