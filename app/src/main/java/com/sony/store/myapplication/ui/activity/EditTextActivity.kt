package com.sony.store.myapplication.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sony.store.myapplication.R
import com.sony.store.myapplication.adapter.SearchFlexboxAdapter
import com.sony.store.myapplication.base.BaseActivity
import kotlinx.android.synthetic.main.activiy_edit_text.*

class EditTextActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_edit_text)


        val searchFlexboxAdapter = SearchFlexboxAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@EditTextActivity)
            adapter = searchFlexboxAdapter
            searchFlexboxAdapter.list= listOf("1","2","3","4","5","","","","","","","","","","wer","wer","er","w","1","2","3","4","5","","","","","","","","","","wer")
        }
    }
}