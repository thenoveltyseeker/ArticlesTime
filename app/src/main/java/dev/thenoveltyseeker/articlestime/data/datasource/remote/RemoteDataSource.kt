package dev.thenoveltyseeker.articlestime.data.datasource.remote

import dev.thenoveltyseeker.articlestime.data.datasource.DataSource
import dev.thenoveltyseeker.articlestime.data.model.ArticleResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val articlesApi: ArticlesApi,
) : DataSource<List<ArticleResponse>?> {

    override suspend fun fetchPopularArticles(): List<ArticleResponse>? {
        val response = articlesApi.getPopularArticles()
        return if (response.status == STATUS_OK) {
            response.articles
        } else {
            emptyList()
        }
    }

    private companion object {
        const val STATUS_OK = "OK"
    }
}

