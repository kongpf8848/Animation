package com.github.kongpf8848.animation.base

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import butterknife.ButterKnife
import com.github.kongpf8848.animation.R
import com.gyf.immersionbar.ImmersionBar
import com.kongpf.commonhelper.LogHelper

abstract class BaseActivity : AppCompatActivity() {

    val TAG: String =javaClass.simpleName

    protected abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        onCreateStart(savedInstanceState)
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")
        setContentView(getLayoutId())
        ButterKnife.bind(this)
        onCreateEnd(savedInstanceState)
    }

    protected open fun onCreateStart(savedInstanceState: Bundle?) {}
    protected open fun onCreateEnd(savedInstanceState: Bundle?) {}

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart() called")
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent() called")
    }

    @JvmOverloads
    fun startActivity(cls: Class<*>?, finishThis: Boolean = false) {
        val intent = Intent(this, cls)
        startActivity(intent)
        if (finishThis) {
            finish()
        }
    }

    fun startActivity(target: Class<*>?, vararg sharedElements: Pair<View?, String?>?) {
        val intent = Intent(this, target)
        val transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *sharedElements)
        startActivity(intent, transitionActivityOptions.toBundle())
    }

    /**++++++++++++++++++++++++++++++++++++状态栏,导航栏相关++++++++++++++++++++++++++++++++++++++++++*/
    /*
     此方法在setContentView之后调用,如需定制，则设置enableWhiteStatusBar为false,然后覆盖customInitStatusBar方法
   */
    override fun onContentChanged() {
        super.onContentChanged()
        try {
            if (enableStatusBar()) {
                ImmersionBar.with(this)
                        .fitsSystemWindows(fitsSystemWindows())
                        .statusBarColor(statusBarColor())
                        .statusBarDarkFont(statusBarDarkFont())
                        .navigationBarColor(navigationBarColor(), navigationBarAlpha())
                        .navigationBarDarkIcon(navigationBarDarkIcon())
                        .keyboardEnable(keyboardEnable())
                        .init()
            } else {
                customInitStatusBar()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /**
     * 针对一些页面，如聊天界面，防止沉浸式状态栏全屏和键盘有冲突，键盘弹出时布局无法上移，可以采用此方法
     * @param color
     * @param isDarkFont
     */
    protected fun setStatusBarColorSpecial(@ColorInt color: Int, isDarkFont: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView = window.decorView
            if (isDarkFont) {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }
        }
    }

    protected fun setStatusBarColorInt(@ColorInt color: Int, isDarkFont: Boolean) {
        ImmersionBar.with(this).statusBarColorInt(color).statusBarDarkFont(isDarkFont).init()
    }

    protected fun setStatusBarColorNavigationColorInt(@ColorInt statusBarColor: Int, isDarkStatus: Boolean,@ColorInt navigationBarColor: Int, isDarkNav: Boolean) {
        ImmersionBar.with(this).statusBarColorInt(statusBarColor)
            .statusBarDarkFont(isDarkStatus)
            .navigationBarColorInt(navigationBarColor)
            .navigationBarDarkIcon(isDarkNav)
            .init()
    }

    protected fun initStatusBar(@IdRes titleBarId: Int, @ColorRes statusBarColor: Int = android.R.color.transparent) {
        ImmersionBar.with(this)
                .titleBarMarginTop(titleBarId)
                .statusBarColor(statusBarColor)
                .navigationBarColor(navigationBarColor(), navigationBarAlpha())
                .navigationBarDarkIcon(navigationBarDarkIcon())
                .keyboardEnable(keyboardEnable())
                .init()
    }

    protected open fun enableStatusBar(): Boolean {
        return true
    }

    protected open fun fitsSystemWindows(): Boolean {
        return true
    }

    protected open fun statusBarColor(): Int {
        return R.color.white
    }

    protected open fun navigationBarColor(): Int {
        return R.color.white
    }

    protected fun navigationBarAlpha(): Float {
        return 0.5f
    }

    protected open fun statusBarDarkFont(): Boolean {
        return true
    }

    protected open fun navigationBarDarkIcon(): Boolean {
        return true
    }

    protected fun keyboardEnable(): Boolean {
        return true
    }

    protected open fun customInitStatusBar() {}
}