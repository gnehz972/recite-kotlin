package com.recite.zz.kotlin.di.component

import com.recite.zz.kotlin.di.module.ActivityModule
import com.recite.zz.kotlin.di.scope.ActivityScope
import com.recite.zz.kotlin.main.MainActivity
import dagger.Subcomponent

/**
 * Created by zouzheng on 18-3-8.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(activity: MainActivity)
}