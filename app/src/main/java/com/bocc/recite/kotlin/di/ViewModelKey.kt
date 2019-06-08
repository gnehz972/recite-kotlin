package com.bocc.recite.kotlin.di

/**
 * Created by gnehz972 on 2019-06-08.
 */
import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ViewModelKey(
        val value: KClass<out ViewModel>
)