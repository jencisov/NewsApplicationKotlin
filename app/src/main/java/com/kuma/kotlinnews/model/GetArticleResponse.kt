package com.kuma.kotlinnews.model

import com.squareup.moshi.Json

/**
 * Created by Jorge.Enciso on 04/04/2017.
 */
data class GetArticleResponse(
        @Json(name = "status") var status: String,
        @Json(name = "source") var source: String,
        @Json(name = "sortBy") var sortBy: String,
        @Json(name = "articles") var articles: List<Article>
)