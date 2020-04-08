package com.github.kongpf8848.animation.activity.tween.translate;

import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.kongpf8848.xsdk.ui.activity.BaseActivity;
import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.ShakeListener;

import butterknife.BindView;


/**
 * Created by jack on 2016/9/17.
 */
public class ShakeActivity extends BaseActivity {
    private ShakeListener mShakeListener = null;
    private Vibrator mVibrator;
    @BindView(R.id.shakeImgUp)
    RelativeLayout mImgUp;
    @BindView(R.id.shakeImgDown)
    RelativeLayout mImgDn;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_shake;
    }

    @Override
    protected void initData() {
        mVibrator = (Vibrator) getApplication().getSystemService(VIBRATOR_SERVICE);
        mShakeListener = new ShakeListener(this);
        mShakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            public void onShake() {
                startAnim();
                mShakeListener.stop();
                startVibrato();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "没有摇到附近的人", Toast.LENGTH_SHORT).show();
                        mVibrator.cancel();
                        mShakeListener.start();
                    }
                }, 2000);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mShakeListener != null) {
            mShakeListener.stop();
        }
    }

    public void startAnim() {

        Animation animUp = AnimationUtils.loadAnimation(this, R.anim.shake_up);
        mImgUp.startAnimation(animUp);
        Animation animDown = AnimationUtils.loadAnimation(this, R.anim.shake_down);
        mImgDn.startAnimation(animDown);

    }

    public void startVibrato() {
        mVibrator.vibrate(new long[]{500, 200, 500, 200}, -1);
    }

    public void onShake(View v) {     //������
        startAnim();
    }

}
