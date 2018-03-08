package com.recite.zz.kotlin.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.recite.zz.kotlin.repository.WordRepository
import com.recite.zz.kotlin.repository.db.AppDatabase
import com.recite.zz.kotlin.repository.db.WordDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by zouzheng on 18-3-8.
 */
@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    fun provideWordDao() = Room.databaseBuilder(app,
            AppDatabase::class.java, "recite_db").build().wordDao()

    @Provides
    @Singleton
    fun provideWordRepo(wordDao: WordDao) = WordRepository(wordDao)

}