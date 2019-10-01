package com.example.advertpal.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.advertpal.App
import com.example.advertpal.features.works.WorksActivity
import com.example.advertpal.utils.AUTH_URL
import com.example.advertpal.utils.SharedPrefWrapper
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sPref: SharedPrefWrapper

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.advertpal.R.layout.activity_main)
        App.component.inject(this)

        sPref.saveToken("de8a7757f0d43b6d9b66adf993773df0b1a2bfa4e66364fe12125debfcf60d9801e135af724de1c7f513c")
        sPref.saveUserId("116812347")
        startActivity(Intent(this@MainActivity, WorksActivity::class.java))
        finish()


        wv_auth.settings.javaScriptEnabled = true
        wv_auth.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {

                url?.let {
                    if (it.contains("access_token") && it.contains("user_id")) {
                        val token = getFieldFromUrl(url, "access_token")
                        val userId = getFieldFromUrl(url, "user_id")
                        sPref.saveToken(token)
                        sPref.saveUserId(userId)
                        startActivity(Intent(this@MainActivity, WorksActivity::class.java))
                        finish()
                    }
                }

                super.onPageFinished(view, url)
            }
        }
        wv_auth.loadUrl(AUTH_URL)
    }

    private fun getFieldFromUrl(url: String, field: String): String {
        val splittedUrl = url.split("$field=")
        if (splittedUrl.size.compareTo(1) != 0) {
            return splittedUrl[1].split("&")[0]
        }
        return ""
    }
}
