/*
 * Infomaniak kMail - Android
 * Copyright (C) 2022-2023 Infomaniak Network SA
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.kongpf8848.animation

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.model.KeyPath
import com.google.android.material.color.utilities.Hct

object IlluColors {

    private val lightTones = listOf(
        40.1,
        64.7,
        79.1,
        37.6,
        48.6,
        62.5,
        59.1,
        47.2,
        48.4,
        29.8,
        93.4,
        95.4,
    )

    private val darkTones = listOf(
        59.6,
        41.7,
        43.4,
        49.8,
        58.7,
        51.0,
        38.8,
        56.1,
        48.1,
        59.0,
        14.1,
        96.0,
    )

    fun LottieAnimationView.changeIllustrationColors(position: Int, accentColor: LocalSettings.AccentColor) {
        updateAccentColorIndependentColors(position)
        updateAccentColorDependentColors(accentColor, position)
    }

    //region Accent color independent part
    private fun LottieAnimationView.updateAccentColorIndependentColors(position: Int) {
        val pathsToColor = context.getAccentIndependentPathsToColor()
        colorPaths(pathsToColor, position)
    }

    private fun Context.getAccentIndependentPathsToColor(): IlluOnBoardingColors {
        val commonColor1 = ContextCompat.getColor(this,R.color.commonColor1)
        val commonColor2 = ContextCompat.getColor(this,R.color.commonColor2)
        val commonColor3 = ContextCompat.getColor(this,R.color.commonColor3)
        val commonColor4 = ContextCompat.getColor(this,R.color.commonColor4)
        val commonColor5 = ContextCompat.getColor(this,R.color.commonColor5)
        val commonColor6 = ContextCompat.getColor(this,R.color.commonColor6)
        val commonColor7 = ContextCompat.getColor(this,R.color.commonColor7)
        val commonColor8 = ContextCompat.getColor(this,R.color.commonColor8)
        val commonColor9 = ContextCompat.getColor(this,R.color.commonColor9)
        val commonColor10 = ContextCompat.getColor(this,R.color.commonColor10)

        val illuOnBoardingColors = listOf(
            IlluColors(keyPath(Category.IPHONESCREEN, 18), commonColor1),
            IlluColors(keyPath(Category.IPHONESCREEN, 22), commonColor2),
            IlluColors(keyPath(Category.IPHONESCREEN, 25), commonColor2),
            IlluColors(keyPath(Category.IPHONESCREEN, 26), commonColor3),
            IlluColors(keyPath(Category.IPHONESCREEN, 27), commonColor2),
            IlluColors(keyPath(Category.IPHONESCREEN, 28), commonColor2),
            IlluColors(keyPath(Category.IPHONESCREEN, 29), commonColor2),
            IlluColors(keyPath(Category.IPHONESCREEN, 30), commonColor2),
            IlluColors(keyPath(Category.IPHONESCREEN, 31), commonColor2),
            IlluColors(keyPath(Category.IPHONESCREEN, 32), commonColor2),
            IlluColors(keyPath(Category.IPHONESCREEN, 33), commonColor2),
            IlluColors(keyPath(Category.IPHONESCREEN, 34), commonColor2),
            IlluColors(keyPath(Category.IPHONESCREEN, 35), commonColor2),
            IlluColors(keyPath(Category.IPHONESCREEN, 36), commonColor2),
            IlluColors(keyPath(Category.IPHONESCREEN, 37), commonColor2),
            IlluColors(keyPath(Category.IPHONESCREEN, 38), commonColor2),
            IlluColors(keyPath(Category.IPHONESCREEN, 39), commonColor4),
            IlluColors(keyPath(Category.IPHONESCREEN, 44), commonColor1),
            IlluColors(keyPath(Category.IPHONESCREEN, 49), commonColor4),
            IlluColors(keyPath(Category.IPHONESCREEN, 50), commonColor1),
            IlluColors(keyPath(Category.IPHONESCREEN, 62), commonColor1),
            IlluColors(keyPath(Category.IPHONESCREEN, 68), commonColor1),
            IlluColors(keyPath(Category.IPHONESCREEN, 70), commonColor1),
        )

        val illuOnBoarding234Colors = listOf(
            IlluColors(keyPath(Category.IPHONESCREEN, 73), commonColor6),
            IlluColors(keyPath(Category.IPHONESCREEN, 74), commonColor1),
            IlluColors(keyPath(Category.IPHONESCREEN, 75), commonColor5),
            IlluColors(keyPath(Category.IPHONESCREEN, 76), commonColor2),
        )

        val illuOnBoarding1Colors = listOf(
            IlluColors(keyPath(Category.POINT, 1, 1), commonColor5),
            IlluColors(keyPath(Category.POINT, 1, 2), commonColor5),
            IlluColors(keyPath(Category.POINT, 1, 3), commonColor5),
            IlluColors(keyPath(Category.POINT, 1, 4), commonColor5),
            IlluColors(keyPath(Category.POINT, 1, 5), commonColor5),
            IlluColors(keyPath(Category.POINT, 1, 6), commonColor5),
            IlluColors(keyPath(Category.IPHONESCREEN, 56), commonColor1),
            IlluColors(keyPath(Category.IPHONESCREEN, 67), commonColor6),
            IlluColors(keyPath(Category.IPHONESCREEN, 69), commonColor5),
        )

        val illuOnBoarding2Colors = listOf(
            IlluColors(keyPath(Category.NOTIFICATION, 5, 2), commonColor4),
            IlluColors(keyPath(Category.NOTIFICATION, 6, 2), commonColor1),
            IlluColors(keyPath(Category.NOTIFICATION, 9, 2), commonColor7),
            IlluColors(keyPath(Category.NOTIFICATION, 10, 2), commonColor7),
            IlluColors(keyPath(Category.NOTIFICATION, 12, 2), commonColor5),
            IlluColors(keyPath(Category.NOTIFICATION, 13, 2), commonColor2),
            IlluColors(keyPath(Category.NOTIFICATION, 14, 2), commonColor1),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 4, 1), commonColor8),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 5, 1), commonColor8),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 6, 1), commonColor8),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 7, 1), commonColor5),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 8, 1), commonColor2),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 9, 1), commonColor2),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 10, 1), commonColor2),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 13, 1), commonColor5),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 14, 1), commonColor8),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 5, 2), commonColor4),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 6, 2), commonColor1),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 9, 2), commonColor7),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 10, 2), commonColor7),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 12, 2), commonColor5),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 13, 2), commonColor2),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 14, 2), commonColor1),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 15, 2), commonColor1),
            IlluColors(KeyPath("MOVING NOTIF 2 TITLE", "Groupe 1", "Fond 1"), commonColor2),
            IlluColors(KeyPath("MOVING NOTIF 2 PREVIEW", "Groupe 1", "Fond 1"), commonColor2),
            IlluColors(keyPath(Category.ARCHIVES, 1), commonColor5),
            IlluColors(keyPath(Category.ARCHIVES, 2), commonColor5),
            IlluColors(keyPath(Category.ARCHIVES, 3), commonColor5),
            IlluColors(keyPath(Category.ARCHIVES, 4), commonColor5),
        )

        val illuOnBoarding3Colors = listOf(
            IlluColors(keyPath(Category.NOTIFICATION, 1, 2), commonColor2),
            IlluColors(keyPath(Category.NOTIFICATION, 2, 2), commonColor2),
            IlluColors(keyPath(Category.NOTIFICATION, 3, 2), commonColor5),
            IlluColors(keyPath(Category.NOTIFICATION, 1, 3), commonColor2),
            IlluColors(keyPath(Category.NOTIFICATION, 2, 3), commonColor2),
            IlluColors(keyPath(Category.NOTIFICATION, 3, 3), commonColor5),
            IlluColors(keyPath(Category.NOTIFICATION, 1, 4), commonColor2),
            IlluColors(keyPath(Category.NOTIFICATION, 2, 4), commonColor2),
            IlluColors(keyPath(Category.NOTIFICATION, 3, 4), commonColor5),
            IlluColors(keyPath(Category.STAR, 2), commonColor3),
            IlluColors(keyPath(Category.BIN, 7), commonColor3),
            IlluColors(keyPath(Category.CLOCK, 5), commonColor3),
        )

        val illuOnBoarding4Colors = listOf(
            IlluColors(keyPath(Category.WOMAN, 5), commonColor4),
            IlluColors(keyPath(Category.WOMAN, 6), commonColor1),
            IlluColors(keyPath(Category.MEN, 5), commonColor4),
            IlluColors(keyPath(Category.MEN, 6), commonColor1),
            IlluColors(keyPath(Category.LETTER, 3), commonColor9),
            IlluColors(keyPath(Category.LETTER, 4), commonColor10),
        )

        return IlluOnBoardingColors(
            illuOnBoardingColors,
            illuOnBoarding234Colors,
            illuOnBoarding1Colors,
            illuOnBoarding2Colors,
            illuOnBoarding3Colors,
            illuOnBoarding4Colors,
        )
    }
    //endregion

    //region Accent color dependent part
    private fun LottieAnimationView.updateAccentColorDependentColors(accentColor: LocalSettings.AccentColor, position: Int) {
        val palette = context.getPaletteFor(accentColor)
        val pathsToColor = getPathsToColorFromPalette(palette)
        colorPaths(pathsToColor, position)
    }

    @SuppressLint("RestrictedApi")
    fun Context.getPaletteFor(accentColor: LocalSettings.AccentColor): IntArray {
        return when (accentColor) {
            LocalSettings.AccentColor.PINK -> resources.getIntArray(R.array.pinkColors)
            LocalSettings.AccentColor.BLUE -> resources.getIntArray(R.array.blueColors)
            LocalSettings.AccentColor.SYSTEM -> {
                val primary = LocalSettings.AccentColor.SYSTEM.getPrimary(this)
                val primaryColor = Hct.fromInt(primary)

                val tones = lightTones

                val palette = tones.map { tone ->
                    val color = Hct.from(primaryColor.hue, primaryColor.chroma, primaryColor.tone)
                    color.tone = tone
                    color.toInt()
                }

                return palette.toIntArray()
            }
        }
    }

    private fun getPathsToColorFromPalette(@ColorInt palette: IntArray): IlluOnBoardingColors {
        val illuOnBoardingAccentColors = listOf(
            IlluColors(keyPath(Category.IPHONESCREEN, 1), palette[0]),
            IlluColors(keyPath(Category.IPHONESCREEN, 2), palette[0]),
            IlluColors(keyPath(Category.IPHONESCREEN, 3), palette[0]),
            IlluColors(keyPath(Category.IPHONESCREEN, 4), palette[0]),
            IlluColors(keyPath(Category.IPHONESCREEN, 5), palette[0]),
            IlluColors(keyPath(Category.IPHONESCREEN, 6), palette[0]),
            IlluColors(keyPath(Category.IPHONESCREEN, 9), palette[5]),
            IlluColors(keyPath(Category.IPHONESCREEN, 12), palette[0]),
            IlluColors(keyPath(Category.IPHONESCREEN, 15), palette[0]),
            IlluColors(keyPath(Category.IPHONESCREEN, 19), palette[0]),
            IlluColors(keyPath(Category.IPHONESCREEN, 20), palette[0]),
            IlluColors(keyPath(Category.IPHONESCREEN, 23), palette[0]),
            IlluColors(keyPath(Category.IPHONESCREEN, 24), palette[0]),
            IlluColors(keyPath(Category.IPHONESCREEN, 43), palette[1]),
            IlluColors(keyPath(Category.IPHONESCREEN, 48), palette[4]),
        )

        val illuOnBoarding234AccentColors = listOf(
            IlluColors(keyPath(Category.IPHONESCREEN, 54), palette[4]),
            IlluColors(keyPath(Category.IPHONESCREEN, 61), palette[2]),
            IlluColors(keyPath(Category.IPHONESCREEN, 67), palette[0]),
            IlluColors(keyPath(Category.IPHONESCREEN, 72), palette[3]),
        )

        val illuOnBoarding1AccentColors = listOf(
            IlluColors(keyPath(Category.CHAT, 1, 1), palette[0]),
            IlluColors(keyPath(Category.CHAT, 1, 2), palette[1]),
            IlluColors(keyPath(Category.IPHONESCREEN, 55), palette[2]),
            IlluColors(keyPath(Category.IPHONESCREEN, 61), palette[0]),
            IlluColors(keyPath(Category.IPHONESCREEN, 66), palette[3]),
        )

        val illuOnBoarding2AccentColors = listOf(
            IlluColors(keyPath(Category.NOTIFICATION, 4, 2), palette[4]),
            IlluColors(keyPath(Category.NOTIFICATION, 11, 2), palette[0]),
            IlluColors(keyPath(Category.HAND, 1), palette[3]),
            IlluColors(keyPath(Category.HAND, 4), palette[9]),
            IlluColors(keyPath(Category.HAND, 5), palette[9]),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 15, 1), palette[0]),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 4, 2), palette[4]),
            IlluColors(keyPath(Category.MOVINGNOTIFICATION, 11, 2), palette[0]),
        )

        val illuOnBoarding3AccentColors = listOf(
            IlluColors(keyPath(Category.NOTIFICATION, 4, 2), palette[0]),
            IlluColors(keyPath(Category.NOTIFICATION, 5, 2), palette[10]),
            IlluColors(keyPath(Category.NOTIFICATION, 4, 3), palette[0]),
            IlluColors(keyPath(Category.NOTIFICATION, 5, 3), palette[10]),
            IlluColors(keyPath(Category.NOTIFICATION, 4, 4), palette[0]),
            IlluColors(keyPath(Category.NOTIFICATION, 5, 4), palette[10]),
            IlluColors(keyPath(Category.HAND, 1), palette[3]),
            IlluColors(keyPath(Category.HAND, 4), palette[9]),
            IlluColors(keyPath(Category.HAND, 5), palette[9]),
            IlluColors(keyPath(Category.STAR, 1, finalLayer = FinalLayer.BORDER), palette[0]),
            IlluColors(keyPath(Category.BIN, 1, finalLayer = FinalLayer.BORDER), palette[0]),
            IlluColors(keyPath(Category.BIN, 2, finalLayer = FinalLayer.BORDER), palette[0]),
            IlluColors(keyPath(Category.BIN, 3, finalLayer = FinalLayer.BORDER), palette[0]),
            IlluColors(keyPath(Category.BIN, 4, finalLayer = FinalLayer.BORDER), palette[0]),
            IlluColors(keyPath(Category.BIN, 5, finalLayer = FinalLayer.BORDER), palette[0]),
            IlluColors(keyPath(Category.BIN, 6, finalLayer = FinalLayer.BORDER), palette[0]),
            IlluColors(keyPath(Category.CLOCK, 1), palette[0]),
            IlluColors(keyPath(Category.CLOCK, 2), palette[0]),
            IlluColors(keyPath(Category.CLOCK, 3), palette[0]),
            IlluColors(keyPath(Category.CLOCK, 4), palette[0]),
        )

        val illuOnBoarding4AccentColors = listOf(
            IlluColors(keyPath(Category.WOMAN, 4), palette[4]),
            IlluColors(keyPath(Category.MEN, 5), palette[1]),
            IlluColors(keyPath(Category.POINT, 1, 1), palette[4]),
            IlluColors(keyPath(Category.POINT, 1, 2), palette[4]),
            IlluColors(keyPath(Category.POINT, 1, 3), palette[1]),
            IlluColors(keyPath(Category.POINT, 1, 4), palette[1]),
            IlluColors(keyPath(Category.LETTER, 1), palette[6]),
            IlluColors(keyPath(Category.LETTER, 2), palette[7]),
            IlluColors(keyPath(Category.LETTER, 5), palette[11]),
            IlluColors(keyPath(Category.LETTER, 6), palette[8]),
            IlluColors(keyPath(Category.LETTER, 7), palette[8]),
        )

        return IlluOnBoardingColors(
            illuOnBoardingAccentColors,
            illuOnBoarding234AccentColors,
            illuOnBoarding1AccentColors,
            illuOnBoarding2AccentColors,
            illuOnBoarding3AccentColors,
            illuOnBoarding4AccentColors,
        )
    }
    //endregion

    //region Common
    private fun LottieAnimationView.colorPaths(pathToColor: IlluOnBoardingColors, position: Int) = with(pathToColor) {
        illuAll.forEach(::changePathColor)

        when (position) {
            1, 2, 3 -> illu234.forEach(::changePathColor)
        }

        when (position) {
            0 -> illu1.forEach(::changePathColor)
            1 -> illu2.forEach(::changePathColor)
            2 -> illu3.forEach(::changePathColor)
            3 -> illu4.forEach(::changePathColor)
        }
    }

    fun keyPath(
        category: Category,
        group: Int = 1,
        categoryNumber: Int? = null,
        finalLayer: FinalLayer = FinalLayer.BACKGROUND,
    ): KeyPath {
        val categoryName = categoryNumber?.let { "${category.value} $it" } ?: category.value
        return KeyPath(categoryName, "Groupe $group", "${finalLayer.value} 1")
    }
    //endregion

    private data class IlluOnBoardingColors(
        val illuAll: List<IlluColors>,
        val illu234: List<IlluColors>,
        val illu1: List<IlluColors>,
        val illu2: List<IlluColors>,
        val illu3: List<IlluColors>,
        val illu4: List<IlluColors>,
    )

    data class IlluColors(val keyPath: KeyPath, @ColorInt val color: Int)

    enum class Category(val value: String) {
        IPHONESCREEN("IPHONE SCREEN"),
        POINT("POINT"),
        CHAT("CHAT"),
        NOTIFICATION("NOTIFICATION"),
        MOVINGNOTIFICATION("MOVING NOTIF"),
        ARCHIVES("ARCHIVES"),
        HAND("HAND"),
        STAR("STAR"),
        BIN("BIN"),
        CLOCK("CLOCK"),
        WOMAN("WOMAN"),
        MEN("MEN"),
        LETTER("LETTER"),
        LINK("LINK"),
    }

    enum class FinalLayer(val value: String) {
        BACKGROUND("Fond"),
        BORDER("Contour"),
    }
}
