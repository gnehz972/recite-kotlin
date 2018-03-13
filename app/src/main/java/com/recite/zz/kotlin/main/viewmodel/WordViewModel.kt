package com.recite.zz.kotlin.main.viewmodel

import com.recite.zz.kotlin.repository.WordRepository
import com.recite.zz.kotlin.repository.data.Word
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by zouzheng on 18-3-8.
 */
class WordViewModel @Inject constructor(private val wordRepo: WordRepository) {

    fun getAllWords(): Observable<List<Word>> {

        return wordRepo.getAllWords()
    }

    fun addWord(word: Word) : Observable<Unit> {
       return wordRepo.addWord(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}