package com.bocc.recite.kotlin.di

import android.content.Context
import androidx.room.Room
import com.bocc.recite.kotlin.repository.WordRepository
import com.bocc.recite.kotlin.repository.api.MainApi
import com.bocc.recite.kotlin.repository.api.WordApi
import com.bocc.recite.kotlin.repository.datastore.AppDataStore
import com.bocc.recite.kotlin.repository.db.AppDatabase
import com.bocc.recite.kotlin.repository.db.WordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by zouzheng on 18-3-8.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideWordDao(@ApplicationContext context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "recite_db").build().wordDao()

    @Provides
    @Singleton
    fun provideWordRepo(wordDao: WordDao) = WordRepository(wordDao)


    @Provides
    @Singleton
    fun provideApiService() : WordApi = Retrofit.Builder()
            .baseUrl("http://open.iciba.com")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WordApi::class.java)


    @Provides
    @Singleton
    fun provideMainApi() : MainApi = Retrofit.Builder()
            .baseUrl("http://open.iciba.com")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MainApi::class.java)

    @Provides
    @Singleton
    fun provideAppDataStore(@ApplicationContext context: Context): AppDataStore =
        AppDataStore(context)


}