package com.recite.zz.kotlin.di.component

import android.app.Application
import com.recite.zz.kotlin.di.module.ActivityModule
import com.recite.zz.kotlin.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by zouzheng on 18-3-8.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: Application)

    fun plus(module: ActivityModule): ActivityComponent


}