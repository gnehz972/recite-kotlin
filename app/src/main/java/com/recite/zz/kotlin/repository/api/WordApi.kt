package com.recite.zz.kotlin.repository.api

import com.recite.zz.kotlin.repository.data.Word
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by zouzheng on 18-3-8.
 */
interface WordApi {

    @GET("/baidu.com")
    fun getWords(): Observable<List<Word>>
}