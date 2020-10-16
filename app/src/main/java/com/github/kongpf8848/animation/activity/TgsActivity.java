package com.github.kongpf8848.animation.activity;

import android.graphics.Rect;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.github.kongpf8848.animation.MainHandler;
import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.StickerPreviewViewer;
import com.github.kongpf8848.animation.adapter.StickerAdapter;
import com.github.kongpf8848.animation.bean.StickerItem;
import com.github.kongpf8848.animation.widget.StickerRecyclerView;
import com.kongpf.commonhelper.ScreenHelper;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class TgsActivity extends BaseToolbarActivity  {

    @BindView(R.id.recyclerview)
    StickerRecyclerView recyclerView;

    private StickerRecyclerView.OnItemClickListener onItemClickListener= (view, position) ->{
        StickerPreviewViewer.getInstance().reset();
        Toast.makeText(TgsActivity.this, "click:"+position, Toast.LENGTH_SHORT).show();
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tgs;
    }

    @Override
    protected void initData() {
        super.initData();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = ScreenHelper.dp2px(getApplicationContext(),10f);
                outRect.bottom = ScreenHelper.dp2px(getApplicationContext(),10f);
            }
        });

        recyclerView.setOnItemClickListener(onItemClickListener);
        recyclerView.setOnTouchListener((v, event) -> StickerPreviewViewer.getInstance().onTouch(event,recyclerView));
        StickerAdapter adapter = new StickerAdapter(this);
        adapter.setList(getDatas());
        recyclerView.setAdapter(adapter);
    }

    private List<StickerItem> getDatas() {
        return Arrays.asList(
                new StickerItem(1, "\u2764\ufe0f",  "2_1901206392136531994.tgs"),
                new StickerItem(2, "\ud83d\udc4d",  "2_1901206392136531995.tgs"),
                new StickerItem(3, "\ud83d\ude18",  "2_1901206392136531997.tgs"),
                new StickerItem(4, "\ud83e\udd2c",  "2_1901206392136531999.tgs"),
                new StickerItem(5, "\ud83c\udf37",  "2_1901206392136532002.tgs"),
                new StickerItem(6, "\ud83e\udda0",  "2_1901206392136532003.tgs"),
                new StickerItem(7, "\ud83d\ude2f",  "2_1901206392136532005.tgs"),
                new StickerItem(8, "\ud83d\ude02",  "2_1901206392136532006.tgs"),
                new StickerItem(9, "\ud83e\udd14",  "2_1901206392136532007.tgs"),
                new StickerItem(10, "\ud83d\ude45", "2_1901206392136532008.tgs"),
                new StickerItem(11, "\ud83e\udd73", "2_1901206392136532009.tgs"),
                new StickerItem(12, "\ud83d\ude28", "2_1901206392136532010.tgs"),
                new StickerItem(13, "\ud83d\ude15", "2_1901206392136532011.tgs"),
                new StickerItem(14, "\u23f3",       "2_1901206392136532012.tgs"),
                new StickerItem(15, "\ud83d\udc4b", "2_1901206392136532014.tgs"),
                new StickerItem(16, "\ud83d\udc48", "2_1901206392136532015.tgs"),
                new StickerItem(17, "\ud83d\ude34", "2_1901206392136532016.tgs"),
                new StickerItem(18, "\ud83d\ude09", "2_1901206392136532017.tgs"),
                new StickerItem(19, "\ud83d\ude29", "2_1901206392136532018.tgs"),
                new StickerItem(20, "\u2753",       "2_1901206392136532019.tgs")

        );
    }
}
