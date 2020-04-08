package com.github.kongpf8848.animation.activity.tween.translate;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.github.kongpf8848.animation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FidoActivity extends Activity {

    @BindView(R.id.finger_imageview)
    ImageView finger_dialog_image;
    @BindView(R.id.fpt_dialog_hint_text)
    TextView finger_dialog_hint;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fpt_dialog);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.fpt_dialog_bottom_cancel)
    public void onCancel(){
        this.finger_dialog_image.setImageResource(R.drawable.finger_wrong);
        this.finger_dialog_hint.setTextColor(Color.RED);
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        this.finger_dialog_hint.startAnimation(shake);
    }
}
