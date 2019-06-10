package com.bocc.recite.kotlin.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bocc.recite.kotlin.di.ViewModelFactory
import com.bocc.recite.kotlin.di.ViewModelKey
import com.bocc.recite.kotlin.main.viewmodel.SentenceViewMode
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by zouzheng on 19-6-10.
 */
@Module
abstract class ViewModeModule{

    @Binds
    @IntoMap
    @ViewModelKey(SentenceViewMode::class)
    abstract fun bindSentenceViewModel(viewModel : SentenceViewMode) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}