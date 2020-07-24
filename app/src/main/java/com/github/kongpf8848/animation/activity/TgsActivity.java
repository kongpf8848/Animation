package com.github.kongpf8848.animation.activity;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.adapter.EmojiNewAdapter;
import com.github.kongpf8848.animation.bean.EmotionItemBean;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class TgsActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tgs;
    }

    @Override
    protected void initData() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = (int) (getResources().getDisplayMetrics().density * 10);
                outRect.bottom = (int) (getResources().getDisplayMetrics().density * 10);
            }
        });

        EmojiNewAdapter adapter = new EmojiNewAdapter(this);
        adapter.setList(getDatas());
        recyclerView.setAdapter(adapter);
    }

    private List<EmotionItemBean> getDatas() {
        return Arrays.asList(
                new EmotionItemBean("\u2764\ufe0f", "2_1901206392136531994.tgs", 1, ""),
                new EmotionItemBean("\ud83d\udc4d", "2_1901206392136531995.tgs", 2, ""),
                new EmotionItemBean("\ud83d\ude18", "2_1901206392136531997.tgs", 3, ""),
                new EmotionItemBean("\ud83e\udd2c", "2_1901206392136531999.tgs", 4, ""),
                new EmotionItemBean("\ud83c\udf37", "2_1901206392136532002.tgs", 5, ""),
                new EmotionItemBean("\ud83e\udda0", "2_1901206392136532003.tgs", 6, ""),
                new EmotionItemBean("\ud83d\ude2f", "2_1901206392136532005.tgs", 7,""),
                new EmotionItemBean("\ud83d\ude02", "2_1901206392136532006.tgs",8, ""),
                new EmotionItemBean("\ud83e\udd14", "2_1901206392136532007.tgs", 9,""),
                new EmotionItemBean("\ud83d\ude45", "2_1901206392136532008.tgs", 10,""),
                new EmotionItemBean("\ud83e\udd73", "2_1901206392136532009.tgs", 11,""),
                new EmotionItemBean("\ud83d\ude28", "2_1901206392136532010.tgs",12, ""),
                new EmotionItemBean("\ud83d\ude15", "2_1901206392136532011.tgs", 13,""),
                new EmotionItemBean("\u23f3",       "2_1901206392136532012.tgs", 14,""),
                new EmotionItemBean("\ud83d\udc4b", "2_1901206392136532014.tgs", 15,""),
                new EmotionItemBean("\ud83d\udc48", "2_1901206392136532015.tgs", 16,""),
                new EmotionItemBean("\ud83d\ude34", "2_1901206392136532016.tgs", 17,""),
                new EmotionItemBean("\ud83d\ude09", "2_1901206392136532017.tgs", 18,""),
                new EmotionItemBean("\ud83d\ude29", "2_1901206392136532018.tgs", 19,""),
                new EmotionItemBean("\u2753",       "2_1901206392136532019.tgs", 20,"")

        );
    }
}
