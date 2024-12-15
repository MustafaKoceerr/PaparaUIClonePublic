package com.kocerlabs.paparauiclone.ui.home.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kocerlabs.paparauiclone.data.network.model.ChatsModel
import com.kocerlabs.paparauiclone.data.repository.ChatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val repository: ChatsRepository
) : ViewModel() {
    private val TAG = "ChatsViewModel"

    private val _chats: MutableLiveData<List<ChatsModel>> = MutableLiveData()
    val chats: LiveData<List<ChatsModel>>
        get() = _chats


    fun getChats() {
        viewModelScope.launch {
            try {
                _chats.value = repository.getChats()
                Log.d(TAG, "Chats view model")
            } catch (e: Exception) {

            }
        }
    }


}