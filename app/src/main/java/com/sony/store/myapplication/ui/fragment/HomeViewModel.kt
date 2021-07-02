package com.sony.store.myapplication.ui.fragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sony.store.myapplication._2_BIG_BANNER_PRODUCT
import com.sony.store.myapplication.model.NewIndexModel
import com.sony.store.myapplication.model.NewIndexModelItem
import com.sony.store.myapplication.model.PriceModel
import com.sony.store.myapplication.net.ApiService
import com.sony.store.myapplication.net.RetrofitClient
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.security.auth.login.LoginException
import kotlin.Exception

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Log.e("TAG_exceptionHandler", throwable.message.toString())
        }

    private val _nweIndexData: MutableLiveData<NewIndexModel> = MutableLiveData()
    val nweIndexData: LiveData<NewIndexModel>
        get() = _nweIndexData

    init {
        getNewIndexData()
//        getPrdPrice2()
    }

    /**
     * 首页数据
     */
    private fun getNewIndexData() = viewModelScope.launch(coroutineExceptionHandler) {
        val mNewIndexData = RetrofitClient.apiService.getNewIndexData()
        val priceNewIndexModel = mNewIndexData.filterIndexed { index, newIndexModelItem ->
            newIndexModelItem.label == _2_BIG_BANNER_PRODUCT
        }.first()
        var priceNewIndexModelItemsIndex = 0
        priceNewIndexModel.items.forEachIndexed { index, item ->
            if (item.price.isEmpty()) {
                priceNewIndexModelItemsIndex = index
                try {
                    val priceModel = RetrofitClient.apiService.getPrdPrice(getPrdPrice(item.eightD))
                    mNewIndexData.forEach {it2->
                        if (it2.label == _2_BIG_BANNER_PRODUCT){
                            it2.items.forEach {it3->
                                if (it3.eightD==priceModel.data.eightcode){
                                    it3.price=priceModel.data.productprice.price.toString()
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        _nweIndexData.value = mNewIndexData
    }

    /**
     * 获取首页两通栏中价格
     */
    private fun getPrdPrice(eightcode: String): Map<String, String> {
        val map: MutableMap<String, String> = mutableMapOf()
        map.put("basestore", "SS");
        map.put("channel", "APP");
        map.put("eightcode", eightcode);
        map.put("pval", "")
        return map
    }


    private fun getPrdPrice2() {
        val map: MutableMap<String, String> = mutableMapOf()
        map.put("basestore", "SS");
        map.put("channel", "APP");
        map.put("eightcode", "P17063180");
        map.put("pval", "")
        RetrofitClient.apiService.getPrdPrice2(map).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val st = response.body()?.string()
                Log.e("TAG", st.toString())
            }
        })
    }


}