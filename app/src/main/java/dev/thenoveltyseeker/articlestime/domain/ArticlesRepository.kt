package dev.thenoveltyseeker.articlestime.domain

import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {

    suspend fun fetchPopularArticles(): Flow<List<PopularArticle>>
}