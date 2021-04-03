package com.github.kongpf8848.animation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.github.kongpf8848.animation.R
import com.github.kongpf8848.animation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_title.*

class ChatFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_title,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_title.text=TAG
    }
}