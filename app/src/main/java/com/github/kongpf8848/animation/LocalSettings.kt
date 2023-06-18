package com.github.kongpf8848.animation

import android.content.Context
import android.view.ContextThemeWrapper
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.google.android.material.color.MaterialColors
import com.google.android.material.R as RMaterial

class LocalSettings private constructor(context: Context) {

    private val sharedPreferences = context.applicationContext.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    fun removeSettings() = sharedPreferences.transaction { clear() }

    private inline fun <reified T : Enum<T>> getEnum(key: String, default: T): T {
        return enumValueOfOrNull<T>(sharedPreferences.getString(key, default.name)) ?: default
    }

    private fun putEnum(key: String, value: Enum<*>) {
        sharedPreferences.transaction { putString(key, value.name) }
    }

    //region Accent color
    var accentColor: AccentColor
        get() = getEnum(ACCENT_COLOR_KEY, DEFAULT_ACCENT_COLOR)
        set(value) = putEnum(ACCENT_COLOR_KEY, value)

    enum class AccentColor(
        @StringRes val localisedNameRes: Int,
        @StyleRes val theme: Int,
        val introTabIndex: Int,
    ) {
        PINK(
            R.string.accentColorPinkTitle,
            R.style.AppTheme_Pink,
            0,
        ),
        BLUE(
            R.string.accentColorBlueTitle,
            R.style.AppTheme_Blue,
            1,
        ),
        SYSTEM(
            R.string.accentColorSystemTitle,
            R.style.AppTheme,
            2,
        );

        fun getPrimary(context: Context): Int {
            val baseThemeContext = ContextThemeWrapper(context, theme)
            return MaterialColors.getColor(baseThemeContext, RMaterial.attr.colorPrimary, 0)
        }

        fun getOnboardingSecondaryBackground(context: Context): Int {
            val baseThemeContext = ContextThemeWrapper(context, theme)
            return ContextCompat.getColor(baseThemeContext,R.color.onboarding_secondary_background)
        }

        fun getRipple(context: Context): Int {
            val baseThemeContext = ContextThemeWrapper(context, theme)
            return MaterialColors.getColor(baseThemeContext, RMaterial.attr.colorControlHighlight, 0)
        }

        override fun toString() = name.lowercase()
    }

    companion object {

        private val TAG = LocalSettings::class.simpleName
        private val DEFAULT_ACCENT_COLOR = AccentColor.PINK

        private const val SHARED_PREFS_NAME = "LocalSettingsSharedPref"
        private const val ACCENT_COLOR_KEY = "accentColorKey"

        //endregion

        @Volatile
        private var INSTANCE: LocalSettings? = null

        fun getInstance(context: Context): LocalSettings {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let { return it }
                LocalSettings(context).also { INSTANCE = it }
            }
        }
    }
}
