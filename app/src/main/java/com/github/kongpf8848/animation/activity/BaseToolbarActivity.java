package com.github.kongpf8848.animation.activity;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.Toolbar;

import com.github.kongpf8848.animation.R;

public abstract class BaseToolbarActivity extends BaseActivity {

    private FrameLayout fl_content;
    private Toolbar toolbar;

    @Override
    protected void initData() {
        super.initData();
        setupToolbar();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        addContentView(view);
    }

    @Override
    public void setContentView(View view) {
        addContentView(view);
    }

    private void addContentView(View view) {
        if (fl_content == null) {
            super.setContentView(R.layout.activity_toolbar);
            fl_content = findViewById(R.id.fl_content);
        }
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        fl_content.addView(view, layoutParams);

    }


    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView tv_title=findViewById(R.id.tv_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> finish());
        tv_title.setText(getTitle());
    }
}
