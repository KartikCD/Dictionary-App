package com.kartikcd.wordsearch.data.repository

import com.kartikcd.wordsearch.data.model.Dictionary
import com.kartikcd.wordsearch.data.model.DictionaryItem
import com.kartikcd.wordsearch.data.repository.datasource.DictionaryRemoteDataSource
import com.kartikcd.wordsearch.data.util.Resource
import com.kartikcd.wordsearch.domain.repository.DictionaryRepository
import retrofit2.Response

class DictionaryRepositoryImpl(
    private val dictionaryRemoteDataSource: DictionaryRemoteDataSource
): DictionaryRepository {
    override suspend fun getSearchWordMeaning(wordSearch: String): Resource<Dictionary> {
        return responseToResource(dictionaryRemoteDataSource.getSearchedMeaning(wordSearch))
    }

    private fun responseToResource(response: Response<Dictionary>):Resource<Dictionary>{
        if(response.code() == 200){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error("No meaning found.")
    }
}