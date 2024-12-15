package com.kocerlabs.paparauiclone.ui.auth

import android.text.Editable
import android.text.TextWatcher

interface CustomBeforeTextWatcher: TextWatcher {

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }
}