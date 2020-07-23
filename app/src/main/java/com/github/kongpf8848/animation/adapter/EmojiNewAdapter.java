package com.github.kongpf8848.animation.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.kongpf8848.animation.R;
import com.github.kongpf8848.animation.bean.EmotionItemBean;
import com.github.kongpf8848.animation.widget.MyStickerEmojiCell;

import java.util.List;

public class EmojiNewAdapter extends RecyclerView.Adapter<EmojiNewAdapter.ViewHolder> {

    private List<EmotionItemBean>list;
    private Context context;
    public EmojiNewAdapter(Context context) {

        this.context=context;
    }

    public void setList(List<EmotionItemBean> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_emoji,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final EmotionItemBean data=list.get(position);
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

    public EmotionItemBean getItem(int position){
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

