package com.bocc.recite.kotlin.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.bocc.recite.kotlin.repository.WordRepository
import com.bocc.recite.kotlin.repository.data.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by zouzheng on 18-3-8.
 */
@HiltViewModel
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