package com.recite.zz.kotlin.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.recite.zz.kotlin.base.BaseFragment
import com.recite.zz.kotlin.R

/**
 * Created by gnehz972 on 18/3/11.
 */
class SettingFragment : BaseFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_setting,container,false)
        return view
    }
}