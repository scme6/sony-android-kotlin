package com.sony.store.myapplication.net

import com.sony.store.myapplication.model.NewIndexModel
import com.sony.store.myapplication.model.PriceModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface ApiService {

    @GET("services/sonystyle/new-index/search")
    suspend fun getNewIndexData(): NewIndexModel

    @POST("b2csearch/esapi/product/pricepromotion")
    suspend fun getPrdPrice(@Body map: Map<String, String>): PriceModel

    @POST("b2csearch/esapi/product/pricepromotion")
     fun getPrdPrice2(@Body map: Map<String, String>): Call<ResponseBody>


    /**
     *
     *
    {"basestore":"SS","channel":"APP","eightcode":"P80819076","pval",""}
     *
     */


}