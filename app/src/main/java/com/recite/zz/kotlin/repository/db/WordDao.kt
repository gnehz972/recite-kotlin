package com.recite.zz.kotlin.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.recite.zz.kotlin.repository.data.DailySentence
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

    @Insert()
    fun addDailySentence(sentence: DailySentence)

    @Query("select * from DailySentence order by id desc limit 7")
    fun getDailySentences() : Single<List<DailySentence>>

    @Query("select * from DailySentence order by id desc limit 7")
    fun getDailySentences2() : List<DailySentence>
}