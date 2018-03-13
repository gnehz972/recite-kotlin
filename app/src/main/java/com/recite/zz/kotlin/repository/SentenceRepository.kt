package com.recite.zz.kotlin.repository

import com.recite.zz.kotlin.repository.api.WordApi
import com.recite.zz.kotlin.repository.data.DailySentence
import io.reactivex.Observable

/**
 * Created by gnehz972 on 18/3/9.
 */
class SentenceRepository(private val wordApi: WordApi){


    fun fetchDailySentence():Observable<DailySentence>{
        return wordApi.fetchDailySentence()
    }
//
//    fun getDailySentences():Observable<List<DailySentence>>{
//
//    }

}