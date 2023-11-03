package com.devtides.androidcoroutinesretrofit.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountriesService {

    private const val BASE_URL = "https://raw.githubusercontent.com"

    fun getCountriesService(): CoroutinesAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoroutinesAPI::class.java)
    }

}