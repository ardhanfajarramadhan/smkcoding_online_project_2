package com.coding.smkcoding_project_2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val JUMLAH_MENU = 3
    override fun createFragment(position: Int) : Fragment {
        return when(position){
            0 -> {
                MyFriendFragment()
            }
            1 -> {
                GithubFragment()
            }
            2 -> {
                ProfileFragment()
            }
            else -> {
                GithubFragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }
}