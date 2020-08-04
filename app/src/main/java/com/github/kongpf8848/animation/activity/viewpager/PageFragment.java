package com.github.kongpf8848.animation.activity.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class PageFragment extends Fragment implements ParallaxViewImp{

    //此Fragment上所有的需要实现视差动画的视图
    private List<View> parallaxViews = new ArrayList<>();


    public static PageFragment newInstance(int resId) {
        Bundle bd = new Bundle();
        bd.putInt("layoutId",resId);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(bd);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater original, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        int layoutId = args.getInt("layoutId");
        ParallaxLayoutInflater parallaxLayoutInflater = new ParallaxLayoutInflater(original, getActivity(), this);
        return parallaxLayoutInflater.inflate(layoutId, null);
    }


    public List<View> getParallaxViews() {
        return parallaxViews;
    }

}
