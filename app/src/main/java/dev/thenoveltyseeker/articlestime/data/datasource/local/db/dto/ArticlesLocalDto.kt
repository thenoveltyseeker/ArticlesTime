package dev.thenoveltyseeker.articlestime.data.datasource.local.db.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.thenoveltyseeker.articlestime.data.datasource.local.db.DbConstants

@Entity(tableName = DbConstants.POPULAR_ARTICLES_TABLE)
data class ArticlesLocalDto(
    @PrimaryKey
    val id: String,
    val title: String,
    val details: String,
    val thumbnailUrl: String?,
    val imageUrl: String?,
    val date: String?,
    val author: String?
)