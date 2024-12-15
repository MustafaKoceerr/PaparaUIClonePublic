package com.kocerlabs.paparauiclone.ui.home

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.kocerlabs.paparauiclone.util.convertDpToPx

class CustomPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val scaleFactor = 0.90f // ortadaki fragment'ın boyutu
        val offset = 0.08f // Sağdaki ve soldaki fragment'ların gözükmesi için
        val shiftAmountDp = 2f // 2 dp sola kaydırdım, 2 dp sola yatık

        page.apply {
            val shiftAmountPx =
                convertDpToPx(
                    context,
                    shiftAmountDp
                ) // Ortadaki fragment'ı sola kaydırma miktarı px cinsinden
            scaleX = scaleFactor
            scaleY = scaleFactor
            translationX = position * -(offset * width) - shiftAmountPx
        }
    }
}
