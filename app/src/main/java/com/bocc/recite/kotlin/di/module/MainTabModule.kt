package com.bocc.recite.kotlin.di.module

import androidx.lifecycle.ViewModelProvider
import com.bocc.recite.kotlin.di.ViewModelFactory
import com.bocc.recite.kotlin.di.scope.FragmentScope
import com.bocc.recite.kotlin.main.HomeFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by zouzheng on 18-3-15.
 */
@Module(includes = [
    FeatureViewModelModule::class
])
abstract class MainTabModule {

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun contributeHomeInjector(): HomeFragment

    @Binds
    abstract fun bindFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

//    @FragmentScope
//    @ContributesAndroidInjector()
//    abstract fun contributeBookFragmentInjector(): BookFragment
//
//    @FragmentScope
//    @ContributesAndroidInjector()
//    abstract fun contributeRememberFragmentInjector(): RememberFragment
//
//    @FragmentScope
//    @ContributesAndroidInjector()
//    abstract fun contributeSettingFragmentInjector(): SettingFragment
}