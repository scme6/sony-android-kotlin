package com.sony.store.myapplication.adapter.viewholder
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sony.store.myapplication.adapter.HomeMenuAdapter
import com.sony.store.myapplication.model.Item
import kotlinx.android.synthetic.main.item_menu.view.*

class HomeMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo1(backgroundImage:String,menuData: List<Item>) {
        val mLayoutManager = GridLayoutManager(itemView.context,2,LinearLayoutManager.HORIZONTAL,false)
        itemView.recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = HomeMenuAdapter(menuData)
        }
        itemView.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //当前RcyclerView显示区域的高度。水平列表屏幕从左侧到右侧显示范围
                val extent = recyclerView.computeHorizontalScrollExtent()

                //整体的高度，注意是整体，包括在显示区域之外的。
                val range = recyclerView.computeHorizontalScrollRange()

                //已经滚动的距离，为0时表示已处于顶部。
                val offset = recyclerView.computeHorizontalScrollOffset()

                //计算出溢出部分的宽度，即屏幕外剩下的宽度
                val maxEndX = (range - extent).toFloat()

                //计算比例
                val proportion = offset / maxEndX

                val layoutWidth = itemView.backgroundLine.width
                val indicatorViewWidth = itemView.main_line.width
                //可滑动的距离
                val scrollableDistance = layoutWidth - indicatorViewWidth
                //设置滚动条移动
                itemView.main_line.translationX = scrollableDistance * proportion
            }

        })

    }
}