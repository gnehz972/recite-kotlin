package com.recite.zz.kotlin.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.recite.zz.kotlin.repository.data.DailySentence
import com.recite.zz.kotlin.repository.data.Word

/**
 * Created by zouzheng on 18-3-8.
 */
@Database(entities = arrayOf(Word::class,DailySentence::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}