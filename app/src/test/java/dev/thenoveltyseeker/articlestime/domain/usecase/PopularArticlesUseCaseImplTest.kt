package dev.thenoveltyseeker.articlestime.domain.usecase

import dev.thenoveltyseeker.articlestime.data.datasource.MockResponse
import dev.thenoveltyseeker.articlestime.domain.ArticlesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.net.ConnectException

@ExperimentalCoroutinesApi
class PopularArticlesUseCaseImplTest {
    @MockK
    private lateinit var articlesRepository: ArticlesRepository

    private lateinit var popularArticlesUseCase: PopularArticlesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        popularArticlesUseCase = PopularArticlesUseCaseImpl(articlesRepository)
    }

    @Test
    fun `when getPopularArticles success return data`() = runTest {
        coEvery {
            articlesRepository.fetchPopularArticles()
        } returns MockResponse.popularArticlesFlow
        val data = popularArticlesUseCase.getPopularArticles()
        coVerify {
            articlesRepository.fetchPopularArticles()
        }
        assertEquals(MockResponse.popularArticlesFlow, data)
    }

    @Test
    fun `when getPopularArticles fails throw exception`() = runTest {
        coEvery {
            articlesRepository.fetchPopularArticles()
        } throws ConnectException()
        try {
            popularArticlesUseCase.getPopularArticles()
        } catch (exception : Exception) {
            assertTrue(exception is ConnectException)
        }
    }
}