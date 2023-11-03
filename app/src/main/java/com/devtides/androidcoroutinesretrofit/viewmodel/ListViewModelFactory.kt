package com.devtides.androidcoroutinesretrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devtides.androidcoroutinesretrofit.model.CountriesService

class ListViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(CountriesService.getCountriesService()) as T
    }
}