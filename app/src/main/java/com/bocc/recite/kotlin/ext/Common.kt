package com.bocc.recite.kotlin.ext

import android.content.res.Resources
import android.widget.ImageView
import com.bocc.recite.kotlin.config.GlideApp

/**
 * Created by zouzheng on 18-3-16.
 */
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun ImageView.loadUrl(url : String) {
    GlideApp.with(this.context).load(url).into(this)

}


val  Resources.screenWidth : Int
    get() = Resources.getSystem().displayMetrics.widthPixels


val  Resources.screenHeight : Int
    get() = Resources.getSystem().displayMetrics.heightPixels
