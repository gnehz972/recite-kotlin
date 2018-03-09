package com.recite.zz.kotlin.main

import com.recite.zz.kotlin.repository.SentenceRepository
import com.recite.zz.kotlin.repository.WordRepository
import com.recite.zz.kotlin.repository.data.DailySentence
import com.recite.zz.kotlin.repository.data.Word
import io.reactivex.Observable

/**
 * Created by zouzheng on 18-3-8.
 */
class WordViewModel(private val wordRepo: WordRepository, private val sentenceRepo: SentenceRepository) {

    fun getAllWords(): Observable<List<Word>> {

        return wordRepo.getAllWords()
    }

    fun addWord(word: Word) {
        wordRepo.addWord(word)
    }

    fun fetchDailySentence(): Observable<DailySentence> {
        return sentenceRepo.fetchDailySentence()
    }


}