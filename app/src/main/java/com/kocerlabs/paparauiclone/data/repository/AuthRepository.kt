package com.kocerlabs.paparauiclone.data.repository

import com.kocerlabs.paparauiclone.data.database.UserPreferences
import com.kocerlabs.paparauiclone.data.network.AuthApi
import com.kocerlabs.simplifiedcodingmvvm.data.network.model.LoginRequest
import com.kocerlabs.simplifiedcodingmvvm.data.network.model.LoginResponse
import com.kocerlabs.simplifiedcodingmvvm.data.network.model.LoginResponseSecond
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val preferences: UserPreferences

) : SafeApiCall() {


    suspend fun login(requestData: LoginRequest): LoginResponse =
        safeApiCall { api.login(requestData) }


    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
        // şimdi view model'den bu fonksiyonu çağırabiliriz.
    }


    suspend fun saveName(name: String) {
        // todo
        // password fragmentta çağır.
        // login saved user fragmentta da oku
        preferences.saveName(name)
    }

    suspend fun clearDataStore() {
        preferences.clear()
    }
}