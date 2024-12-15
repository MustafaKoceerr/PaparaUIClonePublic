package com.kocerlabs.paparauiclone.ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kocerlabs.paparauiclone.R

// Data binding, view model'e ihtiyacımız var.
abstract class BottomBaseFragment<B : ViewBinding?>() : BottomSheetDialogFragment() {
// Buradaki abstract fonksiyonları kullanarak, gerçek fragment'a geçince ihtiyacımız olan class'ları verecekler.


    protected var binding: B? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)


        return binding?.root
    }


    abstract fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): B

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog =
            BottomSheetDialog(requireContext(), R.style.MyTransparentBottomSheetDialogTheme)
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                val behaviour = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        // Arka plana tıklanabilirlik ekle
        dialog.window?.setBackgroundDrawableResource(R.drawable.transparent_background)
        dialog.setCancelable(true) // Dışarıya tıklayınca kapanmasını sağlar

        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    protected open fun initSetOnClickers(rootView: View) {
        // Ortak click listener işlemleri
        rootView.findViewById<View>(R.id.bottomSheetMain)?.setOnClickListener {
            dismiss() // Tıklayınca BottomSheet'i kapatır
        }

        rootView.findViewById<Button>(R.id.btnCancel)?.setOnClickListener {
            dismiss() // Tıklayınca BottomSheet'i kapatır
        }
    }
}