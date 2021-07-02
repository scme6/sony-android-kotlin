package com.sony.store.myapplication.model

import com.stx.xhb.xbanner.entity.SimpleBannerInfo

class NewIndexModel : ArrayList<NewIndexModelItem>()

data class NewIndexModelItem(
    val backgroundImage: String,
    val display: String,
    val iconFontColor: String,
    val iconFontColorDark: String,
    val iconscan: String,
    val imagecamera: String,
    val items: List<Item>,
    val label: String,
    val title: String,
    val trackingTitle: String,
    val type: String
)

data class Item(

    val display: String,
    val eightD: String,
    val fullSize: String,
    val image: String,
    val label: String,
    val linkApp: String,
    val linkType: String,
    val linkWechat: String,
    var price: String,
    val priceColor:String,
    val titleColor:String,
    val subtitleColor:String,
    val leftText:String,
    val leftTextColor:String,
    val leftTextImage:String,
    val product: String,
    val subtitle: String,
    val title: String,
    val trackingTitle: String
) : SimpleBannerInfo() {
    override fun getXBannerUrl(): Any {
        return ""
    }
}