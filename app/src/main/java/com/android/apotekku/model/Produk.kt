package com.android.apotekku.model

import java.io.Serializable


class Produk: Serializable {
    lateinit var nama : String
    lateinit var harga : String
    var gambar : Int = 0

}