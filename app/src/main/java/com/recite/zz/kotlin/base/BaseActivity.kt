package com.recite.zz.kotlin.base

import android.support.v7.app.AppCompatActivity
import com.recite.zz.kotlin.di.component.ActivityComponent
import com.recite.zz.kotlin.di.module.ActivityModule

/**
 * Created by zouzheng on 18-3-8.
 */
open class BaseActivity : AppCompatActivity() {
    private var activityComponent: ActivityComponent? = null

    fun getComponent(): ActivityComponent {
        if (activityComponent == null) {
            val app = application as BaseApp
            activityComponent = app.compenent.plus(ActivityModule(this))


        }

        return activityComponent!!
    }
}