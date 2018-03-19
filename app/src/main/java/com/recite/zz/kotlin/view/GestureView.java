package com.recite.zz.kotlin.view;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.recite.zz.kotlin.ext.LogUtil;

/**
 * Created with Android Studio.
 * User: Michael
 * Date: 2015/11/19
 * Time: 23:21
 */
public class GestureView extends View {
    private static final int TRIGER_DEGREE = 20;
    private static final float MAX_FLING_VELOCITY = 2000;
    private static final int UNIT = 1000; // 计算速率的单位（毫秒）


    private int mHeight, mWidth;
    private float mCurrentX, mCurrentY, mFirtX, mFirstY;
    private int[] mLocation = new int[2];
    private int mPinX, mPinY;
    private float mFirstAngle, mCurrentAngle;

    private final BaseSpringSystem mSpringSystem = SpringSystem.create();
    private final ExampleSpringListener mSpringListener = new ExampleSpringListener();
    private Spring rotateSpring;
    private Spring mScaleSpring;
    private Spring tranSpring;
    private View mAnimateView;
    private View mBackView;
    private VelocityTracker vTracker;
    private boolean mIsAnimating;

    public GestureView(Context context) {
        super(context);
        init(context);
    }

    public GestureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        mScaleSpring = mSpringSystem.createSpring();
        tranSpring = mSpringSystem.createSpring();
        rotateSpring = mSpringSystem.createSpring();
        mScaleSpring.addListener(mSpringListener);
        tranSpring.addListener(mSpringListener);
        rotateSpring.addListener(mSpringListener);
        rotateSpring.addListener(mSpringListener);
        ViewConfiguration configuration = ViewConfiguration.get(context);
//        MAX_FLING_VELOCITY = configuration.getScaledMaximumFlingVelocity();
//        mTouchSlop = configuration.getScaledTouchSlop();
        setFocusable(true);
        setFocusableInTouchMode(true);

    }

    public void setAnimateView(View view) {
        mAnimateView = view;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mAnimateView.setPivotX(mWidth);
            mAnimateView.setPivotY(0);
        }
    }

    public void setBackView(View view) {
        mBackView = view;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float deltaAngle;
        if (mIsAnimating)
            return true;
        if (vTracker == null) {
            vTracker = VelocityTracker.obtain();
        }
        vTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFirtX = event.getRawX();
                mFirstY = event.getRawY();
                mFirstAngle = calcAngle(mFirtX, mFirstY);
                break;
            case MotionEvent.ACTION_MOVE:
                mCurrentX = event.getRawX();
                mCurrentY = event.getRawY();
                mCurrentAngle = calcAngle(mCurrentX, mCurrentY);
                deltaAngle = mCurrentAngle - mFirstAngle;
                rotateSpring.setEndValue(deltaAngle);
                LogUtil.i("tttt", "deltaAngle = " + deltaAngle + " mCurrentAngle=" + mCurrentAngle + " mFirstAngle=" + mFirstAngle);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mCurrentX = event.getRawX();
                mCurrentY = event.getRawY();
                mCurrentAngle = calcAngle(mCurrentX, mCurrentY);
                deltaAngle = mCurrentAngle - mFirstAngle;
                final VelocityTracker tracker = vTracker;
                tracker.computeCurrentVelocity(UNIT);
                float velocityX = tracker.getXVelocity();
                if (vTracker != null) {
                    vTracker.recycle();
                    vTracker = null;
                }
                if (deltaAngle > TRIGER_DEGREE || velocityX < -MAX_FLING_VELOCITY) {
                    resetSpring();
                    animateLeftOut();

                } else if (deltaAngle < -TRIGER_DEGREE || velocityX > MAX_FLING_VELOCITY) {
                    resetSpring();
                    animateRightOut();
                } else {
                    rotateSpring.setEndValue(0);
                    tranSpring.setEndValue(0);
                }

                break;
        }

        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth();
        mHeight = getHeight();
        getLocationOnScreen(mLocation);
        mPinX = mLocation[0] + mWidth;
        mPinY = mLocation[1];
//        LogUtil.i("tttt", "mPinX = %d  mPinY = %d", mPinX, mPinY);
        if (mAnimateView != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                mAnimateView.setPivotX(mWidth);
                mAnimateView.setPivotY(0);
            }
        }
    }

    private float calcAngle(float x, float y) {
        return (float) (Math.atan((mPinX - x) / (y - mPinY)) * 180 / Math.PI);
    }


    private void resetSpring() {
        rotateSpring.removeAllListeners();
        rotateSpring.setCurrentValue(0);
        rotateSpring.setEndValue(0);
        rotateSpring.addListener(mSpringListener);

        tranSpring.removeAllListeners();
        tranSpring.setCurrentValue(0);
        tranSpring.setEndValue(0);
        tranSpring.addListener(mSpringListener);
    }


    private class ExampleSpringListener extends SimpleSpringListener {
        @Override
        public void onSpringUpdate(Spring spring) {
            float value = (float) spring.getCurrentValue();
            String springId = spring.getId();
            if (springId.equals(rotateSpring.getId())) {
                if (mAnimateView != null) {
                    mAnimateView.setRotation(value);
                }
            } else if (springId.equals(tranSpring.getId())) {
                mAnimateView.setTranslationX(value);
                if (spring.isAtRest()) {
                    resetSpring();
                }
            }
        }
    }

    private void animateLeftOut() {
        mAnimateView.animate().translationX(-800)
                .rotationBy(60)
                .setDuration(500)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        mIsAnimating = true;
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        showNext();
                        mIsAnimating = false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                })
                .start();
    }

    private void animateRightOut() {
        mAnimateView.animate().translationX(400)
                .rotationBy(-60)
                .setDuration(500)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        mIsAnimating = true;
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        showNext();
                        mIsAnimating = false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                })
                .start();
    }


    private void showNext() {
        if (mAnimateView == null || mBackView == null ||
                !(mAnimateView instanceof DailyView) || !(mBackView instanceof DailyView)) {
            return;
        }

        ((DailyView) mAnimateView).showNext();
        mAnimateView.setRotation( 0);
        mAnimateView.setTranslationX(0);
        mAnimateView.setTranslationY(0);

        ((DailyView) mBackView).showNext();

        int maxHeight = Math.max(((DailyView) mAnimateView).getHeightNeeded(), ((DailyView) mBackView).getHeightNeeded());
        ViewGroup.LayoutParams params = mAnimateView.getLayoutParams();
        params.height = maxHeight;
        mAnimateView.setLayoutParams(params);
        mBackView.setLayoutParams(params);
    }


}
