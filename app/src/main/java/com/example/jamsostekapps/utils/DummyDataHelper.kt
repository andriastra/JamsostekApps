package com.example.jamsostekapps.utils

import com.example.jamsostekapps.R
import com.example.jamsostekapps.domain.modul.Service

object DummyDataHelper {
    fun getListService(): MutableList<Service> {
        val masterList = Service(
            title = "Pembayaran Iuran",
            img = R.drawable.ic_payment
        )
        return mutableListOf(
            masterList,
            masterList.copy(title = "Promo", img = R.drawable.ic_discount),
            masterList.copy(title = "Pengkinian Data", img = R.drawable.ic_edit_note),
            masterList.copy(title = "Mitra", img = R.drawable.ic_mitra),
            masterList.copy(title = "Info Program", img = R.drawable.ic_menu_book),
            masterList.copy(title = "Pelaporan", img = R.drawable.ic_edit_note),
            masterList.copy(title = "Kantor Cabang", img = R.drawable.ic_company),
            masterList.copy(title = "Pengaduan", img = R.drawable.ic_complaint),
            masterList.copy(title = "Bantuan", img = R.drawable.ic_help_center),
        )
    }
}