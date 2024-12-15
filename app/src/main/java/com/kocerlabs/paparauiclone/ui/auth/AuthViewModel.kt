package com.kocerlabs.paparauiclone.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kocerlabs.paparauiclone.data.repository.AuthRepository
import com.kocerlabs.simplifiedcodingmvvm.data.network.model.LoginRequest
import com.kocerlabs.simplifiedcodingmvvm.data.network.model.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    // Bunu özellikle suspend yaptım, fragment'ta çağıracağım
    // Normalde asenkron işlemleri genelde viewModel'de yaparız ANCAK
    // token geldikten sonra kaydedip, başka bir fragment'a yönlendirmem gerekiyor.
    // İşlem asenkron olduğu için token kaydedilmeden başka fragment'a yönlendirme şansı da var.
    // bu nedenle bu fonksiyonu suspend yapıp View Katmanından çağıracağım.
    suspend fun saveAuthToken(token: String) = repository.saveAuthToken(token)

    // aynı şekilde okuma işlemini de view katmanında yapacağım.
    suspend fun saveName(name: String) = repository.saveName(name)

    suspend fun clearDataStore() = repository.clearDataStore()


    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _loginResponse.value = repository.login(LoginRequest(email, password))
            } catch (e: Exception) {

            }
        }
    }

}