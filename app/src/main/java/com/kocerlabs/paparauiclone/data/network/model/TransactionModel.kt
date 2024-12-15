package com.kocerlabs.paparauiclone.data.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionModel(
    var drawableUrl: String,
    var title: String,
    var description: String,
    var price: Double,
    var transactionDate: String,
    var isSpending: Boolean,
    var extraDesc: String,
) : Parcelable
