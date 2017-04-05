package com.kuma.kotlinnews

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

import com.kuma.kotlinnews.databinding.ActivityNewsDetailsBinding
import com.kuma.kotlinnews.model.Article

class NewsDetailsActivity : AppCompatActivity() {
    var mBinding: ActivityNewsDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val article = intent.getParcelableExtra<Article>(NEW_INDEX)
        if (article != null)
            updateNewsDetails(article)
        else
            Toast.makeText(this, "Sorry, incorrect article passed", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateNewsDetails(article: Article) {
        mBinding?.activityNewsDetailsWebview?.settings?.javaScriptEnabled = true
        mBinding?.activityNewsDetailsWebview?.setWebViewClient(object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                mBinding?.activityNewsDetailsProgressbar?.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                mBinding?.activityNewsDetailsProgressbar?.visibility = View.GONE
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                mBinding?.activityNewsDetailsProgressbar?.visibility = View.GONE
                Toast.makeText(view?.context, "Error in loading webpage", Toast.LENGTH_SHORT).show()
            }
        })
        mBinding?.activityNewsDetailsWebview?.loadUrl(article.url)
        supportActionBar?.title = article.title
    }

    companion object {
        val NEW_INDEX = "news_index"

        fun launch(context: Context, article: Article) {
            val intent = Intent(context, NewsDetailsActivity::class.java)
            intent.putExtra(NEW_INDEX, article)
            context.startActivity(intent)
        }
    }

}