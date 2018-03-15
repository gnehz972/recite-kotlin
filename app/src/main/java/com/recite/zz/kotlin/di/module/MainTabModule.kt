package com.recite.zz.kotlin.di.module

import com.recite.zz.kotlin.di.scope.FragmentScope
import com.recite.zz.kotlin.main.BookFragment
import com.recite.zz.kotlin.main.HomeFragment
import com.recite.zz.kotlin.main.RememberFragment
import com.recite.zz.kotlin.main.SettingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by zouzheng on 18-3-15.
 */
@Module
abstract class MainTabModule {

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