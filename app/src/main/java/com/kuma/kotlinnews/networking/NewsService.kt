package com.kuma.kotlinnews.networking

import com.kuma.kotlinnews.model.GetArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Jorge.Enciso on 04/04/2017.
 */
interface NewsService {

    @GET("articles?apiKey=e133f30d39864545b294516559acf35e")
    fun getArticles(
            @Query("source") source: String,
            @Query("sortBy") sortBy: String
    ): Call<GetArticleResponse>

}