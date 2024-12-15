package com.kocerlabs.paparauiclone.ui.auth

import android.text.Editable
import android.text.TextWatcher

interface CustomOnChangedTextWatcher : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }
}