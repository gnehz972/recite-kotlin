package com.recite.zz.kotlin.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.recite.zz.kotlin.base.BaseApp
import com.recite.zz.kotlin.repository.SentenceRepository
import com.recite.zz.kotlin.repository.WordRepository
import com.recite.zz.kotlin.repository.api.WordApi
import com.recite.zz.kotlin.repository.db.AppDatabase
import com.recite.zz.kotlin.repository.db.WordDao
import com.recite.zz.kotlin.repository.sp.Sp
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by zouzheng on 18-3-8.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideWordDao(app:BaseApp) = Room.databaseBuilder(app,
            AppDatabase::class.java, "recite_db").build().wordDao()

    @Provides
    @Singleton
    fun provideWordRepo(wordDao: WordDao) = WordRepository(wordDao)

    @Provides
    @Singleton
    fun provideSentenceRepo(wordApi: WordApi,wordDao: WordDao) = SentenceRepository(wordApi,wordDao)

    @Provides
    @Singleton
    fun provideApiService() : WordApi = Retrofit.Builder()
            .baseUrl("http://open.iciba.com")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WordApi::class.java)

    @Provides
    @Singleton
    fun providePreference(app: BaseApp) = app.getSharedPreferences(Sp.SP_FILE_NAME, Context.MODE_PRIVATE)


}