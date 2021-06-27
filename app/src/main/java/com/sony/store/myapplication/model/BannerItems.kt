package com.sony.store.myapplication.model

import com.stx.xhb.xbanner.entity.SimpleBannerInfo

data class  BannerItems (
    val image: String
  ): SimpleBannerInfo() {
    override fun getXBannerUrl(): Any {
        return ""
    }
}