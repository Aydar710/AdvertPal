package com.example.advertpal.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.advertpal.App
import com.example.advertpal.R
import com.example.advertpal.base.BaseActivity
import com.example.advertpal.features.works.WorksActivity
import com.example.advertpal.utils.AUTH_URL
import com.example.advertpal.utils.SharedPrefWrapper
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity() {

    @Inject
    lateinit var sPref: SharedPrefWrapper

    private var errorOccured = false

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.component.inject(this)

        initNetworkSnackBar(R.id.activity_main)
        progressBar = pb_main
        showProgress()

        wv_auth.settings.javaScriptEnabled = true
        wv_auth.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                wv_auth.visibility = View.GONE
                showProgress()
                errorOccured = false
                super.onPageStarted(view, url, favicon)
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                errorOccured = true
                super.onReceivedError(view, request, error)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                if (!errorOccured){
                    wv_auth.visibility = View.VISIBLE
                }
                hideProgress()
                url?.let {
                    if (it.contains("access_token") && it.contains("user_id")) {
                        wv_auth.visibility = View.INVISIBLE
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

    override fun showData() {
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
