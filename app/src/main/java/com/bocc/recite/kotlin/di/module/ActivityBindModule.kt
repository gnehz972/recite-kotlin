package com.bocc.recite.kotlin.di.module

import dagger.Module

/**
 * Created by gnehz972 on 18/3/12.
 */
@Module(includes = [MainTabModule::class])
abstract class ActivityBindModule {


//    @ActivityScope
//    @ContributesAndroidInjector(modules = [MainTabModule::class])
//    abstract fun contributeMainActivityInjector(): MainActivity

}