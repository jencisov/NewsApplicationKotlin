package com.kuma.kotlinnews

import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kuma.kotlinnews.databinding.ActivityMainBinding
import com.kuma.kotlinnews.model.GetArticleResponse
import com.kuma.kotlinnews.networking.NewsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var mBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding?.activityMainRecyclerview?.layoutManager = LinearLayoutManager(this)

        NewsAPI.newsService.getArticles("espn", "top").enqueue(object : Callback<GetArticleResponse> {
            override fun onResponse(call: Call<GetArticleResponse>?, response: Response<GetArticleResponse>?) {
                mBinding?.activityMainProgressbar?.visibility = View.GONE
                handleResponse(response)
                showNewApiSnack()
            }

            override fun onFailure(call: Call<GetArticleResponse>?, t: Throwable) {
                mBinding?.activityMainProgressbar?.visibility = View.GONE
            }
        })
    }

    fun handleResponse(response: Response<GetArticleResponse>?) {
        val getArticleResponse: GetArticleResponse = response!!.body()
        val homeNewsAdapter = HomeNewsAdapter(getArticleResponse.articles)
        mBinding?.activityMainRecyclerview?.adapter = homeNewsAdapter
    }

    private fun showNewApiSnack() {
        Snackbar.make(mBinding!!.root, "Powered by NewsApi.org", Snackbar.LENGTH_SHORT)
                .setAction("Visit") { loadNewsApiWebsite() }.show()
    }

    private fun loadNewsApiWebsite() {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://newsapi.org")))
    }

}