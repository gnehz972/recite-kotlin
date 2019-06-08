package com.bocc.recite.kotlin.repository

import com.bocc.recite.kotlin.repository.data.Word
import com.bocc.recite.kotlin.repository.db.WordDao

/**
 * Created by zouzheng on 18-3-8.
 */
class WordRepository(private val wordDao: WordDao) {

    suspend fun getAllWords(): List<Word> {
        return wordDao.getAllWord()
    }

    suspend fun addWord(word: Word){
        wordDao.addSingleWord(word)
    }


}