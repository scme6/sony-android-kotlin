package com.sony.store.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sony.store.myapplication.base.BaseActivity
import com.sony.store.myapplication.ui.activity.WebViewActivity
import com.sony.store.myapplication.ui.fragment.CategoryFragment
import com.sony.store.myapplication.ui.fragment.HomeFragment
import com.sony.store.myapplication.ui.fragment.SettingFragment
import com.sony.store.myapplication.utils.UIUtil.dip2px
import com.sony.store.myapplication.widget.DownloadProgress
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : BaseActivity() {

    private var homeFragment: HomeFragment? = null
    private var categoryFragment: CategoryFragment? = null
    private var settingFragment: SettingFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState?.let {
            homeFragment = supportFragmentManager.findFragmentByTag(TAG_HOME) as HomeFragment?
            categoryFragment =
                supportFragmentManager.findFragmentByTag(TAG_HOME) as CategoryFragment?
            settingFragment =
                supportFragmentManager.findFragmentByTag(TAG_SETTING) as SettingFragment?
        }
        initView()
    }

    private fun initView() {
        val size = dip2px(this, 32f)
        val homeDrawable = ContextCompat.getDrawable(this, R.drawable.select_home_menu)?.apply {
            setBounds(0, 0, size, size)
        }
        val productDrawable =
            ContextCompat.getDrawable(this, R.drawable.select_subject_menu)?.apply {
                setBounds(0, 0, size, size)
            }
        val shoppingCardDrawable =
            ContextCompat.getDrawable(this, R.drawable.select_discover_menu)?.apply {
                setBounds(0, 0, size, size)
            }
        val myDrawable = ContextCompat.getDrawable(this, R.drawable.select_me_menu)?.apply {
            setBounds(0, 0, size, size)
        }
        val settingDrawable =
            ContextCompat.getDrawable(this, R.drawable.select_settings_menu)?.apply {
                setBounds(0, 0, size, size)
            }
        homeButton.setCompoundDrawables(null, homeDrawable, null, null)
        productButton.setCompoundDrawables(null, productDrawable, null, null)
        shoppingCardButton.setCompoundDrawables(null, shoppingCardDrawable, null, null)
        myButton.setCompoundDrawables(null, myDrawable, null, null)
        settingButton.setCompoundDrawables(null, settingDrawable, null, null)
        val mBeginTransaction = supportFragmentManager.beginTransaction()
        homeFragment = HomeFragment()
        homeFragment?.let {
            mBeginTransaction.add(R.id.mFrameLayout, it)
        }
        mBeginTransaction.commitNowAllowingStateLoss()

        bottomBarBackground.setOnCheckedChangeListener { group, checkedId ->
            clickBottomMenu(checkedId)
        }
    }

    private fun clickBottomMenu(checkedId: Int) {
        val mBeginTransaction = supportFragmentManager.beginTransaction()
        if (homeFragment != null) mBeginTransaction.hide(homeFragment!!)
        if (categoryFragment != null) mBeginTransaction.hide(categoryFragment!!)
        if (settingFragment != null) mBeginTransaction.hide(settingFragment!!)

        when (checkedId) {
            R.id.homeButton -> {
                homeFragment?.let {
                    mBeginTransaction.show(it)
                } ?: let {
                    homeFragment =
                        HomeFragment()
                    homeFragment?.let { it2 ->
                        mBeginTransaction.add(R.id.mFrameLayout, it2, TAG_HOME)
                    }
                }
            }
            R.id.productButton -> {
                categoryFragment?.let {
                    mBeginTransaction.show(it)
                } ?: let {
                    categoryFragment =
                        CategoryFragment()
                    categoryFragment?.let { it2 ->
                        mBeginTransaction.add(R.id.mFrameLayout, it2, TAG_CATEGORY)
                    }
                }
            }
            R.id.shoppingCardButton -> {
                startActivity(Intent(this@MainActivity, WebViewActivity::class.java))
            }
            R.id.myButton -> {
            }
            R.id.settingButton -> {
                settingFragment?.let {
                    mBeginTransaction.show(it)
                } ?: let {
                    settingFragment =
                        SettingFragment()
                    settingFragment?.let { it2 ->
                        mBeginTransaction.add(R.id.mFrameLayout, it2, TAG_SETTING)
                    }
                }
            }
        }
        mBeginTransaction.commitAllowingStateLoss()
    }

    private fun shareDialog() {
        val dialog = BottomSheetDialog(this, R.style.BottomSheetDialog)
        dialog.setContentView(R.layout.dialog_share)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.findViewById<TextView>(R.id.weChat)?.setOnClickListener {
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.moments)?.setOnClickListener {
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.qq)?.setOnClickListener {
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.qqZone)?.setOnClickListener {
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.sina)?.setOnClickListener {
            dialog.dismiss()
        }
        dialog.findViewById<TextView>(R.id.cancel)?.setOnClickListener {
            dialog.dismiss()
        }
//        dialog.findViewById<DownloadProgress>(R.id.button)?.apply {
//            setState(DownloadProgress.STATE_DOWNLOADING);
//            var progress=0
//            lifecycleScope.launch {
//                repeat(20){
//                    progress+=5
//                    setProgressText("下载中", progress.toFloat());
//                    delay(1000)
//                }
//            }
//
//        }
        dialog.show()
    }


}