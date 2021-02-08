package com.github.kongpf8848.animation.activity.viewpager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.activity.BaseActivity;
import com.github.kongpf8848.animation.fragment.TitleFragment;
import com.github.kongpf8848.xsdk.ui.adapter.FragmentAdapter;
import com.google.android.material.animation.ArgbEvaluatorCompat;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class WeChatActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.magicIndicator)
    MagicIndicator magicIndicator;

    private int normalTextColor = Color.parseColor("#FF333333");
    private int selectedTextColor = Color.parseColor("#FF00FF00");
    private static final String[] CHANNELS = new String[]{"微信", "通讯录", "发现", "我"};
    private static final int[] DRAWABLES = new int[]{R.drawable.weixin_normal, R.drawable.contact_list_normal, R.drawable.find_normal, R.drawable.weixin_normal};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lottie_tab;
    }

    protected void onCreateEnd(Bundle savedInstanceState){
        super.onCreateEnd(savedInstanceState);
        initViewPager();
        initMagicIndicator();
    }

    private void initViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < CHANNELS.length; i++) {
            TitleFragment fragment = new TitleFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", CHANNELS[i]);
            fragment.setArguments(bundle);

            fragmentList.add(fragment);
        }
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList, Arrays.asList(CHANNELS));
        viewPager.setAdapter(adapter);
    }

    private void initMagicIndicator() {

        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return CHANNELS.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);

                View customLayout = LayoutInflater.from(context).inflate(R.layout.item_tab_wechat, null);
                final ImageView img = customLayout.findViewById(R.id.item_tab_image);
                img.setImageResource(DRAWABLES[index]);
                final TextView text = customLayout.findViewById(R.id.item_tab_text);
                text.setText(CHANNELS[index]);
                text.setTextColor(normalTextColor);
                commonPagerTitleView.setContentView(customLayout);
                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {

                    @Override
                    public void onSelected(int index, int totalCount) {
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {

                        int color = ArgbEvaluatorCompat.getInstance().evaluate(leavePercent, selectedTextColor, normalTextColor);
                        text.setTextColor(color);
                        img.getDrawable().setTint(color);

                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {

                        int color = ArgbEvaluatorCompat.getInstance().evaluate(enterPercent, normalTextColor, selectedTextColor);
                        text.setTextColor(color);
                        img.getDrawable().setTint(color);
                    }
                });

                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });

                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }


}
