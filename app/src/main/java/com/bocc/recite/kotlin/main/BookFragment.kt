package com.bocc.recite.kotlin.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bocc.recite.kotlin.base.BaseFragment
import com.bocc.recite.R

/**
 * Created by gnehz972 on 18/3/11.
 */
class BookFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_book, container, false)
        return view
    }

}