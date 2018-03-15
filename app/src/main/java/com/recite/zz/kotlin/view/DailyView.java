package com.recite.zz.kotlin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.recite.zz.kotlin.R;
import com.recite.zz.kotlin.repository.data.DailySentence;

import java.util.List;


/**
 * Created with Android Studio.
 * User: Michael
 * Date: 2015/11/19
 * Time: 23:07
 */
public class DailyView extends LinearLayout {
    private TextView mDailySentence;
    private TextView mDailyTrans;
    private TextView mDailyNote;
    private TextView mDailyTitle;
    private List<DailySentence> mAllSentences;
    private int mPosition;

    public DailyView(Context context) {
        super(context);
    }

    public DailyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDailyTitle = (TextView) findViewById(R.id.dict_daily_title);
        mDailySentence = (TextView) findViewById(R.id.dict_daily_sentence);
        mDailyTrans = (TextView) findViewById(R.id.dict_daily_sentence_trans);
        mDailyNote = (TextView) findViewById(R.id.dict_daily_sentence_note);

    }

    private void bindView(DailySentence sentence) {
        mDailyTitle.setText(getContext().getString(R.string.ciba_daily) + " " + sentence.getDateline());
        mDailySentence.setText(sentence.getContent());
        mDailyTrans.setText(sentence.getNote());
        mDailyNote.setText(sentence.getTranslation());
    }

    public int getHeightNeeded(){
        return getPaddingTop() + getPaddingBottom()+
                mDailyTitle.getLineCount()*mDailyTitle.getLineHeight()+
                mDailySentence.getLineCount()*mDailySentence.getLineHeight()+
                mDailyTrans.getLineCount()*mDailyTrans.getLineHeight()+
                mDailyNote.getLineCount()*mDailyNote.getLineHeight()+
                mDailyTitle.getPaddingTop()+
                mDailyTitle.getPaddingBottom()+50
                ;


    }

    public void updateSentence(List<DailySentence> sentences, int position) {
        mAllSentences = sentences;
        mPosition = position;
        DailySentence sentence = mAllSentences.get(position);
        if (sentence != null) {
            bindView(sentence);
        }
    }

    public void showNext() {
        mPosition++;
        mPosition = mPosition % mAllSentences.size();
        DailySentence sentence = mAllSentences.get(mPosition);
        if (sentence != null) {
            bindView(sentence);
        }
    }

    public boolean shouldShow(){
        return mAllSentences!=null && !mAllSentences.isEmpty();
    }
}
