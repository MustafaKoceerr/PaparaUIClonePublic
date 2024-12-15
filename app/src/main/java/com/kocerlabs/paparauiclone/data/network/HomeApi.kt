package com.kocerlabs.paparauiclone.data.network

import com.kocerlabs.paparauiclone.data.network.model.ChatsModel
import com.kocerlabs.paparauiclone.data.network.model.TransactionModel
import retrofit2.http.GET

interface HomeApi {

    @GET("dummy_data_transaction.json")
    suspend fun getTransactions(): List<TransactionModel>

    @GET("dummy_data_chats.json")
    suspend fun getChats(): List<ChatsModel>
}