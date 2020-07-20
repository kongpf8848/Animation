package com.github.kongpf8848.animation.activity.tween.alpha;

import android.view.KeyEvent;

import com.github.kongpf8848.animation.activity.BaseActivity;
import com.github.kongpf8848.animation.R;

/**
 * Created by jack on 2016/9/16.
 */
public class OtherActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            finish();
            overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
            return false;
        }
        return false;
    }


}
