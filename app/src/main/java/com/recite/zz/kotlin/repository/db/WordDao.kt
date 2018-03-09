package com.recite.zz.kotlin.repository.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.recite.zz.kotlin.repository.data.Word
import io.reactivex.Single

/**
 * Created by zouzheng on 18-3-8.
 */
@Dao
interface WordDao {

    @Query("select * from Word")
    fun getAllWord(): Single<List<Word>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSingleWord(word: Word)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllWord(words: List<Word>)
}