package com.recite.zz.kotlin.main

import com.recite.zz.kotlin.repository.SentenceRepository
import com.recite.zz.kotlin.repository.data.DailySentence
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by gnehz972 on 18/3/12.
 */
class SentenceViewMode @Inject constructor(private val sentenceRepository: SentenceRepository){

    fun fetchDailiSentence() : Observable<DailySentence> {
       return sentenceRepository.fetchDailySentence()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}