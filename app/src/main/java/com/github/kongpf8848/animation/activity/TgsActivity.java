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
                new EmotionItemBean("\uD83E\uDD2A", "mv00016.tgs", 1, ""),
                new EmotionItemBean("\uD83D\uDE44", "mv00017.tgs", 3, ""),
                new EmotionItemBean("\uD83E\uDD7A", "mv00018.tgs", 4, ""),
                new EmotionItemBean("\uD83D\uDE18", "mv00019.tgs", 5, ""),
                new EmotionItemBean("\uD83D\uDC4D", "mv00021.tgs", 6, ""),

                new EmotionItemBean("\uD83E\uDD2A", "mv00022.tgs", 7,""),
                new EmotionItemBean("\uD83D\uDE44", "mv00023.tgs",8, ""),
                new EmotionItemBean("\uD83E\uDD7A", "mv00024.tgs", 9,""),
                new EmotionItemBean("\uD83D\uDE18", "mv00025.tgs", 10,""),
                new EmotionItemBean("\uD83D\uDC4D", "mv00026.tgs", 11,""),

                new EmotionItemBean("\uD83E\uDD2A", "mv00027.tgs",12, ""),
                new EmotionItemBean("\uD83D\uDE44", "mv00028.tgs", 13,""),
                new EmotionItemBean("\uD83E\uDD7A", "mv00029.tgs", 14,""),
                new EmotionItemBean("\uD83D\uDE18", "mv00030.tgs", 15,""),
                new EmotionItemBean("\uD83D\uDC4D", "mv00031.tgs", 16,""),

                new EmotionItemBean("\uD83E\uDD2A", "mv00032.tgs", 17,""),
                new EmotionItemBean("\uD83D\uDE44", "mv00033.tgs", 18,""),
                new EmotionItemBean("\uD83E\uDD7A", "mv00034.tgs", 19,""),
                new EmotionItemBean("\uD83D\uDE18", "mv00035.tgs", 20,""),
                new EmotionItemBean("\uD83D\uDC4D", "mv00036.tgs", 21,""),

                new EmotionItemBean("\uD83E\uDD2A", "mv00037.tgs", 22,""),
                new EmotionItemBean("\uD83D\uDE44", "mv00038.tgs", 23,""),
                new EmotionItemBean("\uD83E\uDD7A", "mv00039.tgs", 24,""),
                new EmotionItemBean("\uD83D\uDE18", "mv00040.tgs", 25,""),
                new EmotionItemBean("\uD83D\uDE18", "mv00041.tgs", 26,"")

        );
    }
}
