package com.sony.store.myapplication.utils

import com.sony.store.myapplication.BASE_IMAGE_URL

object LoadImage {

    fun imageUrl(url: String): String {
        return "$BASE_IMAGE_URL$url"
    }
}