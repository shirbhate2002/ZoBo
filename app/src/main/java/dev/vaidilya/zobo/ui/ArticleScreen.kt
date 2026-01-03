package dev.vaidilya.zobo.ui

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebPageView(
    url: String,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        }
    )
}


@Composable
fun ArticleScreen(
    modifier: Modifier,
    url: String?
) {
    url?.let {
        WebPageView(
            "https://blog.zomato.com/$it",
            modifier = modifier
        )
    }
}