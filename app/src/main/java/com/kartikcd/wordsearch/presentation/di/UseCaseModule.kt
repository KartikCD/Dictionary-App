package com.kartikcd.wordsearch.presentation.di

import com.kartikcd.wordsearch.domain.repository.DictionaryRepository
import com.kartikcd.wordsearch.domain.usecase.GetSearchWordMeaningUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGetSearchWordMeaningUseCase(
        dictionaryRepository: DictionaryRepository
    ): GetSearchWordMeaningUseCase {
        return GetSearchWordMeaningUseCase(dictionaryRepository)
    }
}