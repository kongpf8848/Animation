package com.github.kongpf8848.animation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.viewpager.widget.ViewPager
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.adapter.ViewPagerAdapter
import com.github.kongpf8848.animation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_launch_whats_new.*
import kotlinx.android.synthetic.main.fragment_title.*

class WhatsNewFragment : BaseFragment() {

    private val images= arrayOf(R.drawable.whats_new_superpowers,R.drawable.whats_new_smartinbox,R.drawable.whats_new_emails_for_team)
    private val titles= arrayOf("体验强大的邮件功能","Smart Inbox","Spark 团队版")
    private val descriptions= arrayOf("放飞您的收件箱，创新的邮件工具让您回归舒适。","只看最重要的邮件，其他的，一扫即可。","与团队分享、创建、讨论邮件。")
    companion object{
        fun newInstance():WhatsNewFragment{
            return WhatsNewFragment()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_launch_whats_new,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list= mutableListOf<View>()
        for(i in 0 until 3){
            val view=LayoutInflater.from(context).inflate(R.layout.fragment_launch_whats_new_info,null,false)
            val iv_image=view.findViewById<ImageView>(R.id.whats_new_image_info)
            iv_image.setImageResource(images[i])
            val tv_title=view.findViewById<TextView>(R.id.whats_new_text_title)
            tv_title.text = titles[i]
            val tv_description=view.findViewById<TextView>(R.id.whats_new_text_description)
            tv_description.text = descriptions[i]
            list.add(view)
        }
        whats_new_view_pager.adapter=ViewPagerAdapter(list)
        whats_new_view_pager.addOnPageChangeListener(object: ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                if(position==2){
                    whats_new_button_got_it.visibility=View.VISIBLE
                    whats_new_skip_text_view.visibility=View.GONE
                    whats_new_next_text_view.visibility=View.GONE
                }
                else{
                    whats_new_button_got_it.visibility=View.GONE
                    whats_new_skip_text_view.visibility=View.VISIBLE
                    whats_new_next_text_view.visibility=View.VISIBLE
                }
            }

        })
        whats_new_view_pager_indicator.setViewPager(whats_new_view_pager)

    }
}