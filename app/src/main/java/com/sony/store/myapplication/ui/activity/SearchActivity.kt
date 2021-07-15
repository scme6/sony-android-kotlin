package com.sony.store.myapplication.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.sony.store.myapplication.R
import com.sony.store.myapplication.adapter.SearchFlexboxAdapter
import com.sony.store.myapplication.base.BaseActivity
import com.sony.store.myapplication.ui.details.DetailsActivity
import com.sony.store.myapplication.widget.dialog.TopicDialog
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.include_search_edit.*

class SearchActivity : BaseActivity() {
    private val searchViewModel by viewModels<SearchViewModel>()

    private val searchFlexboxAdapter by lazy { SearchFlexboxAdapter() }
    private val recommendAdapter by lazy { SearchFlexboxAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
    }

    private fun initView() {

        var ada = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listOf(
                "abcdefg",
                "hijklabcdefgmn",
                "abcdefg",
                "abcdefg",
                "abcdefg",
                "abcdefg",
                "abcdefg"
            )
        )
        etSearch.setAdapter(ada)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                clear.isVisible = s?.length != 0
            }
        })
        val historyLayoutManager = FlexboxLayoutManager(this@SearchActivity).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
            flexWrap = FlexWrap.WRAP
        }
        val recommendLayoutManager = FlexboxLayoutManager(this@SearchActivity).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
            flexWrap = FlexWrap.WRAP
        }
        searchViewModel.historyData.observe(this, Observer {
            searchFlexboxAdapter.list = it
            recommendAdapter.list = it
        })
        historyRecyclerView.apply {
            layoutManager = historyLayoutManager
            adapter = searchFlexboxAdapter
        }

        recommendRecyclerView.apply {
            layoutManager = recommendLayoutManager
            adapter = recommendAdapter
        }

        back.setOnClickListener { finish() }
        deleteHistory.setOnClickListener {
            deleteHistoryDialog()
        }
        clear.setOnClickListener {
            etSearch.setText("")
        }
        tvSearch.setOnClickListener {
            startActivity(Intent(this,DetailsActivity::class.java))
        }
    }

    /**
     * 是否清除搜索历史
     */
    private fun deleteHistoryDialog() {
        TopicDialog(this).apply {
            setMessage(getString(R.string.deleteHistoryMessage))
            setButton(
                DialogInterface.BUTTON_POSITIVE,
                getString(R.string.button_positive),
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
            setButton(
                DialogInterface.BUTTON_NEGATIVE,
                getString(R.string.button_negative),
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
            show()
        }
    }

}