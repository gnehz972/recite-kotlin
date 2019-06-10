package com.bocc.recite.kotlin.main

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.bocc.recite.kotlin.R
import com.bocc.recite.kotlin.base.BaseFragment
import com.bocc.recite.kotlin.main.viewmodel.SentenceViewMode
import com.bocc.recite.kotlin.repository.data.DailySentence
import com.bocc.recite.kotlin.view.DailyView
import com.bocc.recite.kotlin.view.SwipeLayout
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * Created by gnehz972 on 18/3/10.
 */
class HomeFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var sentenceViewMode: SentenceViewMode
    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        Log.d("zoz22","HomeFragment oncreate savedInstanceState=$savedInstanceState + this=$this")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sentenceViewMode = ViewModelProviders.of(this,viewModelFactory).get(SentenceViewMode::class.java)

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

        sentenceViewMode.sentence.observe(viewLifecycleOwner){ sentences ->
            Log.d("zoz22","HomeFragment observe sentences=$sentences",Error())

            sentences.onSuccess {
                updateDailyView(it)
            }

        }

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