package com.github.kongpf8848.animation.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.github.kongpf8848.animation.R;
import com.gyf.immersionbar.ImmersionBar;
import com.kongpf.commonhelper.LogHelper;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getClass().getSimpleName(),"onCreate called");
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initData();
    }

    /*
      此方法在setContentView之后调用,如需定制，则设置enableWhiteStatusBar为false,然后覆盖customInitStatusBar方法
    */
    @Override
    public void onContentChanged(){
        super.onContentChanged();
        try {
            if (enableStatusBar()) {
                ImmersionBar.with(this)
                        .fitsSystemWindows(fitsSystemWindows())
                        .supportActionBar(supportActionBar())
                        .statusBarColor(statusBarColor())
                        .statusBarDarkFont(statusBarDarkFont())
                        .navigationBarColor(navigationBarColor(), navigationBarAlpha())
                        .navigationBarDarkIcon(navigationBarDarkIcon())
                        .keyboardEnable(keyboardEnable())
                        .init();
            }
            else {
                customInitStatusBar();
            }
        } catch(Exception ex) {
            LogHelper.d("initStatusBar Exception:"+ex.getMessage());
        }
    }
    /**
     * 针对一些页面，如聊天界面，防止沉浸式状态栏全屏和键盘有冲突，键盘弹出时布局无法上移，可以采用此方法
     * @param color
     * @param isDarkFont
     */
    protected void setStatusBarColorSpecial(@ColorInt int color, boolean isDarkFont){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(color);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = getWindow().getDecorView();
            if(isDarkFont) {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
            else{
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }
    }

    protected void setStatusBarColorInt(@ColorInt int color, boolean isDarkFont){
        ImmersionBar.with(this).statusBarColorInt(color).statusBarDarkFont(isDarkFont).init();
    }

    protected void initStatusBar(int titleBarId){
        initStatusBar(titleBarId,android.R.color.transparent);
    }
    protected void initStatusBar(@IdRes int titleBarId, @ColorRes int statusBarColor){
        ImmersionBar.with(this)
                .titleBar(titleBarId)
                .statusBarColor(statusBarColor)
                .navigationBarColor(navigationBarColor(), navigationBarAlpha())
                .navigationBarDarkIcon(navigationBarDarkIcon())
                .keyboardEnable(keyboardEnable())
                .init();
    }

    protected boolean enableStatusBar(){
        return true;
    }
    protected boolean fitsSystemWindows(){
        return false;
    }
    protected boolean supportActionBar(){
        return true;
    }
    protected int statusBarColor(){
        return R.color.colorPrimaryDark;
    }
    protected int navigationBarColor(){
        return android.R.color.white;
    }
    protected float navigationBarAlpha(){
        return 0.5f;
    }
    protected boolean statusBarDarkFont(){
        return false;
    }
    protected boolean navigationBarDarkIcon(){
        return true;
    }
    protected boolean keyboardEnable(){
        return true;
    }
    protected void customInitStatusBar(){

    }


    protected void initData(){

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getClass().getSimpleName(), "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(getClass().getSimpleName(), "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(getClass().getSimpleName(), "onPause() called");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(getClass().getSimpleName(), "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(getClass().getSimpleName(), "onDestroy() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(getClass().getSimpleName(), "onRestart() called");
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(getClass().getSimpleName(), "onNewIntent() called");
    }

    public void startActivity(Class<?>cls){
        Intent intent=new Intent(this,cls);
        startActivity(intent);
    }
}