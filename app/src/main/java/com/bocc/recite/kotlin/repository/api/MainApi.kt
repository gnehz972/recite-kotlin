package com.bocc.recite.kotlin.repository.api

import com.bocc.recite.kotlin.repository.data.DailySentence
import retrofit2.http.GET

/**
 * Created by gnehz972 on 18/5/19.
 */
interface MainApi {


    @GET("/dsapi")
    suspend fun fetchDailySentence(): DailySentence
}