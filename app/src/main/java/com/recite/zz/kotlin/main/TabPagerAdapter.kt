package com.recite.zz.kotlin.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by gnehz972 on 18/3/10.
 */
class TabPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm){
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