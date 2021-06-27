package com.sony.store.myapplication

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.sony.store.myapplication.base.BaseActivity
import com.sony.store.myapplication.ui.HomeFragment
import com.sony.store.myapplication.utils.UIUtil.dip2px
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val homeFragment by lazy {
        HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        val size = dip2px(this, 32f)
        val homeDrawable = ContextCompat.getDrawable(this, R.drawable.select_home_menu)?.apply {
            setBounds(0, 0, size, size)
        }
        val productDrawable = ContextCompat.getDrawable(this, R.drawable.select_subject_menu)?.apply {
            setBounds(0, 0, size, size)
        }
        val shoppingCardDrawable = ContextCompat.getDrawable(this,R.drawable.select_discover_menu )?.apply {
            setBounds(0, 0, size, size)
        }
        val myDrawable = ContextCompat.getDrawable(this,R.drawable.select_me_menu)?.apply {
            setBounds(0, 0, size, size)
        }
        val settingDrawable = ContextCompat.getDrawable(this,R.drawable.select_settings_menu)?.apply {
            setBounds(0, 0, size, size)
        }
        homeButton.setCompoundDrawables(null, homeDrawable, null, null)
        productButton.setCompoundDrawables(null, productDrawable, null, null)
        shoppingCardButton.setCompoundDrawables(null, shoppingCardDrawable, null, null)
        myButton.setCompoundDrawables(null, myDrawable, null, null)
        settingButton.setCompoundDrawables(null, settingDrawable, null, null)


        val mBeginTransaction = supportFragmentManager.beginTransaction()
        mBeginTransaction.add(R.id.mFrameLayout, homeFragment)
        mBeginTransaction.commitNowAllowingStateLoss()
    }
}