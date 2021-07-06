package com.sony.store.myapplication.ui.fragment

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import com.arialyy.annotations.Download
import com.arialyy.aria.core.Aria
import com.arialyy.aria.core.download.DownloadTaskListener
import com.arialyy.aria.core.task.DownloadTask
import com.sony.store.myapplication.R
import com.sony.store.myapplication.base.BaseFragment
import java.io.File
import java.lang.Exception


const val DOWNLOAD_URL =
    "https://fga1.market.xiaomi.com/download/AppStore/067459e57040b49649b1b54e0a472f8a050b3cf51/com.sony.store.china.apk"

class SettingFragment : BaseFragment(), DownloadTaskListener {

    override fun init() {
        //注册aria
        Aria.download(this).register()
    }

    override fun setContentView(): Any {
        return R.layout.fragment_setting
    }

    override fun initView(view: View) {
        val path =
            requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS.plus("/gameStore"))
        val file = File(path, "game.apk")

        val taskId: Long = Aria.download(this)
            .load(DOWNLOAD_URL) //读取下载地址
            .setFilePath(file.path) //设置文件保存的完整路径
            .create() //启动下载
    }

    override fun onTaskCancel(task: DownloadTask?) {
        Log.e("TAG", "onTaskCancel")
    }

    override fun onPre(task: DownloadTask?) {
        Log.e("TAG", "taskStart")
    }

    override fun onTaskStart(task: DownloadTask?) {
        Log.e("TAG", "taskStart")
    }

    override fun onTaskPre(task: DownloadTask?) {
        Log.e("TAG", "onTaskPre")
    }

    override fun onTaskRunning(task: DownloadTask?) {
        if (task?.key == DOWNLOAD_URL) {
            //转换后文件大小
           val convertFileSize=task.convertFileSize
            ////任务进度百分比
            val percent = task.percent
            //转换单位后的下载速度，单位转换需要在配置文件中打开
            val convertSpeed = task.convertSpeed
            Log.e("TAG", " $convertFileSize ;  $percent  ;  $convertSpeed ; ")
        }
    }

    override fun onWait(task: DownloadTask?) {
        Log.e("TAG", "onWait")
    }

    override fun onTaskResume(task: DownloadTask?) {
        Log.e("TAG", "onTaskResume")
    }

    override fun onNoSupportBreakPoint(task: DownloadTask?) {
        Log.e("TAG", "onNoSupportBreakPoint")
    }

    override fun onTaskStop(task: DownloadTask?) {
        Log.e("TAG", "onTaskStop")
    }

    override fun onTaskFail(task: DownloadTask?, e: Exception?) {
        Log.e("TAG", "onTaskFail")
    }

    override fun onTaskComplete(task: DownloadTask?) {
        Log.e("TAG", "onTaskComplete")
    }

}