package com.recite.zz.kotlin.main

import com.recite.zz.kotlin.repository.WordRepository
import com.recite.zz.kotlin.repository.data.Word
import io.reactivex.Observable

/**
 * Created by zouzheng on 18-3-8.
 */
class WordViewModel(val wordRepository: WordRepository) {

    fun getAllWords(): Observable<List<Word>> {

        return wordRepository.getAllWords()
    }

    fun addWord(word: Word) {
        wordRepository.addWord(word)
    }


}