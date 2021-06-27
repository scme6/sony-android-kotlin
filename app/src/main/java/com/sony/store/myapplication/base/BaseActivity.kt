package com.sony.store.myapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sony.store.myapplication.utils.Theme.setDarkStatusWhite

/**
 * @Description
 * @author: qiang
 * @date: 2021-06-27
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDarkStatusWhite(this)
    }


}