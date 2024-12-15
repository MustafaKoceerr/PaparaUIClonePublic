package com.kocerlabs.paparauiclone.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.kocerlabs.paparauiclone.data.database.UserPreferences
import javax.inject.Inject

// Data binding, view model'e ihtiyacımız var.
abstract class BaseFragment<B : ViewBinding?>() : Fragment() {
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

}