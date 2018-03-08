package com.recite.zz.kotlin.repository.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.recite.zz.kotlin.repository.data.Word

/**
 * Created by zouzheng on 18-3-8.
 */
@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}