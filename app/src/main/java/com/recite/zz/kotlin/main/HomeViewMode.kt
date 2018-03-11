package com.recite.zz.kotlin.main

import com.recite.zz.kotlin.repository.SentenceRepository
import com.recite.zz.kotlin.repository.data.DailySentence
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by gnehz972 on 18/3/11.
 */
class HomeViewMode(private val sentenceRepo: SentenceRepository){


    fun fetchDailySentence(): Observable<DailySentence> {
        return sentenceRepo.fetchDailySentence()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}