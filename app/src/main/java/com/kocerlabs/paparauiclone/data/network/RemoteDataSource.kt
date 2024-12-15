package com.kocerlabs.paparauiclone.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {
    companion object {
        const val BASE_URL_DUMMY = "https://dummyjson.com/"
        const val BASE_URL_GIT =
            "https://raw.githubusercontent.com/MustafaKoceerr/PaparaUIClone/refs/heads/main/DummyData/"

    }

    fun <Api> buildApi(
        api: Class<Api>,
        url: String
    ): Api { // retrofit nesnesini yaratacak olan generic fonksiyon
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

    fun <Api> buildApiWithHeader(
        api: Class<Api>,
        url: String,
        client: OkHttpClient
    ): Api { // retrofit nesnesini yaratacak olan generic fonksiyon
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}