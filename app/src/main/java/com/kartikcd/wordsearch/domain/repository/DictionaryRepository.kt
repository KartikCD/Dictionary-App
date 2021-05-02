package com.kartikcd.wordsearch.domain.repository

import com.kartikcd.wordsearch.data.model.Dictionary
import com.kartikcd.wordsearch.data.model.DictionaryItem
import com.kartikcd.wordsearch.data.util.Resource

interface DictionaryRepository {
    suspend fun getSearchWordMeaning(wordSearch: String): Resource<Dictionary>
}