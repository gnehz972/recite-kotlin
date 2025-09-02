package com.bocc.recite.kotlin.main.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bocc.recite.kotlin.repository.SentenceRepository
import com.bocc.recite.kotlin.repository.data.DailySentence
import com.bocc.recite.kotlin.repository.sp.Sp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by gnehz972 on 18/3/12.
 */
@HiltViewModel
class SentenceViewMode @Inject constructor(
    private val sentenceRepository: SentenceRepository,
    private val sp: SharedPreferences
) : ViewModel() {

    private val _sentence = MutableStateFlow<Result<List<DailySentence>>?>(null)
    val sentence: StateFlow<Result<List<DailySentence>>?> = _sentence.asStateFlow()

    init {
        Log.d("zoz22", "SentenceViewMode init this=$this")
        loadSentences()
    }

    private fun loadSentences() {
        viewModelScope.launch {
            try {
                val sentences = sentenceRepository.getDailySentencesDb()
                _sentence.value = Result.success(sentences)

                val lastCheckDate = sp.getString(Sp.DAILY_SENTENCE_CHECKDATE, "")
                val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
                if (lastCheckDate != date) {
                    val netSentence = sentenceRepository.getDailySentencesApi()
                    sentenceRepository.saveDailySentenceInDb(netSentence)

                    val sentencesNew = sentenceRepository.getDailySentencesDb()
                    _sentence.value = Result.success(sentencesNew)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                _sentence.value = Result.failure(e)
            }
        }
    }

}