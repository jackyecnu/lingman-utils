package com.lingman.common.tool


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.lingman.common.R
import com.lingman.common.base.BaseActivity
import com.lingman.common.databinding.ActivityWebviewBinding


class DemonWebviewActivity : BaseActivity<ActivityWebviewBinding>() {
    companion object{
        const val KEY_WEBVIEW_PATH = "KEY_WEBVIEW_PATH"
        const val KEY_WEBVIEW_TITLE = "KEY_WEBVIEW_TITLE"
    }
    private lateinit var mWebView: WebView

    override fun initData(savedInstanceState: Bundle?) {
        val path = intent.extras?.getString(KEY_WEBVIEW_PATH)
        val title = intent.extras?.getString(KEY_WEBVIEW_TITLE)
        initToolbar(title)
        initWebView(path)
    }



    private fun initWebView(path: String?) {
        mWebView = WebView(this)
        val layoutParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        mWebView.layoutParams = layoutParams
        mBinding.flWebviewContain.addView(mWebView)

        val webSetting = mWebView.settings
        webSetting.domStorageEnabled = true
        webSetting.useWideViewPort = true
        webSetting.loadWithOverviewMode = true
        webSetting.javaScriptEnabled = true

        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url == null) {
                    return false
                }
                try {
                    if (url.startsWith("weixin://") || url.startsWith("jianshu://")) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                        return true
                    }
                } catch (e: Exception) {
                    return true
                }
                view?.loadUrl(url)
                return true
            }
        }

        mWebView.webChromeClient =  object :WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                mBinding.progressBar.isVisible = newProgress != 100
                mBinding.progressBar.progress = newProgress
            }
        }

        mWebView.webViewClient = object : WebViewClient() {
            //
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.startsWith("tel:")) {
                    val intent = Intent(Intent.ACTION_VIEW,
                        Uri.parse(url))
                    startActivity(intent)
                    return true
                }
                view.loadUrl(url)
                return true
            }
        }




        mWebView.loadUrl(path ?: "")
    }

    override fun getLayoutId(): Int =  R.layout.activity_webview


    override fun onDestroy() {
        mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
        mWebView.clearHistory()
        (mWebView.parent as ViewGroup).removeView(mWebView)
        mWebView.destroy()
        super.onDestroy()
    }

    private fun initToolbar(title: String?) {
        mBinding.run {
            setToolbarBackIcon(toolbarLayout.ivBack, R.drawable.ic_back_clear)
            title?.let { setToolbarTitle(toolbarLayout.tvTitle, it) }
            toolbarLayout.ivBack.setOnClickListener {
                finish()
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        }
    }



}
