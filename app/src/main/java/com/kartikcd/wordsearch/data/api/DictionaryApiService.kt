package com.kartikcd.wordsearch.data.api

import com.kartikcd.wordsearch.data.model.Dictionary
import com.kartikcd.wordsearch.data.model.DictionaryItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApiService {

    @GET("en_US/{word}")
    suspend fun getWordMeaning(
        @Path("word")
        wordSearch: String
    ): Response<Dictionary>

}