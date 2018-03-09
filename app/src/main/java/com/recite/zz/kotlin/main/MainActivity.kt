package com.recite.zz.kotlin.main

import android.os.Bundle
import com.recite.zz.kotlin.base.BaseActivity
import com.recite.zz.kotlin.repository.data.DailySentence
import com.recite.zz.kotlin.repository.data.Word
import com.recite.zz.mvvm_kotlin.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var wordViewModel: WordViewModel
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getComponent().inject(this)

        addTv.setOnClickListener {
//            count++
//            val word = Word("eng_"+count,"ddd","dd","","","")
//            wordViewModel.addSingleWord(word)


            wordViewModel.fetchDailySentence()
                    .subscribe{
                        textTv.text = it.content
                    }
        }
    }
}
