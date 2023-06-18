
package com.github.kongpf8848.animation.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.kongpf8848.animation.fragment.IntroFragment

class IntroPagerAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(manager, lifecycle) {

    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return IntroFragment().apply {
            arguments = Bundle().apply {
                putInt("position",position)
            }
        }
    }
}
