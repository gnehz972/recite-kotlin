package com.recite.zz.kotlin.repository

import com.recite.zz.kotlin.repository.data.DailySentence
import com.recite.zz.kotlin.repository.data.Word
import com.recite.zz.kotlin.repository.db.WordDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by zouzheng on 18-3-8.
 */
class WordRepository(private val wordDao: WordDao) {

    fun getAllWords(): Observable<List<Word>> {
        return wordDao.getAllWord()
                .filter { it.isNotEmpty() }
                .toObservable()
    }

    fun addWord(word: Word) : Observable<Unit>{
      return Observable.fromCallable { wordDao.addSingleWord(word) }
    }


    fun addDailySentence(sentence: DailySentence) : Observable<Unit>{
        return Observable.fromCallable { wordDao.addDailySentence(sentence) }
    }
}