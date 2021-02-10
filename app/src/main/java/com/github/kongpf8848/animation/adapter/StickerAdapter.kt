package com.github.kongpf8848.animation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.bean.StickerItem
import kotlinx.android.synthetic.main.item_emoji.view.*

class StickerAdapter(private val context: Context, private val list: List<StickerItem>) : RecyclerView.Adapter<StickerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_emoji, viewGroup, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.itemView.iv_emoji.setSticker(data)
    }


    fun getItem(position: Int): StickerItem {
        return list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}