package com.bocc.recite.kotlin.view

import android.animation.Animator
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.widget.FrameLayout
import com.facebook.rebound.SimpleSpringListener
import com.facebook.rebound.Spring
import com.facebook.rebound.SpringSystem
import com.bocc.recite.kotlin.ext.LogUtil

/**
 * Created by gnehz972 on 18/3/19.
 */
class SwipeLayout : FrameLayout {

    private var mHeight: Int = 0
    private var mWidth: Int = 0
    private var mCurrentX: Float = 0.toFloat()
    private var mCurrentY: Float = 0.toFloat()
    private var mFirtX: Float = 0.toFloat()
    private var mFirstY: Float = 0.toFloat()
    private val mLocation = IntArray(2)
    private var mPinX: Int = 0
    private var mPinY: Int = 0
    private var mFirstAngle: Float = 0.toFloat()
    private var mCurrentAngle: Float = 0.toFloat()

    private val mSpringSystem = SpringSystem.create()
    private val mSpringListener = ExampleSpringListener()
    private var rotateSpring: Spring
    private var mScaleSpring: Spring
    private var tranSpring: Spring
    private var mFrontView: View? = null
    private var mBackView: View? = null
    private var vTracker: VelocityTracker? = null
    private var mIsAnimating: Boolean = false
    private var swipeListener:SwipeListener? = null


    constructor(context: Context) : super(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)


    init {
        mScaleSpring = mSpringSystem.createSpring()
        tranSpring = mSpringSystem.createSpring()
        rotateSpring = mSpringSystem.createSpring()
        mScaleSpring.addListener(mSpringListener)
        tranSpring.addListener(mSpringListener)
        rotateSpring.addListener(mSpringListener)
        rotateSpring.addListener(mSpringListener)

        isFocusable = true
        isFocusableInTouchMode = true
    }


    fun setFrontBackView(front: View, back: View) {
        mFrontView = front
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mFrontView!!.pivotX = mWidth.toFloat()
            mFrontView!!.pivotY = 0f
        }

        mBackView = back

        //add back first
        addView(back)
        addView(front)
    }

    fun getFrontView():View?{
        return mFrontView
    }


    fun getBackView():View?{
        return mBackView
    }



    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val deltaAngle: Float
        if (mIsAnimating)
            return true
        if (vTracker == null) {
            vTracker = VelocityTracker.obtain()
        }
        vTracker!!.addMovement(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mFirtX = event.rawX
                mFirstY = event.rawY
                mFirstAngle = calcAngle(mFirtX, mFirstY)
            }
            MotionEvent.ACTION_MOVE -> {
                mCurrentX = event.rawX
                mCurrentY = event.rawY
                mCurrentAngle = calcAngle(mCurrentX, mCurrentY)
                deltaAngle = mCurrentAngle - mFirstAngle
                rotateSpring!!.endValue = deltaAngle.toDouble()
                LogUtil.d("tttt", "deltaAngle = $deltaAngle mCurrentAngle=$mCurrentAngle mFirstAngle=$mFirstAngle")
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                mCurrentX = event.rawX
                mCurrentY = event.rawY
                mCurrentAngle = calcAngle(mCurrentX, mCurrentY)
                deltaAngle = mCurrentAngle - mFirstAngle
                val tracker = vTracker
                tracker!!.computeCurrentVelocity(SwipeLayout.UNIT)
                val velocityX = tracker.xVelocity
                if (vTracker != null) {
                    vTracker!!.recycle()
                    vTracker = null
                }
                if (deltaAngle > SwipeLayout.TRIGER_DEGREE || velocityX < -SwipeLayout.MAX_FLING_VELOCITY) {
                    resetSpring()
                    animateLeftOut()

                } else if (deltaAngle < -SwipeLayout.TRIGER_DEGREE || velocityX > SwipeLayout.MAX_FLING_VELOCITY) {
                    resetSpring()
                    animateRightOut()
                } else {
                    rotateSpring.endValue = 0.0
                    tranSpring.endValue = 0.0
                }
            }
        }

        return true
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mWidth = width
        mHeight = height
        getLocationOnScreen(mLocation)
        mPinX = mLocation[0] + mWidth
        mPinY = mLocation[1]
        //        LogUtil.i("tttt", "mPinX = %d  mPinY = %d", mPinX, mPinY);
        if (mFrontView != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                mFrontView?.pivotX = mWidth.toFloat()
                mFrontView?.pivotY = 0f
            }
        }
    }


    private fun calcAngle(x: Float, y: Float): Float {
        return (Math.atan(((mPinX - x) / (y - mPinY)).toDouble()) * 180 / Math.PI).toFloat()
    }


    private inner class ExampleSpringListener : SimpleSpringListener() {
        override fun onSpringUpdate(spring: Spring) {
            val value = spring.currentValue.toFloat()
            val springId = spring.id
            if (springId == rotateSpring.id) {
                if (mFrontView != null) {
                    mFrontView?.rotation = value
                }
            } else if (springId == tranSpring.id) {
                mFrontView?.translationX = value
                if (spring.isAtRest) {
                    resetSpring()
                }
            }
        }
    }


    private fun resetSpring() {
        rotateSpring.removeAllListeners()
        rotateSpring.currentValue = 0.0
        rotateSpring.endValue = 0.0
        rotateSpring.addListener(mSpringListener)

        tranSpring.removeAllListeners()
        tranSpring.currentValue = 0.0
        tranSpring.endValue = 0.0
        tranSpring.addListener(mSpringListener)
    }


    private fun animateLeftOut() {
        mFrontView?.apply {
            animate().translationX(-800f)
                    .rotationBy(60f)
                    .setDuration(500)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animator: Animator) {
                            mIsAnimating = true
                        }

                        override fun onAnimationEnd(animator: Animator) {
                            showNext()
                            mIsAnimating = false
                        }

                        override fun onAnimationCancel(animator: Animator) {

                        }

                        override fun onAnimationRepeat(animator: Animator) {

                        }
                    })
                    .start()
        }
    }

    private fun animateRightOut() {
        mFrontView?.apply {
            animate().translationX(400f)
                    .rotationBy(-60f)
                    .setDuration(500)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animator: Animator) {
                            mIsAnimating = true
                        }

                        override fun onAnimationEnd(animator: Animator) {
                            showNext()
                            mIsAnimating = false
                        }

                        override fun onAnimationCancel(animator: Animator) {

                        }

                        override fun onAnimationRepeat(animator: Animator) {

                        }
                    })
                    .start()
        }
    }


    private fun showNext() {
        if (mFrontView == null || mBackView == null ||
                mFrontView !is DailyView || mBackView !is DailyView) {
            return
        }

       swipeListener?.onSwipeEnd(mFrontView!!,mBackView!!)

        mFrontView?.rotation = 0f
        mFrontView?.translationX = 0f
        mFrontView?.translationY = 0f

    }


    fun setSwipeListener(listener: SwipeListener){
        swipeListener = listener
    }


    interface SwipeListener {
        fun onSwipeEnd(front: View,back: View)
    }

    companion object {
        private val TRIGER_DEGREE = 20
        private val MAX_FLING_VELOCITY = 2000f
        private val UNIT = 1000 // 计算速率的单位（毫秒）
    }


}