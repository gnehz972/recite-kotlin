package com.recite.zz.kotlin.base

import android.os.Bundle
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment

/**
 * Created by gnehz972 on 18/3/11.
 */
open class BaseFragment : DaggerFragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}