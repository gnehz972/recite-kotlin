package com.recite.zz.kotlin.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.recite.zz.kotlin.R
import com.recite.zz.kotlin.ext.loadUrl
import com.recite.zz.kotlin.ext.screenWidth
import com.recite.zz.kotlin.repository.data.DailySentence
import kotlinx.android.synthetic.main.dailyview_layout.view.*


/**
 * Created with Android Studio.
 * User: Michael
 * Date: 2015/11/19
 * Time: 23:07
 */
class DailyView : LinearLayout {
    private var mHeadImg: ImageView? = null
    private var mDailySentence: TextView? = null
    private var mDailyTrans: TextView? = null
    private var mDailyNote: TextView? = null
    private var mDailyTitle: TextView? = null
    private var mAllSentences: List<DailySentence>? = null
    private var mPosition: Int = 0

    val heightNeeded: Int
        get() = paddingTop + paddingBottom +
                mDailyTitle!!.lineCount * mDailyTitle!!.lineHeight +
                mDailySentence!!.lineCount * mDailySentence!!.lineHeight +
                mDailyTrans!!.lineCount * mDailyTrans!!.lineHeight +
                mDailyNote!!.lineCount * mDailyNote!!.lineHeight +
                mDailyTitle!!.paddingTop +
                mDailyTitle!!.paddingBottom + 50 + resources.screenWidth * 260/ 160

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


    override fun onFinishInflate() {
        super.onFinishInflate()
        mHeadImg = findViewById(R.id.headImg)
        mDailyTitle = findViewById(R.id.dict_daily_title)
        mDailySentence = findViewById(R.id.dict_daily_sentence)
        mDailyTrans = findViewById(R.id.dict_daily_sentence_trans)
        mDailyNote = findViewById(R.id.dict_daily_sentence_note)

    }

    private fun bindView(sentence: DailySentence) {
        headImg.loadUrl(sentence.picture)

        mDailyTitle!!.text = context.getString(R.string.ciba_daily) + " " + sentence.dateline
        mDailySentence!!.text = sentence.content
        mDailyTrans!!.text = sentence.note
        mDailyNote!!.text = sentence.translation
    }

    fun updateSentence(sentences: List<DailySentence>, position: Int) {
        mAllSentences = sentences
        mPosition = position
        val sentence = mAllSentences!![position]
        if (sentence != null) {
            bindView(sentence)
        }
    }

    fun showNext() {
        mPosition++
        mPosition = mPosition % mAllSentences!!.size
        val sentence = mAllSentences!![mPosition]
        if (sentence != null) {
            bindView(sentence)
        }
    }

    fun shouldShow(): Boolean {
        return mAllSentences != null && !mAllSentences!!.isEmpty()
    }
}
