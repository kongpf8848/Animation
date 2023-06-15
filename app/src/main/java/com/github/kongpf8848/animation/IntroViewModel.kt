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

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.kongpf8848.animation.LocalSettings
import com.github.kongpf8848.animation.LocalSettings.AccentColor

class IntroViewModel(application: Application) : AndroidViewModel(application) {
    var updatedAccentColor: MutableLiveData<Pair<AccentColor, AccentColor>> = MutableLiveData(
        LocalSettings.getInstance(application).accentColor to LocalSettings.getInstance(application).accentColor
    )
}
