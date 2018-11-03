package com.recite.zz.kotlin.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by gnehz972 on 18/3/10.
 */
class TabPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return HomeFragment()
    }

    override fun getCount(): Int {
       return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "tab".plus(position)
    }
}