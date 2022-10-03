package dev.thenoveltyseeker.articlestime.data.datasource.local

import dev.thenoveltyseeker.articlestime.data.datasource.DataSource
import dev.thenoveltyseeker.articlestime.data.datasource.local.db.ArticlesDao
import dev.thenoveltyseeker.articlestime.data.datasource.local.db.dto.PopularArticleLocalDto
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val articlesDao: ArticlesDao,
    private val articlesToLocalDtoMapper: PopularArticlesToLocalDtoMapper
) : DataSource<Flow<List<PopularArticleLocalDto>>> {

    override suspend fun fetchPopularArticles() = articlesDao.getPopularArticles()

    suspend fun isCacheAvailable() = articlesDao.isCacheAvailable()

    suspend fun updateCache(latestPopularArticles: List<PopularArticle>) {
        articlesDao.insertArticles(
            articlesToLocalDtoMapper.map(latestPopularArticles)
        )
    }

    suspend fun deleteCachedItems() {
        articlesDao.deleteAll()
    }
}