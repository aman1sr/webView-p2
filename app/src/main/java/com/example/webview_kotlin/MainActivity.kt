package com.example.webview_kotlin

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.webview_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding
    private val URL = "https://developer.android.com/topic/libraries/view-binding"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.webView.apply {
            this.settings.loadsImagesAutomatically = true
            this.settings.javaScriptEnabled = true
            binding.webView.scrollBarSize = View.SCROLLBARS_INSIDE_OVERLAY
            binding.webView.webViewClient = WebViewClient()

            binding.webView.loadUrl(URL)
        }

        val progressDialog: ProgressDialog? = ProgressDialog(this)

        binding.webView.webViewClient = object: WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressDialog?.setTitle("Loading...")
                progressDialog?.setMessage("Please wait...")
                progressDialog?.setCancelable(false)
                progressDialog?.show()

            }

            override fun onPageCommitVisible(view: WebView?, url: String?) {
                super.onPageCommitVisible(view, url)

                progressDialog?.dismiss()

                /*
                * Ab. statement is same as :
                *                            if (progressDialog != null) {
                                                 progressDialog.dismiss()
                                                  }
                *
                * */
            }
        }









    }
}