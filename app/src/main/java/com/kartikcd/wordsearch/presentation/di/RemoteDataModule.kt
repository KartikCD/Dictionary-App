package com.kartikcd.wordsearch.presentation.di

import com.kartikcd.wordsearch.data.api.DictionaryApiService
import com.kartikcd.wordsearch.data.repository.datasource.DictionaryRemoteDataSource
import com.kartikcd.wordsearch.data.repository.datasourceimpl.DictionaryRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideDictionaryRemoteDataSource(
        dictionaryApiService: DictionaryApiService
    ): DictionaryRemoteDataSource {
        return DictionaryRemoteDataSourceImpl(dictionaryApiService)
    }
}