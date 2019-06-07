package com.recite.zz.kotlin.repository

import com.recite.zz.kotlin.repository.data.Word
import com.recite.zz.kotlin.repository.db.WordDao

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