package com.example.advertpal.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.advertpal.App
import com.example.advertpal.utils.AUTH_URL
import com.example.advertpal.utils.SharedPrefWrapper
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sPref : SharedPrefWrapper

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.advertpal.R.layout.activity_main)
        App.component.inject(this)


        wv_auth.loadUrl(AUTH_URL)
        wv_auth.settings.javaScriptEnabled = true
        wv_auth.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {

                val splittedUrl = url?.split("access_token=")

                if (splittedUrl?.size?.compareTo(1) != 0) {
                    splittedUrl?.let {
                        val token = it[1].split("&")[0]
                        sPref.saveToken(token)
                        startActivity(Intent(this@MainActivity, WorksActivity::class.java))
                        finish()
                    }
                }
                super.onPageFinished(view, url)
            }
        }
        wv_auth.loadUrl(AUTH_URL)
    }
}
