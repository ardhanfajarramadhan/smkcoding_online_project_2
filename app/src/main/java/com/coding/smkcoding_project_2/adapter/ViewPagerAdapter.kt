package com.coding.smkcoding_project_2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.coding.smkcoding_project_2.*

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val JUMLAH_MENU = 5
    override fun createFragment(position: Int) : Fragment {
        return when(position){
            0 -> {
                WorldFragment()
            }
            1 -> {
                ProvinceFragment()
            }
            2 -> {
                ChartFragment()
            }
            3 -> {
                WebFragment()
            }
            4 -> {
                TutorialFragment()
            }
            else -> {
                WorldFragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }
}