package com.kuma.kotlinnews.networking

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Jorge.Enciso on 04/04/2017.
 */
class NewsAPI {

    companion object {
        val newsService: NewsService = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v1/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build().create(NewsService::class.java)
    }

}