package com.sony.store.myapplication.ui.activity

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import androidx.core.view.isNotEmpty
import com.sony.store.myapplication.R
import com.sony.store.myapplication.base.BaseActivity
import kotlinx.android.synthetic.main.activity_web_view.*

/**
 * 隐私协议 用户协议 等
 */
class WebViewActivity : BaseActivity() {

    private var startUrl: String = ""
    private var uploadMessage: ValueCallback<Uri?>? = null
    private lateinit var uploadMessageAboveL: ValueCallback<Array<Uri?>?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        initView()
        initWebSettings()
        initWebView()
    }

    private fun initView() {
        back.setOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                finish()
            }

        }
    }

    private fun initWebView() {
        webView.loadUrl("https://www.sonystyle.com.cn/content/dam/sonystyle-wechat/index.html#/Product?eightD=P43033968")
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(
                webView: WebView?,
                s: String,
                bitmap: Bitmap?
            ) {
                super.onPageStarted(webView, s, bitmap)
                startUrl = s
            }

            override fun onReceivedSslError(
                webView: WebView?,
                sslErrorHandler: SslErrorHandler,
                sslError: SslError?
            ) {
                sslErrorHandler.proceed()
            }

            override fun shouldOverrideUrlLoading(webView: WebView, s: String): Boolean {
                if (s.startsWith("weixin://") || s.startsWith("alipays://") || s.startsWith("mqqapi://")) {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse(s)
                    startActivity(intent)
                } else if (startUrl == s) {
                    webView.loadUrl(s)
                } else {
                    //交给系统处理
                    return super.shouldOverrideUrlLoading(webView, s)
                }
                return true
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(
                webView: WebView?,
                i: Int
            ) {

                super.onProgressChanged(webView, i)
            }

            // For Android >= 5.0
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri?>?>,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                uploadMessageAboveL = filePathCallback
                openImageChooserActivity()
                return true
            }
        }
    }

    private fun openImageChooserActivity() {

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun onActivityResultAboveL(
        requestCode: Int,
        resultCode: Int,
        intent: Intent?
    ) {
        var results: Array<Uri?>? = null
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                val dataString = intent.dataString
                val clipData = intent.clipData
                if (clipData != null) {
                    results = arrayOfNulls(clipData.itemCount)
                    for (i in 0 until clipData.itemCount) {
                        val item = clipData.getItemAt(i)
                        results[i] = item.uri
                    }
                }
                if (dataString != null) results = arrayOf(Uri.parse(dataString))
            }
        }
        uploadMessageAboveL.onReceiveValue(results)
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun initWebSettings() {
        val webSetting: WebSettings = webView.settings
        webSetting.allowFileAccess = true
        webSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        webSetting.loadWithOverviewMode = true
        webSetting.setSupportZoom(true)
        webSetting.builtInZoomControls = true
        webSetting.useWideViewPort = true
        webSetting.setSupportMultipleWindows(false)
        webSetting.setAppCacheEnabled(true)
        webSetting.domStorageEnabled = true
        webSetting.javaScriptEnabled = true
        webSetting.javaScriptCanOpenWindowsAutomatically = true
        webSetting.setGeolocationEnabled(true)
        webSetting.mixedContentMode = WebSettings.LOAD_NORMAL
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE)
        webSetting.domStorageEnabled = true
        webSetting.setAppCachePath(getDir("appcache", 0).path)
        webSetting.databasePath = getDir("databases", 0).path
        webSetting.setGeolocationDatabasePath(getDir("geolocation", 0).path)
        webSetting.pluginState = WebSettings.PluginState.ON_DEMAND
        webSetting.setUserAgentString(webView.getSettings().getUserAgentString() + "/sonyapp");


        CookieSyncManager.createInstance(this)
        CookieSyncManager.getInstance().sync()
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.isNotEmpty() && webView.canGoBack()) {
                webView.goBack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

}