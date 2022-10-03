package dev.thenoveltyseeker.articlestime.domain.usecase

import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle
import kotlinx.coroutines.flow.Flow

interface PopularArticlesUseCase {

    suspend fun getPopularArticles() : Flow<List<PopularArticle>>
}