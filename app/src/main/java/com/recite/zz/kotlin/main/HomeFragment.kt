package com.recite.zz.kotlin.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.recite.zz.kotlin.base.BaseFragment
import com.recite.zz.kotlin.R
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * Created by gnehz972 on 18/3/10.
 */
class HomeFragment : BaseFragment() {
    @Inject
    lateinit var sentenceViewMode : SentenceViewMode


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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sentenceViewMode.fetchDailiSentence()
                .subscribe { testTv.text = it.content }
    }
}