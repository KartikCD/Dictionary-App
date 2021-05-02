package com.kartikcd.wordsearch.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kartikcd.wordsearch.data.model.Dictionary
import com.kartikcd.wordsearch.data.model.DictionaryItem
import com.kartikcd.wordsearch.data.util.Resource
import com.kartikcd.wordsearch.domain.usecase.GetSearchWordMeaningUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class DictionaryViewModel(
    private val app: Application,
    private val getSearchWordMeaningUseCase: GetSearchWordMeaningUseCase
): AndroidViewModel(app) {
    val dictionaryMeaning: MutableLiveData<Resource<Dictionary>> = MutableLiveData()

    //Get search word meaning
    fun getSearchedWordMeaning(searchQuery: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dictionaryMeaning.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)) {
                    val apiResult = getSearchWordMeaningUseCase.execute(searchQuery)

                    dictionaryMeaning.postValue(apiResult)
                } else {
                    dictionaryMeaning.postValue(Resource.Error("Internet is not available"))
                }
            } catch (e: Exception) {
                dictionaryMeaning.postValue(Resource.Error(e.message.toString()))
            }
        }
    }

    private fun isNetworkAvailable(context: Context?):Boolean{
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }
}