package com.inmortal.messenger.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.inmortal.messenger.R

class Cookie : AppCompatActivity() {

    var w: WebView? = null
    var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cookie)


        w = findViewById<View>(R.id.web) as WebView
        progressBar = findViewById<View>(R.id.prg) as ProgressBar
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        w!!.webViewClient = WebViewClient()

        // this will load the url of the website
        w!!.loadUrl("https://www.sociomee.com/cookie-policy/")

        // this will enable the javascript settings
        w!!.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        w!!.settings.setSupportZoom(true)
        w!!.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
        w!!.settings.builtInZoomControls = true
        w!!.getSettings().setUseWideViewPort(true);
        w!!.getSettings().setLoadWithOverviewMode(true);
        w!!.setInitialScale(1);
        w!!.getSettings().setUseWideViewPort(true);
        w!!.getSettings().setLoadWithOverviewMode(true);

    }
}