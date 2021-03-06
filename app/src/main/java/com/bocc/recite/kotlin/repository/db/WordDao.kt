package com.bocc.recite.kotlin.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bocc.recite.kotlin.repository.data.DailySentence
import com.bocc.recite.kotlin.repository.data.Word

/**
 * Created by zouzheng on 18-3-8.
 */
@Dao
interface WordDao {

    @Query("select * from Word")
    suspend fun getAllWord(): List<Word>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSingleWord(word: Word)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllWord(words: List<Word>)

    @Insert
    suspend fun addDailySentence(sentence: DailySentence)

    @Query("select * from DailySentence order by id desc limit 7")
    suspend fun getDailySentences2() : List<DailySentence>
}