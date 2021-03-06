package com.bocc.recite.kotlin.repository.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by zouzheng on 18-3-8.
 */
@Entity(tableName = "Word")
data class Word(
        @PrimaryKey
        val keyword: String,
        val explain: String,
        val ps: String,
        val sentence: String,
        val image: String,
        val article: String
)