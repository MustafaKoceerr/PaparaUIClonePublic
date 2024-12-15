package com.kocerlabs.paparauiclone.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): T {
        return withContext(Dispatchers.IO) { //api call'unu IO dispatcher'da gerçekleştirmek istediğim için
            try {
                apiCall.invoke()
            } catch (throwable: Throwable) {
                throw throwable
            }
        }
    }
}