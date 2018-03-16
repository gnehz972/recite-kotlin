package com.recite.zz.kotlin.ext

import android.content.res.Resources

/**
 * Created by zouzheng on 18-3-16.
 */
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

