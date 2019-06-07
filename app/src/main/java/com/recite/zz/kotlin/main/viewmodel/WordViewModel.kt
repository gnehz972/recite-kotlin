package com.recite.zz.kotlin.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.recite.zz.kotlin.repository.WordRepository
import com.recite.zz.kotlin.repository.data.Word
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by zouzheng on 18-3-8.
 */
class WordViewModel @Inject constructor(private val wordRepo: WordRepository) : ViewModel() {

    fun getAllWords() = liveData {
        try {
            emit(Result.success(wordRepo.getAllWords()))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun addWord(word: Word) {
        viewModelScope.launch {
            wordRepo.addWord(word)
        }
    }

}