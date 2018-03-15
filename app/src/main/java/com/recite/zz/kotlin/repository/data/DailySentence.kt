package com.recite.zz.kotlin.repository.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by gnehz972 on 18/3/9.
 */
@Entity(tableName = "DailySentence")
data class DailySentence(
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,
        val content: String,
        val note: String,
        val love: String,
        val translation: String,
        val picture: String,
        val picture2: String,
        val caption: String,
        val dateline: String,
        val fenxiang_img: String
)