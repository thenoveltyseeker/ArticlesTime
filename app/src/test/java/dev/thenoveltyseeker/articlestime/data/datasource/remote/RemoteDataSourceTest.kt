package dev.thenoveltyseeker.articlestime.data.datasource.remote

import dev.thenoveltyseeker.articlestime.MainDispatcherRule
import dev.thenoveltyseeker.articlestime.data.datasource.MockResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class RemoteDataSourceTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var articlesApi: ArticlesApi

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        remoteDataSource = RemoteDataSource(articlesApi)
    }

    @Test
    fun `when fetchPopularArticles success return data`() = runTest {
        coEvery {
            articlesApi.getPopularArticles()
        } returns MockResponse.articlesResponse

        val articles = remoteDataSource.fetchPopularArticles()
        coVerify {
            articlesApi.getPopularArticles()
        }
        assertEquals(MockResponse.articlesResponse.articles, articles)
    }

    @Test
    fun `when fetchPopularArticles success with different status return emptyList`() = runTest {
        coEvery {
            articlesApi.getPopularArticles()
        } returns MockResponse.articlesFailedResponse

        val articles = remoteDataSource.fetchPopularArticles()
        coVerify {
            articlesApi.getPopularArticles()
        }
        assertTrue(articles?.isEmpty() == true)
    }

    @Test
    fun `when fetchPopularArticles failure throws exception`() = runTest {
        coEvery {
            articlesApi.getPopularArticles()
        } throws UnknownHostException()
        try {
            remoteDataSource.fetchPopularArticles()
        } catch (exception: Exception) {
            assertTrue(exception is UnknownHostException)
        }
    }
}