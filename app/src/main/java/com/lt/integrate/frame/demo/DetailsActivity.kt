package com.lt.integrate.frame.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_details.*
class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initWebView(getIntent().getStringExtra("url"))
    }

    fun initWebView(url: String?){
        val setting = mWebView.settings
        setting.javaScriptEnabled = true
        setting.defaultTextEncodingName = "UTF-8"
        setting.javaScriptCanOpenWindowsAutomatically = true//允许js弹出窗口
        setting.builtInZoomControls = true // 原网页基础上缩放
        setting.useWideViewPort = true
        setting.setSupportZoom(true)//支持缩放
        setting.loadWithOverviewMode = true
        setting.domStorageEnabled = true
        setting.blockNetworkImage = false
        setting.blockNetworkLoads = false
        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

                view.loadUrl(url)

                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
            }
        }
        mWebView.loadUrl(url)
        mWebView.webChromeClient = object : WebChromeClient() {
            override fun onRequestFocus(view: WebView) {
                super.onRequestFocus(view)
                view.requestFocus()
            }

            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)

            }
        }
    }
}
