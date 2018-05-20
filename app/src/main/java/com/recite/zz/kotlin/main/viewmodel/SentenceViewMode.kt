package com.recite.zz.kotlin.main.viewmodel

import android.content.SharedPreferences
import androidx.content.edit
import com.recite.zz.kotlin.ext.await
import com.recite.zz.kotlin.repository.SentenceRepository
import com.recite.zz.kotlin.repository.api.MainApi
import com.recite.zz.kotlin.repository.data.DailySentence
import com.recite.zz.kotlin.repository.db.WordDao
import com.recite.zz.kotlin.repository.sp.Sp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.async
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by gnehz972 on 18/3/12.
 */
class SentenceViewMode @Inject constructor(private val sentenceRepository: SentenceRepository,
                                           private val wordDao: WordDao,
                                           private val mainApi: MainApi,
                                           private val sp: SharedPreferences) {

    fun fetchDailySentence(): Observable<List<DailySentence>> {
        val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
        val lastCheckDate = sp.getString(Sp.DAILY_SENTENCE_CHECKDATE, "")



        return kotlin.run {
            if (lastCheckDate != date) {
                Observable.concatArray(sentenceRepository.getDailySentencesApi()
                        .doOnNext { sp.edit { putString(Sp.DAILY_SENTENCE_CHECKDATE, date) } },
                        sentenceRepository.getDailySentencesDb())
            } else {
                sentenceRepository.getDailySentencesDb()

            }
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


    }

    suspend fun getDailySentence(): List<DailySentence> {
        try {

            val lastCheckDate = sp.getString(Sp.DAILY_SENTENCE_CHECKDATE, "")
            val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
            if (lastCheckDate != date) {
                val netSentence = mainApi.fetchDailySentence().await()
                async { wordDao.addDailySentence(netSentence) }.await()
            }

            return wordDao.getDailySentences()
                    .subscribeOn(Schedulers.io())
                    .await()

        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }


}