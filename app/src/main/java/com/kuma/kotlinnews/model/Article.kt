package com.kuma.kotlinnews.model

import android.os.Parcel
import android.os.Parcelable
import com.kuma.news.utils.DateUtils
import com.squareup.moshi.Json

/**
 * Created by Jorge.Enciso on 04/04/2017.
 */
data class Article(
        @Json(name = "author") var author: String = "",
        @Json(name = "title") var title: String = "",
        @Json(name = "description") var description: String = "",
        @Json(name = "url") var url: String = "",
        @Json(name = "urlToImage") var urlToImage: String = "",
        @Json(name = "publishedAt") var publishedAt: String = ""
) : Parcelable {
    var value = publishedAt
        get():String {
            return DateUtils.formatNewsApiDate(publishedAt)
        }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Article> = object : Parcelable.Creator<Article> {
            override fun createFromParcel(source: Parcel): Article = Article(source)
            override fun newArray(size: Int): Array<Article?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            dest.writeString(author)
            dest.writeString(title)
            dest.writeString(description)
            dest.writeString(url)
            dest.writeString(urlToImage)
            dest.writeString(publishedAt)
        }
    }

}