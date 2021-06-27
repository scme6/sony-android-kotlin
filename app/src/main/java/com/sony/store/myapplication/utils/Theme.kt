package com.sony.store.myapplication.utils

import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.sony.store.myapplication.R

/**
 * @Description
 * @author: qiang
 * @date: 2021-06-27
 */
object Theme {
    fun setDarkStatusWhite(activity: AppCompatActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView = activity.window.decorView
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                WindowCompat.getInsetsController(activity.window,decorView)?.isAppearanceLightStatusBars = true
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        } else {
            activity.window.statusBarColor = ContextCompat.getColor(activity, R.color.black)
        }
    }
}