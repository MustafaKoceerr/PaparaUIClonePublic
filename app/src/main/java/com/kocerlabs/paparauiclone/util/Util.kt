package com.kocerlabs.paparauiclone.util

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.kocerlabs.paparauiclone.R


fun Fragment.goToOtherFragment(action: NavDirections) {
    findNavController().navigate(action)
}

fun Fragment.goToBack() {
    findNavController().popBackStack()  // Gerideki fragment'a dön
}

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    // snackbar'ın action button'u vardır.
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    action?.let { retry ->
        snackbar.setAction("Retry") {
            retry()
        }
    }
    snackbar.show()
}

fun Fragment.bottomNavigationVisibility(boolean: Boolean) {
    val bottomNav =
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
    if (boolean) bottomNav.visibility = View.VISIBLE else bottomNav.visibility = View.GONE
}


fun convertPxToDp(context: Context, px: Float): Float {
    val density = context.resources.displayMetrics.density
    return px / density
}

fun convertDpToPx(context: Context, dp: Float): Float {
    val density = context.resources.displayMetrics.density
    return dp * density
}