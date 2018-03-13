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
abstract class BuilderModule {

    @ActivityScope
    @ContributesAndroidInjector()
    abstract fun contributeMainActivityInjector(): MainActivity

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun contributeHomeInjector(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun contributeBookFragmentInjector(): BookFragment

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun contributeRememberFragmentInjector(): RememberFragment

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun contributeSettingFragmentInjector(): SettingFragment
}