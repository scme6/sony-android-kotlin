package com.sony.store.myapplication.utils

import android.content.Context

/**
 * @Description
 * @author: qiang
 * @date: 2021-06-27
 */

object UIUtil {

    fun screenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun px2dip(context: Context, pxValue: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }
}