package com.sony.store.myapplication.net

import com.sony.store.myapplication.BASE_URL
import com.sony.store.myapplication.net.util.HttpsUtils
import com.sony.store.myapplication.net.util.HttpsUtils.setUnsafe
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS).apply {
//                setUnsafe(this)
            }
            .build()
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}