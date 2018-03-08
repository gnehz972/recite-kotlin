package com.recite.zz.kotlin.di.module

import android.support.v7.app.AppCompatActivity
import com.recite.zz.kotlin.repository.WordRepository
import com.recite.zz.kotlin.main.WordViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by zouzheng on 18-3-8.
 */
@Module(subcomponents = arrayOf())
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun provideActivity() = activity

    @Provides
    fun provideWordViewModel(wordRepository: WordRepository) = WordViewModel(wordRepository)
}