package com.recite.zz.kotlin.base

import android.app.Application
import com.recite.zz.kotlin.di.component.AppComponent
import com.recite.zz.kotlin.di.component.DaggerAppComponent
import com.recite.zz.kotlin.di.module.AppModule

/**
 * Created by zouzheng on 18-3-8.
 */
class BaseApp : Application() {
    lateinit var compenent: AppComponent
        private set

    companion object {
        private var instance: Application? = null

        fun instance() = instance!!
    }


    override fun onCreate() {
        super.onCreate()

        instance = this
        compenent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        compenent.inject(this)
    }
}