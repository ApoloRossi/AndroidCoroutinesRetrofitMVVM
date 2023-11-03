package com.devtides.androidcoroutinesretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devtides.androidcoroutinesretrofit.model.CoroutinesAPI
import com.devtides.androidcoroutinesretrofit.model.CountriesService
import com.devtides.androidcoroutinesretrofit.model.Country
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import kotlin.coroutines.coroutineContext

class ListViewModel(private val service: CoroutinesAPI) : ViewModel() {

    var job: Job? = null

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = service.getCountries()
            if (response.isSuccessful) {
                countries.postValue(response.body())
                countryLoadError.postValue("")
                loading.postValue(false)
            } else {
                onError("Error : ${response.message()} ")
            }

        }
    }

    private fun onError(message: String) {
        countryLoadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}