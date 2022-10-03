package dev.thenoveltyseeker.articlestime.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopularArticle(
    val id: String,
    val title: String,
    val details: String,
    val thumbnailUrl: String?,
    val imageUrl: String?,
    val date: String?,
    val author: String?
) : Parcelable