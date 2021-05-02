package com.kartikcd.wordsearch.data.repository.datasource

import com.kartikcd.wordsearch.data.model.Dictionary
import com.kartikcd.wordsearch.data.model.DictionaryItem
import retrofit2.Response

interface DictionaryRemoteDataSource {
    suspend fun getSearchedMeaning(wordSearch: String): Response<Dictionary>
}