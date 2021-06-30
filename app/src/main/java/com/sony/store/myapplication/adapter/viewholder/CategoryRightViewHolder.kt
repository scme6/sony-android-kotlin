package com.sony.store.myapplication.adapter.viewholder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sony.store.myapplication.adapter.FeedsListAdapter
import kotlinx.android.synthetic.main.item_category_right.view.*

class CategoryRightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bindTo(){

        itemView.recyclerView.apply {
            layoutManager=LinearLayoutManager(context)
            //测试用
            adapter= FeedsListAdapter()
        }
        itemView.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
               val layoutManager= recyclerView.layoutManager as LinearLayoutManager
                if (layoutManager.findFirstCompletelyVisibleItemPosition()==0){
                    //处于顶部
                    Log.e("TAG","处于顶部")
                }
                if ( layoutManager.findLastCompletelyVisibleItemPosition()==layoutManager.itemCount-1){
                    //处于底部
                    Log.e("TAG","处于底部")
                }

                //0 未滚动 1、2滚动
               if (recyclerView.scrollState==0) {
                   Log.e("TAG","未滚动")
               }else{
                   Log.e("TAG","滚动")



               }

            }
        })
    }

}