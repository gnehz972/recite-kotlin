package com.recite.zz.kotlin.main.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.recite.zz.kotlin.repository.SentenceRepository
import com.recite.zz.kotlin.repository.data.DailySentence
import com.recite.zz.kotlin.repository.sp.Sp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by gnehz972 on 18/3/12.
 */
class SentenceViewMode @Inject constructor(private val sentenceRepository: SentenceRepository,
                                           private val sp: SharedPreferences) : ViewModel() {


    fun getSentences() = liveData {
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

    class Factory(private val sentenceRepository: SentenceRepository,
                  private val sp: SharedPreferences) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SentenceViewMode(sentenceRepository, sp) as T
        }
    }


}