package com.recite.zz.kotlin.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.recite.zz.kotlin.base.BaseFragment
import com.recite.zz.kotlin.R

/**
 * Created by gnehz972 on 18/3/10.
 */
class HomeFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.i("zoz","onHiddenChanged "+hidden+ " "+this)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.i("zoz","setUserVisibleHint "+isVisibleToUser+ " "+this)
    }
}