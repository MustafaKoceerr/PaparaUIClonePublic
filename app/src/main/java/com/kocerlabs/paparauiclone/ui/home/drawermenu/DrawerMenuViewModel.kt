package com.kocerlabs.paparauiclone.ui.home.drawermenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kocerlabs.paparauiclone.data.network.model.MenuModel
import com.kocerlabs.paparauiclone.data.repository.DrawerMenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrawerMenuViewModel @Inject constructor(
    private val repository: DrawerMenuRepository
) : ViewModel() {

    private val _leftMenu: MutableLiveData<List<MenuModel>> = MutableLiveData()
    val leftMenu: LiveData<List<MenuModel>>
        get() = _leftMenu

    private val _qrMenu: MutableLiveData<List<MenuModel>> = MutableLiveData()
    val qrMenu: LiveData<List<MenuModel>>
        get() = _qrMenu

    private val _moneyTransferMenu: MutableLiveData<List<MenuModel>> = MutableLiveData()
    val moneyTransferMenu: LiveData<List<MenuModel>>
        get() = _moneyTransferMenu

    private val _accTypeMenu: MutableLiveData<List<String>> = MutableLiveData()
    val accTypeMenu: LiveData<List<String>>
        get() = _accTypeMenu


    fun getLeftMenu() {
        _leftMenu.value = repository.getLeftMenu()
    }

    fun getQRMenu() {
        _qrMenu.value = repository.getQRMenu()
    }

    fun getMoneyTransferMenu() {
        _moneyTransferMenu.value = repository.getMoneyTransferMenu()
    }

    fun getAccTypeMenu() {
        _accTypeMenu.value = repository.getAccTypeMenu()
    }

}