package dev.thenoveltyseeker.articlestime.data.model

import com.google.gson.annotations.SerializedName

data class PopularArticlesResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("results")
    val articles: List<ArticleResponse>?
)

data class ArticleResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("abstract")
    val details: String,
    @SerializedName("published_date")
    val date: String,
    @SerializedName("byline")
    val byline: String,
    @SerializedName("media")
    val media: List<Media>?
)

data class Media(
    @SerializedName("type")
    val type: String?,
    @SerializedName("media-metadata")
    val metadata: List<MediaMetadata>?
)

data class MediaMetadata(
    @SerializedName("url")
    val mediaUrl: String?,
    @SerializedName("format")
    val format: String?
)
