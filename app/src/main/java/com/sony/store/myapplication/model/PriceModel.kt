package com.sony.store.myapplication.model

data class PriceModel(
    val data: Data,
    val message: String,
    val returncode: Int
)

data class Data(
    val cartpromotelist: List<Any>,
    val eightcode: String,
    val productprice: Productprice,
    val promotelist: List<Any>
)

data class Productprice(
    val basestore: String,
    val channel: String,
    val eightcode: String,
    val memberpriceflag: String,
    val mtype: String,
    val price: Double,
    val pts: Double,
    val regprice: Double,
    val relatedproducts: Int,
    val salestatus: String,
    val srp: Double,
    val storestatus: String
)