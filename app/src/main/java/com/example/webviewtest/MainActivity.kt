package com.example.webviewtest

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

@SuppressLint("SetJavaScriptEnabled")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val webView = findViewById<WebView>(R.id.webView)

        webView.settings.javaScriptEnabled = true;

        webView.settings.javaScriptCanOpenWindowsAutomatically = true;  // 자바스크립트가 창을 자동으로 열 수 있게할지 여부

        webView.settings.loadsImagesAutomatically = true // 이미지 자동 로드

        webView.settings.useWideViewPort = true // wide

        webView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {

                return super.shouldOverrideKeyEvent(view, event)
            }

        }
        webView.loadUrl("file:///android_asset/webview.html");
        webView.addJavascriptInterface(JavascriptBridge(),"Native")
    }


    inner class JavascriptBridge() {
        @JavascriptInterface
        fun showMessage(str : String) {
            System.out.println("Message Received : $str")
            if(str=="1111"){
                val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            }
        }
    }
}