package com.sony.store.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sony.store.myapplication.base.BaseActivity
import com.sony.store.myapplication.ui.activity.EditTextActivity
import com.sony.store.myapplication.ui.activity.SearchActivity
import com.sony.store.myapplication.ui.activity.SmsCode
import com.sony.store.myapplication.ui.activity.WebViewActivity
import com.sony.store.myapplication.ui.fragment.CategoryFragment
import com.sony.store.myapplication.ui.fragment.HomeFragment
import com.sony.store.myapplication.ui.fragment.SettingFragment
import com.sony.store.myapplication.utils.UIUtil.dip2px
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
//        sendNotification()

        startActivity(Intent(this,SmsCode::class.java))

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

    private fun sendNotification(){
       val intent=PendingIntent.getActivity(this, 123, Intent(this,SearchActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)
       notification("title","content",80,intent)
    }
    private fun notification(title: String, content: String, progress: Int, intent: PendingIntent) {
        val sys = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var builder : NotificationCompat.Builder?=null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "default";
            val channelName = "????????????";
            val mChannel = NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH)
            //  mChannel.setDescription(description);
            //  mChannel.enableLights(true);
            //  mChannel.setLightColor(Color.RED);
            //  mChannel.enableVibration(true);
            //  mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            //  mChannel.setDescription(description);
            //  mChannel.enableLights(true);
            //  mChannel.setLightColor(Color.RED);
            //  mChannel.enableVibration(true);
            //  mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setSound(null, null)
            mChannel.importance = IMPORTANCE_LOW
            sys.createNotificationChannel(mChannel)
            builder = NotificationCompat.Builder(this, channelId)
        }
         builder=NotificationCompat.Builder(this,"default")
        //??????
        builder.setContentTitle(title)
        //????????????
        builder.setContentText(content)
        //?????????
        builder.setSmallIcon(R.mipmap.ic_launcher)
        //??????????????????????????????????????????????????????????????????????????????????????????
        // ???????????????
        // BitmpFactory.decodeResource(Resource res,int id)
        // ?????????????????????Id???????????????
        // builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        if (progress > 0 && progress < 100) {
            //??????????????????????????????false???,
            // ???????????????????????????true???
            // ?????????false??????????????????
            // ?????????true???????????????
            builder.setProgress(100, 0, false)

        } else {
            //0,0,false,????????????????????????
            builder.setProgress(0, 0, false)
            builder.setContentText("**??????");
        }
        //???????????????????????????????????????
        builder.setAutoCancel(true)
        //???????????????
        builder.setWhen(System.currentTimeMillis())
        //??????????????????????????????????????????
        builder.setContentIntent(intent)
        builder.setOnlyAlertOnce(true)
        sys.notify(1, builder.build())

        lifecycleScope.launch {
            repeat(100){
                delay(100)
                builder.setProgress(100, it, false)
                sys.notify(1, builder.build())

//                builder.setProgress(100, it, false)
//                sys.notify(it, builder.build())
            }
        }



//        val manager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        //??????????????????
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            val channelId = "default";
//            val channelName = "????????????";
//            manager.createNotificationChannel( NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH));
//        }
//        //
//        val notification = NotificationCompat.Builder(this,"default")
//            .setContentTitle("This is content title")
//            .setContentText("this is content text")
//            .setWhen(System.currentTimeMillis())
//            .setSmallIcon(R.mipmap.ic_launcher)
//            .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
//            .build();
//        manager.notify(1,notification);









    }




}