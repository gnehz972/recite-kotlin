package com.bocc.recite.kotlin.di.component

import com.bocc.recite.kotlin.base.BaseApp
import com.bocc.recite.kotlin.di.module.AppModule
import com.bocc.recite.kotlin.di.module.ActivityBindModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by zouzheng on 18-3-8.
 */
@Singleton
@Component(modules = [AppModule::class,
    ActivityBindModule::class,
    AndroidSupportInjectionModule::class])

interface AppComponent : AndroidInjector<BaseApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BaseApp>()


}