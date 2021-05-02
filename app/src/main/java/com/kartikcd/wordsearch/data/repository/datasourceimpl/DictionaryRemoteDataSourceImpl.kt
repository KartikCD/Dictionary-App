package com.kartikcd.wordsearch.data.repository.datasourceimpl

import com.kartikcd.wordsearch.data.api.DictionaryApiService
import com.kartikcd.wordsearch.data.model.Dictionary
import com.kartikcd.wordsearch.data.model.DictionaryItem
import com.kartikcd.wordsearch.data.repository.datasource.DictionaryRemoteDataSource
import retrofit2.Response

class DictionaryRemoteDataSourceImpl(
    private val dictionaryApiService: DictionaryApiService
): DictionaryRemoteDataSource {
    override suspend fun getSearchedMeaning(wordSearch: String): Response<Dictionary> {
        return dictionaryApiService.getWordMeaning(wordSearch)
    }
}