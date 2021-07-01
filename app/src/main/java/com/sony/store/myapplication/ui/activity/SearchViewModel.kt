package com.sony.store.myapplication.ui.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SearchViewModel(application: Application) : AndroidViewModel(application) {


    //搜索历史 测试数据
    private val _historyData: MutableLiveData<List<String>> = MutableLiveData()
    val historyData: LiveData<List<String>>
        get() = _historyData

    init {
        _historyData.value= listOf("FlexBox1","FlBoxexBox1","FlexBoBoxBoxx1","FlexBoxBoFlexBoxFlexBox1FlexBox1FlexBox1FlexBox1FlexBox11xBoxBox1","FlexBox1","FlexBox1","FlexBox1")
    }
}