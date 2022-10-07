package com.bocc.recite.kotlin.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout

import com.bocc.recite.R
import com.bocc.recite.databinding.DailyviewLayoutBinding
import com.bocc.recite.kotlin.ext.loadUrl
import com.bocc.recite.kotlin.ext.screenWidth
import com.bocc.recite.kotlin.repository.data.DailySentence


/**
 * Created with Android Studio.
 * User: Michael
 * Date: 2015/11/19
 * Time: 23:07
 */
class DailyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {
    private var mAllSentences: List<DailySentence>? = null
    private var mPosition: Int = 0
    private val binding =
        DailyviewLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    private fun bindView(sentence: DailySentence) {
        binding.headImg.loadUrl(sentence.picture)
        binding.dictDailyTitle.text =
            context.getString(R.string.ciba_daily) + " " + sentence.dateline
        binding.dictDailySentence.text = sentence.content
        binding.dictDailySentenceTrans.text = sentence.note
        binding.dictDailySentenceNote.text = sentence.translation
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
