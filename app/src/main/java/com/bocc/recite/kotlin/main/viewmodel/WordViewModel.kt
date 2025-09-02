package com.bocc.recite.kotlin.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bocc.recite.kotlin.repository.WordRepository
import com.bocc.recite.kotlin.repository.data.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by zouzheng on 18-3-8.
 */
@HiltViewModel
class WordViewModel @Inject constructor(private val wordRepo: WordRepository) : ViewModel() {

    private val _words = MutableStateFlow<Result<List<Word>>?>(null)
    val words: StateFlow<Result<List<Word>>?> = _words.asStateFlow()

    fun getAllWords() {
        viewModelScope.launch {
            try {
                val wordsList = wordRepo.getAllWords()
                _words.value = Result.success(wordsList)
            } catch (e: Exception) {
                _words.value = Result.failure(e)
            }
        }
    }

    fun addWord(word: Word) {
        viewModelScope.launch {
            try {
                wordRepo.addWord(word)
                // Refresh the words list after adding
                getAllWords()
            } catch (e: Exception) {
                _words.value = Result.failure(e)
            }
        }
    }

}