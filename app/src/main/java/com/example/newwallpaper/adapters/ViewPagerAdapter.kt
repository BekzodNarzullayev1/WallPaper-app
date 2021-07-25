package com.example.newwallpaper.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.newwallpaper.CategoryFragment
import com.example.newwallpaper.models.Category

class ViewPagerAdapter(fragmentManager: FragmentManager, var list: List<Category>):FragmentPagerAdapter(
    fragmentManager
) {
    override fun getCount(): Int  = list.size

    override fun getItem(position: Int): Fragment {
        return CategoryFragment.newInstance(list[position])
    }

}