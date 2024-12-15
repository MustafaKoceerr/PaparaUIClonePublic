package com.kocerlabs.paparauiclone.data.repository

import com.kocerlabs.paparauiclone.R
import com.kocerlabs.paparauiclone.data.network.AuthApi
import com.kocerlabs.paparauiclone.data.network.HomeApi
import com.kocerlabs.paparauiclone.data.network.model.StoryModel
import com.kocerlabs.paparauiclone.data.network.model.TransactionModel
import com.kocerlabs.simplifiedcodingmvvm.data.network.model.LoginResponseSecond
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: HomeApi,
    private val userApi: AuthApi,
) : SafeApiCall() {

    suspend fun getTransactions(): List<TransactionModel> = safeApiCall { api.getTransactions() }

    fun getStories(): List<StoryModel> {
        return listOf(
            StoryModel(R.drawable.img_wrap_up_papara_cashback, "Cashback"),
            StoryModel(R.drawable.onboarding_illustration_4, "Duyurular"),
            StoryModel(R.drawable.img_wrap_up_invite_friend, "Davet Et,\nKazan"),
            StoryModel(R.drawable.img_wrap_up_international_transfer, "Yurt Dışı Para\nTransferi"),
            StoryModel(R.drawable.img_wrap_up_papara_chat, "Sohbetler"),
            StoryModel(R.drawable.img_wrap_up_papara_international, "Seyahat\nAyrıcalıkları"),
            StoryModel(R.drawable.img_precious_metal_cashback_settings, "Kıymetli\nMadenler"),
            StoryModel(R.drawable.img_wrap_up_cards, "Kartını Tasarla"),
            StoryModel(R.drawable.img_wrap_up_papara_iban, "Aylık Özet"),
        )
    }

    suspend fun getCurrentUser(): LoginResponseSecond = safeApiCall { userApi.getCurrentUser() }

}