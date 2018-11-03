package com.recite.zz.kotlin.repository

import com.recite.zz.kotlin.repository.api.MainApi
import com.recite.zz.kotlin.repository.api.WordApi
import com.recite.zz.kotlin.repository.data.DailySentence
import com.recite.zz.kotlin.repository.db.WordDao
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.rx2.await
import javax.inject.Inject

/**
 * Created by gnehz972 on 18/3/9.
 */
class SentenceRepository @Inject constructor (private val wordApi: WordApi,private val mainApi: MainApi, private val wordDao: WordDao){


    fun getDailySentencesDb():Observable<List<DailySentence>>{
      return  wordDao.getDailySentences()
                .toObservable()

    }

    fun getDailySentencesApi():Observable<List<DailySentence>>{
        return  wordApi.fetchDailySentence()
                .doOnNext { saveDailySentenceInDb(it) }
                .toList()
                .toObservable()
    }


    fun saveDailySentenceInDb(sentences:DailySentence){
        Observable.fromCallable { wordDao.addDailySentence(sentences) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe()
    }

    fun getDailySentences():Deferred<DailySentence>{
        return mainApi.fetchDailySentence()
    }

    fun getDailySentenceDb(): Deferred<List<DailySentence>> {
        return GlobalScope.async { wordDao.getDailySentences().await() }
    }





}