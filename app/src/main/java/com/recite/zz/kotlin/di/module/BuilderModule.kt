package com.recite.zz.kotlin.di.module

import com.recite.zz.kotlin.base.BaseFragment
import com.recite.zz.kotlin.di.scope.ActivityScope
import com.recite.zz.kotlin.di.scope.FragmentScope
import com.recite.zz.kotlin.main.HomeFragment
import com.recite.zz.kotlin.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by gnehz972 on 18/3/12.
 */
@Module
abstract class BuilderModule{

    @ActivityScope
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun contributeMainActivityInjector() : MainActivity



    @FragmentScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeHomeInjector() : HomeFragment

//    @FragmentScope
//    @ContributesAndroidInjector(modules = [FragmentModule::class])
//    abstract fun contributeBaseFragmentInjector() : BaseFragment
}