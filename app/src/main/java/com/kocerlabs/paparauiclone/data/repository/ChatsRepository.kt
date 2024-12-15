package com.kocerlabs.paparauiclone.data.repository

import com.kocerlabs.paparauiclone.data.network.HomeApi
import com.kocerlabs.paparauiclone.data.network.model.ChatsModel
import javax.inject.Inject

class ChatsRepository @Inject constructor(
    private val api: HomeApi
) : SafeApiCall() {


    suspend fun getChats(): List<ChatsModel> = safeApiCall { api.getChats() }


}