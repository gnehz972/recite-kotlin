package com.recite.zz.kotlin.di.module

import com.recite.zz.kotlin.di.scope.ActivityScope
import com.recite.zz.kotlin.di.scope.FragmentScope
import com.recite.zz.kotlin.main.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by gnehz972 on 18/3/12.
 */
@Module
abstract class ActivityBindModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainTabModule::class])
    abstract fun contributeMainActivityInjector(): MainActivity

}