package com.recite.zz.kotlin.main.viewmodel

import android.content.SharedPreferences
import androidx.content.edit
import com.recite.zz.kotlin.repository.SentenceRepository
import com.recite.zz.kotlin.repository.WordRepository
import com.recite.zz.kotlin.repository.data.DailySentence
import com.recite.zz.kotlin.repository.sp.Sp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter
import javax.inject.Inject

/**
 * Created by gnehz972 on 18/3/12.
 */
class SentenceViewMode @Inject constructor(private val sentenceRepository: SentenceRepository,
                                           private val sp: SharedPreferences) {

    fun fetchDailySentence(): Observable<List<DailySentence>> {
        val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
        val lastCheckDate = sp.getString(Sp.DAILY_SENTENCE_CHECKDATE, "")


        return if (lastCheckDate != date) {
            Observable.concatArray(sentenceRepository.getDailySentencesApi()
                    .doOnNext { sp.edit { putString(Sp.DAILY_SENTENCE_CHECKDATE, date) } },
                    sentenceRepository.getDailySentencesDb())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        } else {
            sentenceRepository.getDailySentencesDb()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }


    }


}