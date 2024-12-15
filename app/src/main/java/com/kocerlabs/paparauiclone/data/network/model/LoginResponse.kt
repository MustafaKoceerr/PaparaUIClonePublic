package com.kocerlabs.simplifiedcodingmvvm.data.network.model

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    // login linki için
    val id: Long? = null,
    val username: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val gender: String? = null,
    val image: String? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null,
)

data class  LoginResponseSecond(
    // auth/me linki için
    @SerializedName("id"         ) var id         : Int?    = null,
    @SerializedName("firstName"  ) var firstName  : String? = null,
    @SerializedName("lastName"   ) var lastName   : String? = null,
    @SerializedName("maidenName" ) var maidenName : String? = null,
    @SerializedName("age"        ) var age        : Int?    = null,
    @SerializedName("gender"     ) var gender     : String? = null,
    @SerializedName("email"      ) var email      : String? = null,
    @SerializedName("image"      ) var image      : String? = null
)


