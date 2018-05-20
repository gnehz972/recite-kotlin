package com.recite.zz.kotlin.repository.api

import com.recite.zz.kotlin.repository.data.DailySentence
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

/**
 * Created by gnehz972 on 18/5/19.
 */
interface MainApi {


    @GET("/dsapi")
    fun fetchDailySentence(): Deferred<DailySentence>
}