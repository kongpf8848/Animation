package com.github.kongpf8848.animation.fragment;

import android.widget.TextView;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.xsdk.ui.fragment.BaseFragment;
import com.github.kongpf8848.xsdk.ui.fragment.BaseLazyFragment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;

public class TitleFragment extends BaseLazyFragment {

    @BindView(R.id.tv_title)
    TextView tv_title;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_title;
    }


    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        tv_title.setText(getArguments().getString("title",""));

    }


}
