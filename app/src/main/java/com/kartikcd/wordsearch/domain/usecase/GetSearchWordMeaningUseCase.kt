package com.kartikcd.wordsearch.domain.usecase

import com.kartikcd.wordsearch.data.model.Dictionary
import com.kartikcd.wordsearch.data.model.DictionaryItem
import com.kartikcd.wordsearch.data.util.Resource
import com.kartikcd.wordsearch.domain.repository.DictionaryRepository

class GetSearchWordMeaningUseCase(private val dictionaryRepository: DictionaryRepository) {
    suspend fun execute(wordSearch: String): Resource<Dictionary> {
        return dictionaryRepository.getSearchWordMeaning(wordSearch)
    }
}