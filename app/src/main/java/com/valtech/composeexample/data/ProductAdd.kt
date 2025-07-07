package com.valtech.composeexample.data

import android.net.Uri

data class ProductAdd(
    val nama: String,
    val harga: String,
    val stok: String,
    val deskripsi: String,
    val gambarUri: Uri?
)
