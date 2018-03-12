package com.recite.zz.kotlin.di.module

import android.support.v7.app.AppCompatActivity
import com.recite.zz.kotlin.repository.WordRepository
import com.recite.zz.kotlin.main.WordViewModel
import com.recite.zz.kotlin.repository.SentenceRepository
import dagger.Module
import dagger.Provides

/**
 * Created by zouzheng on 18-3-8.
 */
@Module()
class ActivityModule {


    @Provides
    fun provideWordViewModel(wordRepository: WordRepository,sentenceRepository: SentenceRepository) = WordViewModel(wordRepository,sentenceRepository)
}