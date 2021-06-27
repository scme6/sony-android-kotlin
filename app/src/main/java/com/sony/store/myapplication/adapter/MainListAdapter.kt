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
        private const val VIEW_TYPE_IMAGE_1 = 1
        // 菜单
        private const val VIEW_TYPE_MENU = 2

        // 秒杀的三张图
        private const val VIEW_TYPE_NOTICE = 3

        // 秒杀的内容
        private const val VIEW_TYPE_SEC_KILL_CONTENT = 3

        // 今日推荐
        private const val VIEW_TYPE_TODAY_RECOMMEND = 4

        // 新年大街
        private const val VIEW_TYPE_NEW_YEAR_STREET = 5

        // 商品流
        private const val VIEW_TYPE_FEEDS = 6

        // 正在加载tabs
        private const val VIEW_TYPE_LOADING_TABS = 7
    }

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> VIEW_TYPE_CAROUSEL
        1 -> VIEW_TYPE_IMAGE_1
        2 -> VIEW_TYPE_MENU
        3 -> VIEW_TYPE_NOTICE
        4 -> VIEW_TYPE_TODAY_RECOMMEND
        5 -> VIEW_TYPE_NEW_YEAR_STREET
        6 -> if (tabsLoaded) VIEW_TYPE_FEEDS else VIEW_TYPE_LOADING_TABS
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
            VIEW_TYPE_IMAGE_1 -> {
                val itemView = inflater.inflate(R.layout.item_image_1, parent, false)
                HomeImage1ViewHolder(itemView)
            }
            VIEW_TYPE_MENU -> {
                // 菜单
                val itemView = inflater.inflate(R.layout.item_menu, parent, false)
                HomeMenuViewHolder(itemView)
            }

            VIEW_TYPE_NOTICE -> {
                // 通知
                val itemView = inflater.inflate(R.layout.item_notice, parent, false)
                NoticeViewHolder(itemView)
            }

            VIEW_TYPE_SEC_KILL_CONTENT -> {
                // 秒杀的内容
                val itemView = inflater.inflate(R.layout.item_main, parent, false)
                CarouselViewHolder(itemView)
            }

            VIEW_TYPE_TODAY_RECOMMEND -> {
                // 今日推荐
                val itemView = inflater.inflate(R.layout.item_main, parent, false)
                CarouselViewHolder(itemView)
            }

            VIEW_TYPE_NEW_YEAR_STREET -> {
                // 新年大街
                val itemView = inflater.inflate(R.layout.item_main, parent, false)
                CarouselViewHolder(itemView)
            }

            VIEW_TYPE_LOADING_TABS -> {
                val itemView = inflater.inflate(R.layout.item_main, parent, false)
                CarouselViewHolder(itemView)
            }

            else -> {
                // 商品流
                val itemView = inflater.inflate(R.layout.item_main_feeds, parent, false)
                FeedsViewHolder(itemView, mFragment)
            }
        }
    }

    override fun getItemCount(): Int = 7

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeBannerViewHolder -> {
                holder.bindTo()
            }
            is HomeImage1ViewHolder -> {
                holder.bindTo()
            }
            is HomeMenuViewHolder -> {
                holder as HomeMenuViewHolder
                holder.bindTo1()
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