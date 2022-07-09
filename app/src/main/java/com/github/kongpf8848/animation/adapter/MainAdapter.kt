package com.github.kongpf8848.animation.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.kongpf8848.animation.R
import com.kongpf.commonhelper.ScreenHelper
import kotlinx.android.synthetic.main.item_main.view.*

class MainAdapter(private val mList: List<Triple<String,Int,Class<*>>>) : RecyclerView.Adapter<MainAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        val data = mList[position]
        holder.itemView.tv_name.text = data.first
        if(data.second>0){
            if(data.second==R.drawable.logo_telegram ||data.second==R.drawable.logo_lottie || data.second==R.drawable.logo_gif){
                holder.itemView.iv_logo.layoutParams.width=ScreenHelper.dp2px(holder.itemView.context,40f)
            }
            Glide.with(holder.itemView).load(data.second).into(holder.itemView.iv_logo)
        }
        holder.itemView.setOnClickListener {
            val intent= Intent(holder.itemView.context,data.third)
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
}