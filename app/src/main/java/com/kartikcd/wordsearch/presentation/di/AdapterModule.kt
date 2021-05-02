package com.kartikcd.wordsearch.presentation.di

import com.kartikcd.wordsearch.presentation.adapter.DictionaryAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun provideDictionaryAdapter(): DictionaryAdapter {
        return DictionaryAdapter()
    }
}