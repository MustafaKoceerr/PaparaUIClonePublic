package com.kocerlabs.paparauiclone.data.network

import com.kocerlabs.simplifiedcodingmvvm.data.network.model.LoginRequest
import com.kocerlabs.simplifiedcodingmvvm.data.network.model.LoginResponse
import com.kocerlabs.simplifiedcodingmvvm.data.network.model.LoginResponseSecond
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("user/login")
    suspend fun login( // async olarak coroutine ile çağırmak için suspend yaptım.
        @Body loginRequest: LoginRequest
    ): LoginResponse


    @GET("user/me")
    suspend fun getCurrentUser( // async olarak coroutine ile çağırmak için suspend yaptım.
    ): LoginResponseSecond

}