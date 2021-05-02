package com.kartikcd.wordsearch.presentation.di

import com.kartikcd.wordsearch.data.repository.DictionaryRepositoryImpl
import com.kartikcd.wordsearch.data.repository.datasource.DictionaryRemoteDataSource
import com.kartikcd.wordsearch.domain.repository.DictionaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesDictionaryRepository(
        dictionaryRemoteDataSource: DictionaryRemoteDataSource
    ): DictionaryRepository {
        return DictionaryRepositoryImpl(dictionaryRemoteDataSource)
    }

}