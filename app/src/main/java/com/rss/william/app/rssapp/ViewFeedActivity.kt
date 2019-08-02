package com.rss.william.app.rssapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class ViewFeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_feed)

        if (supportActionBar != null)
            supportActionBar!!.setDisplayShowTitleEnabled(false)

        val urlExtra = intent.getStringExtra("url-extra")

        // Obtener WebView
        val webview = findViewById(R.id.webviewFeed) as WebView

        // Habilitar Javascript en el renderizado
        webview.settings.javaScriptEnabled = true

        // Transmitir localmente
        webview.webViewClient = WebViewClient()

        // Cargar el contenido de la url
        webview.loadUrl(urlExtra)
    }
}
