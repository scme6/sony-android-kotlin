package com.sony.store.myapplication.net

import android.app.Application
import android.content.Context
import android.util.Log
import com.sony.store.myapplication.BASE_URL
import com.sony.store.myapplication.CONNECT_TIMEOUT
import com.sony.store.myapplication.READ_TIMEOUT
import com.sony.store.myapplication.WRITE_TIMEOUT
import com.sony.store.myapplication.net.persistentcookiejar.PersistentCookieJar
import com.sony.store.myapplication.net.persistentcookiejar.cache.SetCookieCache
import com.sony.store.myapplication.net.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.sony.store.myapplication.net.util.HttpsUtils.setUnsafe
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object  RetrofitClient{
//    private var apiService: ApiService? = null
//
//    init {
//        val mOkHttpClient = OkHttpClient.Builder()
//            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS) //建立连接 耗时
//            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
//            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
//            .cookieJar(PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context)))
//            .build()
//
//        apiService = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(mOkHttpClient)
//            .build()
//            .create(ApiService::class.java)
//
//    }
//
//    companion object {
//        private var mRetrofitClient: RetrofitClient? = null
//            get() {
//                if (field == null) {
//                    synchronized(RetrofitClient::class.java) {
//                        if (field == null) {
//                            field = RetrofitClient()
//                        }
//                    }
//                }
//                return field!!
//            }
//        fun getInstance(): RetrofitClient {
//            return mRetrofitClient!!
//        }
//    }
//
//    fun api(): ApiFunction = apiFunction!!
//

//    private val cookieJar by lazy {
//        PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))
//    }
    private val okHttpClient by lazy {
        Log.e("TAG","okHttpClient")
        OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS).apply {
                connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                setUnsafe(this)
//                cookieJar(cookieJar)
            }
            .build()
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private var apiService: ApiService? = null


    @Synchronized
    fun getInstance(): ApiService {
        if (apiService == null) {
            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService!!
    }


}