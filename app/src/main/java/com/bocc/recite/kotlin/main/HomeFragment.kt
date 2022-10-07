package com.bocc.recite.kotlin.main

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bocc.recite.R
import com.bocc.recite.databinding.FragmentHomeBinding
import com.bocc.recite.kotlin.base.BaseFragment
import com.bocc.recite.kotlin.main.viewmodel.SentenceViewMode
import com.bocc.recite.kotlin.repository.data.DailySentence
import com.bocc.recite.kotlin.view.DailyView
import com.bocc.recite.kotlin.view.SwipeLayout
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by gnehz972 on 18/3/10.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val sentenceViewMode by viewModels<SentenceViewMode>()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("zoz22","HomeFragment oncreate savedInstanceState=$savedInstanceState + this=$this")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val frontView = DailyView(requireContext())
        val backView = DailyView(requireContext())
        binding.swipeLayout.setFrontBackView(frontView, backView)
        binding.swipeLayout.setSwipeListener(object : SwipeLayout.SwipeListener {
            override fun onSwipeEnd(front: View, back: View) {
                if (front is DailyView && back is DailyView) {
                    front.showNext()
                    back.showNext()
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
        val frontView =binding.swipeLayout.getFrontView()
        val backView = binding.swipeLayout.getBackView()

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
                }
            }
        }

    }
}