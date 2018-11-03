package com.recite.zz.kotlin.main

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.recite.zz.kotlin.base.BaseFragment
import com.recite.zz.kotlin.R
import com.recite.zz.kotlin.main.viewmodel.SentenceViewMode
import com.recite.zz.kotlin.repository.data.DailySentence
import com.recite.zz.kotlin.view.DailyView
import com.recite.zz.kotlin.view.SwipeLayout
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by gnehz972 on 18/3/10.
 */
class HomeFragment : BaseFragment() {
    @Inject
    lateinit var sentenceViewMode: SentenceViewMode
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
        Log.i("zoz", "onHiddenChanged " + hidden + " " + this)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        sentenceViewMode.fetchDailySentence()
//                .subscribe {
//                    updateDailyView(it)
//
//                }
        GlobalScope.launch(Dispatchers.Main) {
            val sentences = sentenceViewMode.getDailySentence()
            updateDailyView(sentences)
        }

        val frontView = layoutInflater.inflate(R.layout.dailyview_layout, null)
        val backView = layoutInflater.inflate(R.layout.dailyview_layout, null)
        swipeLayout.setFrontBackView(frontView, backView)
        swipeLayout.setSwipeListener(object : SwipeLayout.SwipeListener {
            override fun onSwipeEnd(front: View, back: View) {
                if (front is DailyView && back is DailyView) {
                    front.showNext()
                    back.showNext()

                    val maxHeight = Math.max(front.heightNeeded, back.heightNeeded)
                    val params = front.layoutParams
                    params?.height = maxHeight
                    front.layoutParams = params
                    back.layoutParams = params
                }
            }


        })


    }

    private fun updateDailyView(dailySentences: List<DailySentence>) {
        val frontView = swipeLayout.getFrontView()
        val backView = swipeLayout.getBackView()

        if (frontView is DailyView && backView is DailyView) {
            when (dailySentences.size) {
                0 -> {
                    frontView.visibility = View.GONE
                    backView.visibility = View.GONE
                }
                1 -> {
                    frontView.updateSentence(dailySentences, 0)
                    backView.visibility = View.GONE
                }
                else -> {
                    frontView.updateSentence(dailySentences, 0)
                    backView.updateSentence(dailySentences, 1)
                    activity?.window?.decorView?.post {
                        mHandler.post {
                            val maxHeight = Math.max(frontView.heightNeeded, backView.heightNeeded)
                            val params = frontView.layoutParams
                            params.height = maxHeight
                            frontView.layoutParams = params
                            backView.layoutParams = params
                        }
                    }
                }
            }
        }

    }
}