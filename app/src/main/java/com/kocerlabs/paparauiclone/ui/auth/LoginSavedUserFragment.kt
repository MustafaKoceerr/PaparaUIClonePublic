package com.kocerlabs.paparauiclone.ui.auth

import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnKeyListener
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.kocerlabs.paparauiclone.R
import com.kocerlabs.paparauiclone.data.database.UserPreferences
import com.kocerlabs.paparauiclone.databinding.FragmentLoginSavedUserBinding
import com.kocerlabs.paparauiclone.ui.base.BaseFragment
import com.kocerlabs.paparauiclone.util.goToOtherFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginSavedUserFragment : BaseFragment<FragmentLoginSavedUserBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginSavedUserBinding =
        FragmentLoginSavedUserBinding.inflate(inflater, container, false)

    private val viewModel: AuthViewModel by viewModels()

    @Inject
    lateinit var userPreferences: UserPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            // burada lifecycle scope kullandım, çünkü bu değeri bekleyen başka bir fonksiyonu aşağıda çağırmıyorum.
            // eğer bu değeri bekleyen başka bir fonksiyonu aşağıda çağıracaksam runBlocking kullanacaktım.
            binding!!.txtName.text = userPreferences.fullName.first()
        }

        provideEditTextFunctionality()
        initSetOnClickListeners()
        observeLoginGoHome()
    }

    private fun observeLoginGoHome() {
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer { response ->
            binding!!.progressBar.visibility = View.GONE
            lifecycleScope.launch {
                viewModel.saveAuthToken(response.accessToken ?: "")
                viewModel.saveName("${response.firstName ?: ""} ${response.lastName ?: ""}")

                findNavController().navigate(
                    R.id.nav_account, null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.viewPagerFragment, true) // loginFragment'ı da temizliyoruz
                        .build()
                )
            }
        })
    }


    private fun initSetOnClickListeners() {
        with(binding!!) {
            txtForgetMe.setOnClickListener {
                lifecycleScope.launch {
                    viewModel.clearDataStore()
                    goToOtherFragment(LoginSavedUserFragmentDirections.loginSavedUserFragmentToViewPagerFragment())
                }
            }
        }
    }


    private fun openFocusAndFocusEdtText(edttext: EditText) {
        with(binding!!) {
            edttext.focusable = View.FOCUSABLE
            edttext.isFocusableInTouchMode = true
            edttext.requestFocus()
        }
    }


    private fun checkPassword(password: String) {
        // todo gerçek uygulamada view model ile doğrulama atılacak
        with(binding!!) {
            if (txtName.text.equals("Emily Johnson")) {
                // emily.johnson@x.dummyjson.com
                if (password.equals("111111")) {
                    progressBar.visibility = View.VISIBLE
                    viewModel.login("emilys", "emilyspass")
                } else {
                    clearEditTexts()
                    goToOtherFragment(LoginSavedUserFragmentDirections.loginSavedUserFragmentToWrongPasswordFragment())
                }
            } else if (txtName.text.equals("Michael Williams")) {
                // michael.williams@x.dummyjson.com
                if (password.equals("222222")) {
                    progressBar.visibility = View.VISIBLE
                    viewModel.login("michaelw", "michaelwpass")
                } else {
                    clearEditTexts()
                    goToOtherFragment(LoginSavedUserFragmentDirections.loginSavedUserFragmentToWrongPasswordFragment())
                }
            } else {
                clearEditTexts()
                goToOtherFragment(LoginSavedUserFragmentDirections.loginSavedUserFragmentToWrongPasswordFragment())
            }
        }
    }


    private fun clearEditTexts() {
        with(binding!!) {
            edtPass1.setText("")
            edtPass2.setText("")
            edtPass3.setText("")
            edtPass4.setText("")
            edtPass5.setText("")
            edtPass6.setText("")
            edtPass1.requestFocus()
        }
    }


    private fun getTexts(): String =
        with(binding!!) {
            return "${edtPass1.text}${edtPass2.text}${edtPass3.text}${edtPass4.text}${edtPass5.text}"
        }


    private fun provideEditTextFunctionality() {
        with(binding!!) {

            edtPass1.addTextChangedListener(object : CustomBeforeTextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    if (after == 1 && s.isNullOrBlank()) {
                        openFocusAndFocusEdtText(edtPass2)
                        edtPass1.focusable = View.NOT_FOCUSABLE
                    }
                }
            })


            edtPass2.addTextChangedListener(object : CustomBeforeTextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    if (after == 1 && s.isNullOrBlank()) {
                        openFocusAndFocusEdtText(edtPass3)
                        edtPass2.focusable = View.NOT_FOCUSABLE
                    }
                }
            })

            edtPass2.setSoftKeyListener(object : OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                    // doluyken silme işlemi yaparken:
                    // önce burası, sonra alt kısımdaki if'lerin içini, sonra burasını tekrar,en sonda beforeTextChanged'ı çalıştırıyor.

                    // boşken silme işlemi yaparken:
                    // burası, aşağıdaki kod, sonra tekrar burası, beforeTextChanged çalışmıyor

                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        // NOT: Burayı 1 kere çalıştırıyor.
                        if (event?.action == KeyEvent.ACTION_DOWN) {
                            if (edtPass2.text.toString().isNullOrBlank()) {
                                // focusu 1'e ata ve 1'in içindeki text'i sil.
                                edtPass1.setText("")
                                openFocusAndFocusEdtText(edtPass1)
                            }
                            return true
                        }
                    }
                    // Diğer tuşlar için super.onKey() çağrısı yapılır
                    return false
                }
            })


            edtPass3.addTextChangedListener(object : CustomBeforeTextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    if (after == 1 && s.isNullOrBlank()) {
                        openFocusAndFocusEdtText(edtPass4)
                        edtPass3.focusable = View.NOT_FOCUSABLE
                    }
                }
            })

            edtPass3.setSoftKeyListener(object : OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {

                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        if (event?.action == KeyEvent.ACTION_DOWN) {
                            if (edtPass3.text.toString().isNullOrBlank()) {
                                edtPass2.setText("")
                                openFocusAndFocusEdtText(edtPass2)
                            }
                            return true
                        }
                    }
                    // Diğer tuşlar için super.onKey() çağrısı yapılır
                    return false
                }
            })


            edtPass4.addTextChangedListener(object : CustomBeforeTextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    if (after == 1 && s.isNullOrBlank()) {
                        openFocusAndFocusEdtText(edtPass5)
                        edtPass4.focusable = View.NOT_FOCUSABLE
                    }
                }
            })

            edtPass4.setSoftKeyListener(object : OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {

                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        if (event?.action == KeyEvent.ACTION_DOWN) {
                            if (edtPass4.text.toString().isNullOrBlank()) {
                                edtPass3.setText("")
                                openFocusAndFocusEdtText(edtPass3)
                            }
                            return true
                        }
                    }
                    // Diğer tuşlar için super.onKey() çağrısı yapılır
                    return false
                }
            })


            edtPass5.addTextChangedListener(object : CustomBeforeTextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    if (after == 1 && s.isNullOrBlank()) {
                        openFocusAndFocusEdtText(edtPass6)
                        edtPass5.focusable = View.NOT_FOCUSABLE
                    }
                }
            })

            edtPass5.setSoftKeyListener(object : OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {

                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        if (event?.action == KeyEvent.ACTION_DOWN) {
                            if (edtPass5.text.toString().isNullOrBlank()) {
                                edtPass4.setText("")
                                openFocusAndFocusEdtText(edtPass4)
                            }
                            return true
                        }
                    }
                    // Diğer tuşlar için super.onKey() çağrısı yapılır
                    return false
                }
            })

            edtPass6.addTextChangedListener(object : CustomBeforeTextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if (!s.isNullOrBlank()) {
                        // todo view model'e istek at ve doğrulama yap
                        checkPassword(getTexts() + s)
                    }
                }
            })

            edtPass6.setSoftKeyListener(object : OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {

                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        if (event?.action == KeyEvent.ACTION_DOWN) {
                            if (edtPass6.text.toString().isNullOrBlank()) {
                                edtPass5.setText("")
                                openFocusAndFocusEdtText(edtPass5)
                            }
                            return true
                        }
                    }
                    // Diğer tuşlar için super.onKey() çağrısı yapılır
                    return false
                }
            })
        }
    }


}