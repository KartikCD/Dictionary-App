package com.kartikcd.wordsearch.presentation.di

import com.kartikcd.wordsearch.BuildConfig
import com.kartikcd.wordsearch.data.api.DictionaryApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideDictionaryApiService(retrofit: Retrofit): DictionaryApiService {
        return retrofit.create(DictionaryApiService::class.java)
    }

}