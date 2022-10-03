package dev.thenoveltyseeker.articlestime.data

import dev.thenoveltyseeker.articlestime.MainDispatcherRule
import dev.thenoveltyseeker.articlestime.data.datasource.MockResponse
import dev.thenoveltyseeker.articlestime.data.datasource.local.LocalDataSource
import dev.thenoveltyseeker.articlestime.data.datasource.remote.RemoteDataSource
import dev.thenoveltyseeker.articlestime.data.model.PopularArticlesResponse
import dev.thenoveltyseeker.articlestime.domain.ArticlesRepository
import dev.thenoveltyseeker.articlestime.domain.mapper.ArticlesLocalDtoMapper
import dev.thenoveltyseeker.articlestime.domain.mapper.ArticlesRemoteDtoMapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyAll
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException

@ExperimentalCoroutinesApi
class ArticlesRepositoryImplTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var localDataSource: LocalDataSource

    @MockK
    private lateinit var remoteDataSource: RemoteDataSource

    @MockK
    private lateinit var remoteDtoMapper: ArticlesRemoteDtoMapper

    @MockK
    private lateinit var localDtoMapper: ArticlesLocalDtoMapper

    @MockK
    private lateinit var response: Response<PopularArticlesResponse>

    private lateinit var repository: ArticlesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        repository = ArticlesRepositoryImpl(
            remoteDataSource,
            localDataSource,
            mainDispatcherRule.dispatcher,
            remoteDtoMapper,
            localDtoMapper
        )
    }

    @Test
    fun `when fetchPopularArticles success`() = runTest {
        coEvery {
            remoteDataSource.fetchPopularArticles()
        } returns MockResponse.articlesResponse.articles
        repository.fetchPopularArticles()
        coVerifyAll {
            remoteDataSource.fetchPopularArticles()
            remoteDtoMapper.map(MockResponse.articlesResponse.articles)
            with(localDataSource) {
                deleteCachedItems()
                updateCache(any())
                fetchPopularArticles()
            }
        }
    }

    @Test
    fun `when fetchPopularArticles failed`() = runTest {
        coEvery {
            remoteDataSource.fetchPopularArticles()
        } throws ConnectException()
        coEvery { localDataSource.isCacheAvailable() } returns true
        repository.fetchPopularArticles()
        coVerifyAll {
            with(localDataSource) {
                isCacheAvailable()
                fetchPopularArticles()
            }
        }
        coVerify(exactly = 0) {
            with(localDataSource) {
                deleteCachedItems()
                updateCache(emptyList())
            }
        }
    }

    @Test
    fun `when fetchPopularArticles failed and cacheEmpty throws exception`() = runTest {
        coEvery {
            remoteDataSource.fetchPopularArticles()
        } throws ConnectException()
        coEvery { localDataSource.isCacheAvailable() } returns false
        try {
            repository.fetchPopularArticles()
        } catch (exception: Exception) {
            assertEquals(NO_INTERNET_ERROR, exception.message)
        }
    }

    @Test
    fun `when fetchPopularArticles failed with HttpException`() = runTest {
        coEvery {
            remoteDataSource.fetchPopularArticles()
        } throws HttpException(response)
        coEvery { localDataSource.isCacheAvailable() } returns false
        try {
            repository.fetchPopularArticles()
        } catch (exception: Exception) {
            assertEquals(ARTICLES_UNAVAILABLE_ERROR, exception.message)
        }
    }

    @Test
    fun `when fetchPopularArticles failed with other exceptions`() = runTest {
        coEvery {
            remoteDataSource.fetchPopularArticles()
        } throws Exception(DUMMY_ERROR)
        coEvery { localDataSource.isCacheAvailable() } returns false
        try {
            repository.fetchPopularArticles()
        } catch (exception: Exception) {
            assertEquals(exception.message, DUMMY_ERROR)
        }
    }

    private companion object {
        const val ARTICLES_UNAVAILABLE_ERROR = "Unable to get the popular articles at this time."
        const val NO_INTERNET_ERROR = "Unable to connect, please check your internet connection."
        const val DUMMY_ERROR = "Dummy message"
    }
}