package dev.thenoveltyseeker.articlestime.domain.usecase

import dev.thenoveltyseeker.articlestime.domain.ArticlesRepository
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularArticlesUseCaseImpl @Inject constructor(
    private val repository: ArticlesRepository
) : PopularArticlesUseCase {

    override suspend fun getPopularArticles(): Flow<List<PopularArticle>> {
        return repository.fetchPopularArticles()
    }
}