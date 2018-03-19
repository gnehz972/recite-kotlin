package com.recite.zz.kotlin.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by zouzheng on 18-3-8.
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}