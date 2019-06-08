package com.bocc.recite.kotlin.repository

import com.bocc.recite.kotlin.repository.api.MainApi
import com.bocc.recite.kotlin.repository.api.WordApi
import com.bocc.recite.kotlin.repository.data.DailySentence
import com.bocc.recite.kotlin.repository.db.WordDao
import javax.inject.Inject

/**
 * Created by gnehz972 on 18/3/9.
 */
class SentenceRepository @Inject constructor (private val wordApi: WordApi,
                                              private val mainApi: MainApi,
                                              private val wordDao: WordDao){


    suspend fun getDailySentencesDb():List<DailySentence>{
      return  wordDao.getDailySentences2()
    }

    suspend fun getDailySentencesApi() : DailySentence{
        return  mainApi.fetchDailySentence()
    }


    suspend fun saveDailySentenceInDb(sentences:DailySentence){
        wordDao.addDailySentence(sentences)
    }


}