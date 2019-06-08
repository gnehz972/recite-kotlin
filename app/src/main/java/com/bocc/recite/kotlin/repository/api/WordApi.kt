package com.bocc.recite.kotlin.repository.api

import com.bocc.recite.kotlin.repository.data.DailySentence
import retrofit2.http.GET

/**
 * Created by zouzheng on 18-3-8.
 */
interface WordApi {

    @GET("/dsapi")
    suspend fun fetchDailySentence(): DailySentence
}