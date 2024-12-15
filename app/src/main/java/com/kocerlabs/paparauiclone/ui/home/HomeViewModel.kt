package com.kocerlabs.paparauiclone.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kocerlabs.paparauiclone.data.network.model.StoryModel
import com.kocerlabs.paparauiclone.data.network.model.TransactionModel
import com.kocerlabs.paparauiclone.data.repository.HomeRepository
import com.kocerlabs.paparauiclone.ui.home.viewpagerfragment.HomeConstants.FIRST_ELEMENT
import com.kocerlabs.paparauiclone.ui.home.viewpagerfragment.HomeConstants.SECOND_ELEMENT
import com.kocerlabs.simplifiedcodingmvvm.data.network.model.LoginResponseSecond
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: HomeRepository
) : ViewModel() {
    val TAG = "HomeViewModel"
    private val _transactions: MutableLiveData<List<TransactionModel>> = MutableLiveData()
    val transactions: LiveData<List<TransactionModel>>
        get() = _transactions

    private val _stories: MutableLiveData<List<StoryModel>> = MutableLiveData()
    val stories: LiveData<List<StoryModel>>
        get() = _stories

    private val _currentUser: MutableLiveData<LoginResponseSecond> = MutableLiveData()
    val currentUser: LiveData<LoginResponseSecond>
        get() = _currentUser


    fun getLastTwoTransactions() {
        viewModelScope.launch {
            try {
                val result = repository.getTransactions()
                val homeScreenMaterials = listOf(result[FIRST_ELEMENT], result[SECOND_ELEMENT])
                _transactions.value = homeScreenMaterials
            } catch (e: Exception) {
                Log.d(TAG, "Cant get data: ${e.message}")
            }
        }
    }

    fun getAllTransactions() {
        viewModelScope.launch {
            try {
                _transactions.value = repository.getTransactions()
            } catch (e: Exception) {
                Log.d(TAG, "Cant get data: ${e.message}")
            }
        }
    }

    fun getStories() {
        _stories.value = repository.getStories()
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            try {
                _currentUser.value = repository.getCurrentUser()
                Log.d(TAG, "all Data fetch: ${_currentUser.value}")
            } catch (e: Exception) {
                Log.d(TAG, "Cant get data: ${e.message}")
            }
        }
    }


}