package com.github.kongpf8848.animation.adapter;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.bean.StickerItem;
import com.github.kongpf8848.animation.widget.MyStickerEmojiCell;

import java.util.List;

public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.ViewHolder> {

    private List<StickerItem>list;
    private Context context;
    public StickerAdapter(Context context) {

        this.context=context;
    }

    public void setList(List<StickerItem> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_emoji,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final StickerItem data=list.get(position);
        holder.iv_emoji.setSticker(data,holder.itemView,true);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder,position);
        } else {
            Bundle bundle = (Bundle) payloads.get(0);
            for(String key : bundle.keySet()) {
                holder.iv_emoji.setText(true,bundle.getString(key));
            }
        }
    }

    public StickerItem getItem(int position){
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        MyStickerEmojiCell iv_emoji;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_emoji=itemView.findViewById(R.id.iv_emoji);
        }


    }
}

