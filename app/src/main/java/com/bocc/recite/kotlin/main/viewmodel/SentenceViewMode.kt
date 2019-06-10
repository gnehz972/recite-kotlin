package com.bocc.recite.kotlin.main.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bocc.recite.kotlin.repository.SentenceRepository
import com.bocc.recite.kotlin.repository.sp.Sp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by gnehz972 on 18/3/12.
 */
class SentenceViewMode @Inject constructor(private val sentenceRepository: SentenceRepository,
                                           private val sp: SharedPreferences) : ViewModel() {

    init {
        Log.d("zoz22", "SentenceViewMode init this=$this")
    }

    val sentence =  liveData {
        try {
            val sentences = sentenceRepository.getDailySentencesDb()
            emit(Result.success(sentences))

            val lastCheckDate = sp.getString(Sp.DAILY_SENTENCE_CHECKDATE, "")
            val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
            if (lastCheckDate != date) {
                val netSentence = sentenceRepository.getDailySentencesApi()
                sentenceRepository.saveDailySentenceInDb(netSentence)

                val sentencesNew = sentenceRepository.getDailySentencesDb()
                emit(Result.success(sentencesNew))
            }

        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }

//    fun getSentences() = liveData {
//        try {
//            val sentences = sentenceRepository.getDailySentencesDb()
//            emit(Result.success(sentences))
//
//            val lastCheckDate = sp.getString(Sp.DAILY_SENTENCE_CHECKDATE, "")
//            val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
//            if (lastCheckDate != date) {
//                val netSentence = sentenceRepository.getDailySentencesApi()
//                sentenceRepository.saveDailySentenceInDb(netSentence)
//
//                val sentencesNew = sentenceRepository.getDailySentencesDb()
//                emit(Result.success(sentencesNew))
//            }
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//            emit(Result.failure(e))
//        }
//    }


}