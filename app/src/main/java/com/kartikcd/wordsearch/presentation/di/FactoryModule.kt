package com.kartikcd.wordsearch.presentation.di

import android.app.Application
import com.kartikcd.wordsearch.domain.usecase.GetSearchWordMeaningUseCase
import com.kartikcd.wordsearch.presentation.viewmodel.DictionaryViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun providesDictionaryViewModelFactory(
        application: Application,
        getSearchWordMeaningUseCase: GetSearchWordMeaningUseCase
    ): DictionaryViewModelFactory {
        return DictionaryViewModelFactory(
            application,
            getSearchWordMeaningUseCase
        )
    }
}