package com.example.remotejobs.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.remotejobs.R
import kotlinx.android.synthetic.main.fragment_web_view.*


class WebViewFragment : Fragment(R.layout.fragment_web_view) {
    private val args: WebViewFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(args.webViewUrl)
        }
    }
}