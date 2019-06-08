package com.bocc.recite.kotlin.di.module

import androidx.lifecycle.ViewModel
import com.bocc.recite.kotlin.di.ViewModelKey
import com.bocc.recite.kotlin.main.viewmodel.SentenceViewMode
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by gnehz972 on 2019-06-07.
 */
@Module
abstract class FeatureViewModelModule {


    @IntoMap
    @ViewModelKey(SentenceViewMode::class)
    @Binds
    abstract fun bindViewModel(vm: SentenceViewMode): ViewModel

    // Add other feature sub view models

}