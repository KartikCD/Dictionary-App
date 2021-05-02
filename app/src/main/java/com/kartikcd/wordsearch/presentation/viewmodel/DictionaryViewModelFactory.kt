package com.kartikcd.wordsearch.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kartikcd.wordsearch.domain.usecase.GetSearchWordMeaningUseCase

class DictionaryViewModelFactory(
    private val app: Application,
    private val getSearchWordMeaningUseCase: GetSearchWordMeaningUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DictionaryViewModel(
            app,
            getSearchWordMeaningUseCase
        ) as T
    }
}