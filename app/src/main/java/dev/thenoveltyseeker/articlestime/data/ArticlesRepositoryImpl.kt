package dev.thenoveltyseeker.articlestime.data

import dev.thenoveltyseeker.articlestime.data.datasource.local.LocalDataSource
import dev.thenoveltyseeker.articlestime.data.datasource.remote.RemoteDataSource
import dev.thenoveltyseeker.articlestime.domain.ArticlesRepository
import dev.thenoveltyseeker.articlestime.domain.mapper.ArticlesLocalDtoMapper
import dev.thenoveltyseeker.articlestime.domain.mapper.ArticlesRemoteDtoMapper
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val coroutineDispatcher: CoroutineDispatcher,
    private val articlesRemoteDtoMapper: ArticlesRemoteDtoMapper,
    private val articlesLocalDtoMapper: ArticlesLocalDtoMapper
) : ArticlesRepository {

    override suspend fun fetchPopularArticles(): Flow<List<PopularArticle>> {
        return withContext(coroutineDispatcher) {
            try {
                fetchPopularArticlesFromRemote()
            } catch (exception: Exception) {
                handleError(exception)
            }
            localDataSource.fetchPopularArticles().map {
                articlesLocalDtoMapper.map(it)
            }
        }
    }

    private suspend fun isCacheEmpty() = !localDataSource.isCacheAvailable()

    private suspend fun fetchPopularArticlesFromRemote() {
        val latestPopularArticles = articlesRemoteDtoMapper.map(
            remoteDataSource.fetchPopularArticles()
        )
        with(localDataSource) {
            deleteCachedItems()
            updateCache(latestPopularArticles)
        }
    }

    private suspend fun handleError(exception: Exception) {
        when (exception) {
            is UnknownHostException,
            is ConnectException -> {
                if (isCacheEmpty())
                    throw Exception(NO_INTERNET_ERROR)
            }
            is HttpException -> {
                if (isCacheEmpty())
                    throw Exception(ARTICLES_UNAVAILABLE_ERROR)
                //Else data will be populated from cache
            }
            else -> throw exception
        }
    }

    private companion object {
        const val ARTICLES_UNAVAILABLE_ERROR = "Unable to get the popular articles at this time."
        const val NO_INTERNET_ERROR = "Unable to connect, please check your internet connection."
    }
}