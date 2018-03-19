package com.recite.zz.kotlin.base

import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment

/**
 * Created by gnehz972 on 18/3/11.
 */
open class BaseFragment : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}