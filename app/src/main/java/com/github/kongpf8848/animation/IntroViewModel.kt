
package com.github.kongpf8848.animation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.kongpf8848.animation.LocalSettings.AccentColor

class IntroViewModel(application: Application) : AndroidViewModel(application) {
    var updatedAccentColor: MutableLiveData<Pair<AccentColor, AccentColor>> = MutableLiveData(
        LocalSettings.getInstance(application).accentColor to LocalSettings.getInstance(application).accentColor
    )
}
