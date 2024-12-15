package com.kocerlabs.paparauiclone.ui.auth

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputConnectionWrapper
import androidx.appcompat.widget.AppCompatEditText
import java.util.Random

class WiseEditText : AppCompatEditText {

    private val r = Random()
    private var keyListener: OnKeyListener? = null

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection {
        return MyInputConnection(super.onCreateInputConnection(outAttrs)!!, true)
    }

    private inner class MyInputConnection(target: InputConnection, mutable: Boolean) :
        InputConnectionWrapper(target, mutable) {

        override fun sendKeyEvent(event: KeyEvent): Boolean {
            keyListener?.onKey(this@WiseEditText, event.keyCode, event)
            return super.sendKeyEvent(event)
        }

        override fun deleteSurroundingText(beforeLength: Int, afterLength: Int): Boolean {
            // magic: in latest Android, deleteSurroundingText(1, 0) will be called for backspace
            if (beforeLength == 1 && afterLength == 0) {
                // backspace
                sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
                sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL))
                return true
            }
            return super.deleteSurroundingText(beforeLength, afterLength)
        }
    }

    fun setSoftKeyListener(listener: OnKeyListener?) {
        keyListener = listener
    }
}
