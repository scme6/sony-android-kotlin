package com.sony.store.myapplication.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private var _viewPagerPosition: MutableLiveData<Int> = MutableLiveData()

    val viewPagerPosition: LiveData<Int>
        get() = _viewPagerPosition

    init {
        _viewPagerPosition.value = 0
    }

    /**
     * 上一页
     */
    fun minusViewPagerPosition() {
        _viewPagerPosition.value = _viewPagerPosition.value?.minus(1)
    }

    /**
     * 下一页
     */
    fun plusViewPagerPosition() {
        _viewPagerPosition.value = _viewPagerPosition.value?.plus(1)
    }
}