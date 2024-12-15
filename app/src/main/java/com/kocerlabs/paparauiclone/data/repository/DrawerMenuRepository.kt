package com.kocerlabs.paparauiclone.data.repository

import com.kocerlabs.paparauiclone.R
import com.kocerlabs.paparauiclone.data.network.model.MenuModel
import javax.inject.Inject

class DrawerMenuRepository @Inject constructor(
) {


    fun getLeftMenu(): List<MenuModel> {
        return listOf(
            MenuModel(R.drawable.ic_notification_center, "Bildirimler"),
            MenuModel(R.drawable.ic_location_marker, "ATM Bul"),
            MenuModel(R.drawable.left_menu_deposit_withdraw, "Para Çek / Yatır"),
            MenuModel(R.drawable.left_menu_payment_split, "Harcama Bölüştür"),
            MenuModel(R.drawable.left_menu_edu, "Papara Edu"),
            MenuModel(R.drawable.left_menu_cashback, "Cashback"),
            MenuModel(R.drawable.ic_streamer_drawer_icon, "Yayıncı Hesabı"),
            MenuModel(R.drawable.ic_monthly_summary, "Aylık Özet"),
            MenuModel(R.drawable.left_menu_fee_limit, "Ücretler & Limitler"),
            MenuModel(R.drawable.ic_commercial, "Ticari hesap"),
            MenuModel(R.drawable.ic_sss, "Sıkça Sorulan Sorular"),
            MenuModel(R.drawable.left_menu_contact, "Destek Merkezi"),
        )
    }

    fun getQRMenu(): List<MenuModel> {
        return listOf(
            MenuModel(R.drawable.ic_pay_with_qr, "QR ile Ödeme Yap"),
            MenuModel(R.drawable.ic_get_payment_with_qr, "QR ile Ödeme Al"),
            MenuModel(R.drawable.ic_deposit_by_qr, "QR ile ATM'den Para Çek"),
        )
    }

    fun getMoneyTransferMenu(): List<MenuModel> {
        return listOf(
            MenuModel(R.drawable.ic_send_money, "Para Gönder"),
            MenuModel(R.drawable.ic_request_money, "Para İste"),
            MenuModel(R.drawable.ic_int_money_transfer, "Yurt Dışı Para Transferi"),
            MenuModel(R.drawable.ic_send_money_with_secure_pay, "Güvenli Ödeme İşlemi"),
            MenuModel(R.drawable.ic_chat_menu_repeat, "Düzenli Transfer"),
        )
    }

    fun getAccTypeMenu(): List<String> = listOf("Hepsi", "Türk Lirası", "Dolar")

}