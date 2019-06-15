package com.bocc.recite.kotlin.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

import com.bocc.recite.R
import com.bocc.recite.kotlin.ext.loadUrl
import com.bocc.recite.kotlin.ext.screenWidth
import com.bocc.recite.kotlin.repository.data.DailySentence
import kotlinx.android.synthetic.main.dailyview_layout.view.*


/**
 * Created with Android Studio.
 * User: Michael
 * Date: 2015/11/19
 * Time: 23:07
 */
class DailyView : LinearLayout {
    private var mAllSentences: List<DailySentence>? = null
    private var mPosition: Int = 0

    val heightNeeded: Int
        get() = paddingTop + paddingBottom +
                dict_daily_title.lineCount * dict_daily_title.lineHeight +
                dict_daily_sentence.lineCount * dict_daily_sentence.lineHeight +
                dict_daily_sentence_trans.lineCount * dict_daily_sentence_trans.lineHeight +
                dict_daily_sentence_note.lineCount * dict_daily_sentence_note.lineHeight +
                dict_daily_title.paddingTop +
                dict_daily_title.paddingBottom + 50 + resources.screenWidth * 160 / 260

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


    private fun bindView(sentence: DailySentence) {
        headImg.loadUrl(sentence.picture)

        dict_daily_title.text = context.getString(R.string.ciba_daily) + " " + sentence.dateline
        dict_daily_sentence.text = sentence.content
        dict_daily_sentence_trans.text = sentence.note
        dict_daily_sentence_note.text = sentence.translation
    }

    fun updateSentence(sentences: List<DailySentence>, position: Int) {
        mAllSentences = sentences
        mPosition = position
        val sentence = mAllSentences?.getOrNull(position)
        if (sentence != null) {
            bindView(sentence)
        }
    }

    fun showNext() {
        mAllSentences?.let {
            if (it.isNotEmpty()) {
                mPosition++
                mPosition %= it.size
                val sentence = it.getOrNull(mPosition)
                if (sentence != null) {
                    bindView(sentence)
                }
            }
        }

    }
}
