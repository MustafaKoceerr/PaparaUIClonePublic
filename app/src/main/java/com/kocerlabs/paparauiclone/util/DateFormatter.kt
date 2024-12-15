package com.kocerlabs.paparauiclone.util

import android.content.Context
import androidx.core.content.ContextCompat
import com.kocerlabs.paparauiclone.R
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


fun formatTransactionDates(date: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM yyyy HH:mm", Locale("tr")) // Türkçe tarih formatı

    val parsedDate = inputFormat.parse(date)
    return parsedDate?.let { outputFormat.format(it) } ?: "null"
}

fun formatAndParseTransactionDates(date: String): List<String> {
    // Tarih ve saat bilgisini parçalıyoruz
    val dateTime = LocalDateTime.parse(date)

    // Tarihi formatlıyoruz
    val dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("tr"))
    val formattedDate = dateTime.format(dateFormatter)

    // Saat bilgisini alıyoruz
    val hourFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val formattedHour = dateTime.format(hourFormatter)
    return listOf(formattedDate, formattedHour)
}

fun colorHelper(context: Context, isSpending: Boolean): Int {
    return if (isSpending) {
        // true ise @color/money_rounding_donation_icon rengini döndür
        ContextCompat.getColor(context, R.color.money_rounding_donation_icon)
    } else {
        // false ise @color/purchase_green rengini döndür
        ContextCompat.getColor(context, R.color.purchase_green)
    }
}