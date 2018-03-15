package com.recite.zz.kotlin.main

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.content.edit
import com.recite.zz.kotlin.base.BaseFragment
import com.recite.zz.kotlin.R
import com.recite.zz.kotlin.config.GlideApp
import com.recite.zz.kotlin.main.viewmodel.SentenceViewMode
import com.recite.zz.kotlin.repository.sp.Sp
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * Created by gnehz972 on 18/3/10.
 */
class HomeFragment : BaseFragment() {
    @Inject
    lateinit var sentenceViewMode : SentenceViewMode
    @Inject
    lateinit var sp: SharedPreferences


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.i("zoz","onHiddenChanged "+hidden+ " "+this)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sentenceViewMode.let {  }
        sentenceViewMode.fetchDailySentence()
                .subscribe {
                    var text = ""
                    for (sentence in it){
                        text+= sentence.caption+"\n"

                    }
                    testTv.text = text
                    val img = it[0].picture
                    GlideApp.with(this)
                            .load(img)
                            .into(testImg)
                }

        sp.edit { putString(Sp.CARD_NAME,"card")}
    }
}