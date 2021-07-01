package com.sony.store.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.sony.store.myapplication.R
import com.sony.store.myapplication.adapter.viewholder.*

class MainListAdapter(private val mFragment: Fragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var tabsLoaded = false

    private var loadingTabsListener: (() -> Unit)? = null

    companion object {
        // 轮播图
        private const val VIEW_TYPE_CAROUSEL = 0

        //一张banner  通栏_带皮肤
        private const val VIEW_TYPE_1_BIG_BANNER = 1

        //"通栏_两等分白底产品"
        private const val VIEW_TYPE_2_BIG_BANNER_PRODUCT = 2

        //"通栏_三等分带皮肤"
        private const val VIEW_TYPE_3_BIG_BANNER = 3

        // 菜单 menu
        private const val VIEW_TYPE_MENU = 4

        //member_registration 会员注册
        private const val VIEW_TYPE_MEMBER_REGISTRATION = 5

        // 通知
        private const val VIEW_TYPE_NOTICE = 6

        //限时秒杀
        private const val VIEW_TYPE_FLASH_BUY = 7

        //限时免息 Instalment
        private const val VIEW_TYPE_INSTALMENT = 8

        //重叠viewPager
        private const val VIEW_TYPE_VIEW_PAGER = 9

        //新品 热卖
        private const val VIEW_TYPE_NEW_PRODUCT = 10

        //"通栏_四等分带皮肤"
        private const val VIEW_TYPE_4_BIG_BANNER = 11

        //直播 回放
        private const val VIEW_TYPE_LIVE_STREAMING = 12

        //底部推荐产品  会员专区
        private const val VIEW_TYPE_FEEDS = 13

        private const val VIEW_TYPE_LOADING_TABS = 14
    }

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> VIEW_TYPE_CAROUSEL
        1 -> VIEW_TYPE_1_BIG_BANNER
        2 -> VIEW_TYPE_2_BIG_BANNER_PRODUCT
        3 -> VIEW_TYPE_3_BIG_BANNER
        4 -> VIEW_TYPE_MENU
        5 -> VIEW_TYPE_MEMBER_REGISTRATION
        6 -> VIEW_TYPE_NOTICE
        7 -> VIEW_TYPE_FLASH_BUY
        8 -> VIEW_TYPE_INSTALMENT
        9 -> VIEW_TYPE_VIEW_PAGER
        10 -> VIEW_TYPE_NEW_PRODUCT
        11 -> VIEW_TYPE_4_BIG_BANNER
        12 -> VIEW_TYPE_LIVE_STREAMING
        13 -> if (tabsLoaded) VIEW_TYPE_FEEDS else VIEW_TYPE_LOADING_TABS
        else -> -1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_CAROUSEL -> {
                // 轮播图
                val itemView = inflater.inflate(R.layout.item_banner, parent, false)
                HomeBannerViewHolder(itemView)
            }
            VIEW_TYPE_1_BIG_BANNER -> {
                //一张banner  通栏_带皮肤
                val itemView = inflater.inflate(R.layout.item_image_1, parent, false)
                HomeImage1ViewHolder(itemView)
            }
            VIEW_TYPE_2_BIG_BANNER_PRODUCT -> {
                //"通栏_两等分白底产品"
                val itemView = inflater.inflate(R.layout.item_2_big_banner_product, parent, false)
                Home2BigBannerProductViewHolder(itemView)
            }
            VIEW_TYPE_3_BIG_BANNER -> {
                //"通栏_三等分带皮肤"
                val itemView = inflater.inflate(R.layout.item_3_big_banner, parent, false)
                Home3BigBannerViewHolder(itemView)
            }
            VIEW_TYPE_MENU -> {
                // 菜单
                val itemView = inflater.inflate(R.layout.item_menu, parent, false)
                HomeMenuViewHolder(itemView)
            }
            VIEW_TYPE_MEMBER_REGISTRATION -> {
                //member_registration 会员注册
                val itemView = inflater.inflate(R.layout.item_member_registration, parent, false)
                HomeMemberRegistrationViewHolder(itemView)
            }
            VIEW_TYPE_NOTICE -> {
                // 通知
                val itemView = inflater.inflate(R.layout.item_notice, parent, false)
                NoticeViewHolder(itemView)
            }
            VIEW_TYPE_FLASH_BUY -> {
                // 限时秒杀
                val itemView = inflater.inflate(R.layout.item_flashbuy, parent, false)
                HomeFlashBuyViewHolder(itemView)
            }
            VIEW_TYPE_INSTALMENT -> {
                // 限时免息
                val itemView = inflater.inflate(R.layout.item_flashbuy, parent, false)
                HomeInstalmentViewHolder(itemView)
            }
            VIEW_TYPE_VIEW_PAGER -> {
                // 重叠viewpager
                val itemView = inflater.inflate(R.layout.item_sub_kv, parent, false)
                HomeSubKvViewHolder(itemView)
            }
            VIEW_TYPE_NEW_PRODUCT -> {
                // 新品 热卖
                val itemView = inflater.inflate(R.layout.item_new_product_carousel, parent, false)
                HomeNewProductViewHolder(itemView, mFragment)

            }
            VIEW_TYPE_4_BIG_BANNER -> {
                // "通栏_四等分带皮肤"
                val itemView = inflater.inflate(R.layout.item_4_big_banner, parent, false)
                Home4BigBannerViewHolder(itemView)
            }
            VIEW_TYPE_LIVE_STREAMING -> {
                // 直播 回看
                val itemView = inflater.inflate(R.layout.item_live_streaming, parent, false)
                HomeLiveStreamingViewHolder(itemView)
            }
            else -> {
                //底部推荐产品  会员专区
                val itemView = inflater.inflate(R.layout.item_main_feeds, parent, false)
                FeedsViewHolder(itemView, mFragment)
            }
        }
    }

    override fun getItemCount(): Int = 14

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeBannerViewHolder -> {
                holder.bindTo()
            }
            is HomeImage1ViewHolder -> {
                holder.bindTo()
            }
            is Home2BigBannerProductViewHolder -> {

            }
            is Home3BigBannerViewHolder -> {

            }
            is HomeMenuViewHolder -> {
                holder as HomeMenuViewHolder
                holder.bindTo1()
            }
            is HomeMemberRegistrationViewHolder -> {

            }
            is NoticeViewHolder -> {
                holder as NoticeViewHolder
            }
            is HomeFlashBuyViewHolder -> {
                holder as HomeFlashBuyViewHolder
                holder.bindTo()
            }
            is HomeInstalmentViewHolder -> {
                holder as HomeInstalmentViewHolder
                holder.bindTo()
            }
            is HomeSubKvViewHolder -> {
                holder as HomeSubKvViewHolder
                holder.bindTo()
            }
            is HomeNewProductViewHolder -> {
                holder as HomeNewProductViewHolder
                holder.bindTo()
            }
            is HomeLiveStreamingViewHolder -> {
                holder as HomeLiveStreamingViewHolder
                holder.bindTo()
            }
        }

    }

    fun setLoadingTabsListener(listener: () -> Unit) {
        this.loadingTabsListener = listener
    }

    /**
     * tabs加载完成
     */
    fun onTabsLoaded() {
        this.tabsLoaded = true
        this.notifyItemChanged(7)
    }
}