package com.bocc.recite.kotlin.base

import com.bocc.recite.kotlin.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by zouzheng on 18-3-8.
 */
class BaseApp : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}