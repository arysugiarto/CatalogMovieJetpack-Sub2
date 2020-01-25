package com.arysugiarto.catalogmoviejetpack.helper

import com.arysugiarto.catalogmoviejetpack.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestApi {
    fun create(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
        return retrofit.create(ApiInterface::class.java)
    }
}