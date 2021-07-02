package com.sony.store.myapplication.adapter.viewholder

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sony.store.myapplication.model.Item
import com.sony.store.myapplication.utils.LoadImage
import kotlinx.android.synthetic.main.item_2_big_banner_product.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Home2BigBannerProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bindTo(_2BigBannerProductData: List<Item>) {
        if (_2BigBannerProductData.isEmpty()) return
        if (_2BigBannerProductData.isNotEmpty()) {
            if (_2BigBannerProductData[0].fullSize == "true") {
                Glide.with(itemView.view_two_img)
                    .load(LoadImage.imageUrl(_2BigBannerProductData[0].image))
                    .into(itemView.view_two_img)
                itemView.c1?.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        android.R.color.transparent
                    )
                );
            } else {
                Glide.with(itemView.iv)
                    .load(LoadImage.imageUrl(_2BigBannerProductData[0].image))
                    .into(itemView.iv)
                itemView.c1?.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        android.R.color.white
                    )
                )
            }
            itemView.tvName.setTextColor(Color.parseColor(_2BigBannerProductData[0].titleColor))
            itemView.tvDesc.setTextColor(Color.parseColor(_2BigBannerProductData[0].subtitleColor))
            itemView.tvPrice.setTextColor(Color.parseColor(_2BigBannerProductData[0].priceColor))
            itemView.tvRmb1.setTextColor(Color.parseColor(_2BigBannerProductData[1].priceColor))

            itemView.tvName.text = _2BigBannerProductData[0].title
            itemView.tvDesc.text = _2BigBannerProductData[0].subtitle.replace("\\n", "\n")
            itemView.tvPrice.text = _2BigBannerProductData[0].price


        }
        if (_2BigBannerProductData.size >= 2) {
            if (_2BigBannerProductData[1].fullSize == "true") {
                Glide.with(itemView.view_two_img22)
                    .load(LoadImage.imageUrl(_2BigBannerProductData[1].image))
                    .into(itemView.view_two_img22)
                itemView.c2?.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        android.R.color.transparent
                    )
                );
            } else {
                Glide.with(itemView.iv2)
                    .load(LoadImage.imageUrl(_2BigBannerProductData[1].image))
                    .into(itemView.iv2)
                itemView.c2?.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        android.R.color.white
                    )
                )
            }
            itemView.tvName2.setTextColor(Color.parseColor(_2BigBannerProductData[1].titleColor))
            itemView.tvDesc2.setTextColor(Color.parseColor(_2BigBannerProductData[1].subtitleColor))
            itemView.tvRmb2.setTextColor(Color.parseColor(_2BigBannerProductData[1].priceColor))
            itemView.tvPrice2.setTextColor(Color.parseColor(_2BigBannerProductData[1].priceColor))
            itemView.tvName2.text = _2BigBannerProductData[1].title
            itemView.tvDesc2.text = _2BigBannerProductData[1].subtitle.replace("\\n", "\n")
            itemView.tvPrice2.text = _2BigBannerProductData[1].price

        }


    }


}