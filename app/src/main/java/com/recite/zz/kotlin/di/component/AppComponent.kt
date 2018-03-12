package com.recite.zz.kotlin.di.component

import android.app.Application
import com.recite.zz.kotlin.base.BaseApp
import com.recite.zz.kotlin.di.module.ActivityModule
import com.recite.zz.kotlin.di.module.AppModule
import com.recite.zz.kotlin.di.module.BuilderModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by zouzheng on 18-3-8.
 */
@Singleton
@Component(modules = [AppModule::class,
AndroidSupportInjectionModule::class,
BuilderModule::class
])
interface AppComponent : AndroidInjector<BaseApp>{

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BaseApp>()


}