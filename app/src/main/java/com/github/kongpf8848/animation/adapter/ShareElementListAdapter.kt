package com.github.kongpf8848.animation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.bean.ImageBean


class ShareElementListAdapter(private val mContext: Context, private val mList: List<ImageBean>) : RecyclerView.Adapter<ShareElementListAdapter.ViewHolder>() {

    private lateinit var mOnCallBack: OnCallBack

    interface OnCallBack {
        fun onClick(view: View,position:Int)
    }

    fun setOnClickListener(mOnCallBack: OnCallBack) {
        this.mOnCallBack = mOnCallBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_share_element_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mList[position]
        Glide.with(mContext)
                .load(data.resId)
                .apply(RequestOptions().skipMemoryCache(true))
                .into(holder.mImg)
        ViewCompat.setTransitionName(holder.mImg,data.name)
        holder.mImg.setOnClickListener {
            mOnCallBack.onClick(holder.mImg,position)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mImg: ImageView = itemView.findViewById(R.id.item_img)
    }

}
