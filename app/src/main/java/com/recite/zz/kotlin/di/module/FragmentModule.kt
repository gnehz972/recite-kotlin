package com.recite.zz.kotlin.di.module

import com.recite.zz.kotlin.di.scope.FragmentScope
import com.recite.zz.kotlin.main.SentenceViewMode
import com.recite.zz.kotlin.repository.SentenceRepository
import dagger.Module
import dagger.Provides

/**
 * Created by gnehz972 on 18/3/12.
 */
@Module
@FragmentScope
class FragmentModule{


    @Provides
    @FragmentScope
    fun provideSentenceViewMode(sentenceRepository: SentenceRepository) = SentenceViewMode(sentenceRepository)

}