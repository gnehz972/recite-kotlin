package com.bocc.recite.kotlin.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by zouzheng on 18-3-8.
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}