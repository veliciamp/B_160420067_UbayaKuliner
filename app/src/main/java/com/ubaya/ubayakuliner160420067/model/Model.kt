package com.ubaya.ubayakuliner160420067.model

import com.google.gson.annotations.SerializedName

data class Kuliner(
    val id:String?,
    val name:String?,
    val category:String?,
    val phone:String?,
@SerializedName("photo_url")
    val photoUrl:String?,

    val alamat:String?
)

data class Review(
    val idKuliner:String?,

    val idReview:String?,

    val name:String?,

    val review:String?,

)
data class Menu(
    val idKuliner:String?,
    val idMenu:String?,
    val name:String?,
    val harga:String?,
    val keterangan:String?,
    val photo_url:String?,
)

data class ReviewMenu(
    val idKuliner:String?,
    val idMenu:String?,
    val idReview:String?,
    val name:String?,
    val review:String?,
)

data class Orders(
    val idOrder:String?,
    val namaResto:String?,
    val pesananMakanan:String?,
    val tanggal:String?,
    val photo_url:String?,

)
data class Promo(
    val idPromo:String?,
    val namaPromo:String?,
    val makananPromo:String?,
    val hargaAsli:String?,
    val hargaDiskon:String?,
    val photo_url:String?,

    )
data class Wallet(
    val idWallet:String?,
    val namaWallet:String?,
    val saldoWallet:String?,
    val photo_url:String?,

    )
