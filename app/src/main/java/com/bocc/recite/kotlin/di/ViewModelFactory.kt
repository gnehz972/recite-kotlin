package com.bocc.recite.kotlin.di

/**
 * Created by gnehz972 on 2019-06-08.
 */
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory
@Inject constructor(private val viewModelMap: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>)
    : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = viewModelMap.getValue(modelClass).get() as T
}