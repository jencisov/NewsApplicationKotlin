package com.kuma.kotlinnews

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.firebase.analytics.FirebaseAnalytics
import com.kuma.kotlinnews.databinding.ItemHomeNewsBinding
import com.kuma.kotlinnews.model.Article

/**
 * Created by Jorge.Enciso on 04/04/2017.
 */
class HomeNewsAdapter(var articleList: List<Article>?) :
        RecyclerView.Adapter<HomeNewsAdapter.HomeNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeNewsViewHolder {
        val binding: ItemHomeNewsBinding = ItemHomeNewsBinding.inflate(
                LayoutInflater.from(parent?.context), parent, false)
        return HomeNewsViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: HomeNewsViewHolder, position: Int) {
        val article: Article = articleList!![position]
        val root: View = holder.itemHomeNewsBinding.root

        bindNews(holder, article, root)
    }

    override fun getItemCount(): Int {
        return articleList!!.size
    }

    class HomeNewsViewHolder(itemView: View?, val itemHomeNewsBinding: ItemHomeNewsBinding) :
            RecyclerView.ViewHolder(itemView)

    private fun bindNews(homeNewsViewHolder: HomeNewsViewHolder, article: Article, root: View) {
        Glide.with(root.context)
                .load(article.urlToImage)
                .centerCrop()
                .into(homeNewsViewHolder.itemHomeNewsBinding.cardNewsImage)
        homeNewsViewHolder.itemHomeNewsBinding.setVariable(BR.article, article)
        homeNewsViewHolder.itemHomeNewsBinding.executePendingBindings()
        homeNewsViewHolder.itemHomeNewsBinding.root.setOnClickListener {
            view ->
            onItemClick(article, view)
        }
    }

    private fun onItemClick(article: Article, view: View) {
        val firebaseAnalytics = FirebaseAnalytics.getInstance(view.context)
        val bundle = Bundle()
        bundle.putString("index", article.title)
        firebaseAnalytics.logEvent("cardClicked", bundle)
        NewsDetailsActivity.launch(view.context, article)
    }

}