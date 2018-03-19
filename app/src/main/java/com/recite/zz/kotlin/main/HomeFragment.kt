package com.recite.zz.kotlin.main

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.content.edit
import com.recite.zz.kotlin.base.BaseFragment
import com.recite.zz.kotlin.R
import com.recite.zz.kotlin.config.GlideApp
import com.recite.zz.kotlin.ext.px
import com.recite.zz.kotlin.main.viewmodel.SentenceViewMode
import com.recite.zz.kotlin.repository.data.DailySentence
import com.recite.zz.kotlin.repository.sp.Sp
import com.recite.zz.kotlin.view.DailyView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.dailyview_layout.view.*
import javax.inject.Inject

/**
 * Created by gnehz972 on 18/3/10.
 */
class HomeFragment : BaseFragment() {
    @Inject
    lateinit var sentenceViewMode : SentenceViewMode
    @Inject
    lateinit var sp: SharedPreferences
    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

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
        sentenceViewMode.fetchDailySentence()
                .subscribe {
                    var text = ""
                    for (sentence in it){
                        text+= sentence.caption+"\n"

                    }
                    if (it.isNotEmpty()){
//                        val img = it[0].picture
//                        GlideApp.with(this)
//                                .load(img)
//                                .into(testImg)
//                        dailyView.updateSentence(it,0)
                        updateDailyView(it)
                    }
                   val ten = 10.px

                }

        sp.edit { putString(Sp.CARD_NAME,"card")}
    }

    private fun updateDailyView(dailySentences: List<DailySentence>) {
        dict_daily_touch_view.setAnimateView(dict_daily_front)
        dict_daily_touch_view.setBackView(dict_daily_back)

        if (dailySentences.isEmpty()) {
            dict_daily_front.visibility = View.GONE
            dict_daily_back.visibility = View.GONE
        } else if (dailySentences.size == 1) {
            (dict_daily_front as DailyView).updateSentence(dailySentences, 0)
            dict_daily_back.visibility = View.GONE
        } else {
            (dict_daily_front as DailyView).updateSentence(dailySentences, 0)
            (dict_daily_back as DailyView).updateSentence(dailySentences, 1)
            //set invisible to hold the space
            //            mBackDailyView.setVisibility(View.INVISIBLE);
            activity?.window?.decorView?.post{
                mHandler.post{
                    val maxHeight = Math.max((dict_daily_front as DailyView).heightNeeded, (dict_daily_back as DailyView).heightNeeded)
                    val params = dict_daily_front.layoutParams
                    params.height = maxHeight
                    dict_daily_front.layoutParams = params
                    dict_daily_back.layoutParams = params
                }
            }

        }

    }
}