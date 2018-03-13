package com.recite.zz.kotlin.repository

import com.recite.zz.kotlin.repository.api.WordApi
import com.recite.zz.kotlin.repository.data.DailySentence
import com.recite.zz.kotlin.repository.db.WordDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by gnehz972 on 18/3/9.
 */
class SentenceRepository(private val wordApi: WordApi,private val wordDao: WordDao){


    private fun getDailySentencesDb():Observable<List<DailySentence>>{
      return  wordDao.getDailySentences()
                .toObservable()

    }

   private fun getDailySentencesApi():Observable<List<DailySentence>>{
        return  wordApi.fetchDailySentence()
                .doOnNext { saveDailySentenceInDb(it) }
                .toList()
                .toObservable()
    }

    fun fetchDailySentence() : Observable<List<DailySentence>>{
        return Observable.concatArray(
                getDailySentencesDb(),
                getDailySentencesApi()
        )
    }

    private fun saveDailySentenceInDb(sentences:DailySentence){
        Observable.fromCallable { wordDao.addDailySentence(sentences) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe()
    }





}