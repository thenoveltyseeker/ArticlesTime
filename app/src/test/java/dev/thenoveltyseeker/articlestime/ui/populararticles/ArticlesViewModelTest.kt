package dev.thenoveltyseeker.articlestime.ui.populararticles

import dev.thenoveltyseeker.articlestime.MainDispatcherRule
import dev.thenoveltyseeker.articlestime.data.datasource.MockResponse
import dev.thenoveltyseeker.articlestime.domain.usecase.PopularArticlesUseCase
import dev.thenoveltyseeker.articlestime.presentation.populararticles.ArticlesViewModel
import dev.thenoveltyseeker.articlestime.presentation.populararticles.State
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArticlesViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var useCase: PopularArticlesUseCase

    private lateinit var viewModel: ArticlesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun `when viewModel started state is loading`() = runTest {
        viewModel = ArticlesViewModel(useCase)
        assertEquals(State.Loading, viewModel.uiState.value)
    }

    @Test
    fun `when viewModel created fetch articles called`() = runTest {
        viewModel = ArticlesViewModel(useCase)
        advanceUntilIdle()
        coVerify {
            useCase.getPopularArticles()
        }
    }

    @Test
    fun `when articles fetch success`() = runTest {
        coEvery {
            useCase.getPopularArticles()
        }.returns(MockResponse.popularArticlesFlow)
        viewModel = ArticlesViewModel(useCase)
        advanceUntilIdle()
        coVerify {
            useCase.getPopularArticles()
        }
        assertEquals(
            State.ArticlesSuccess(MockResponse.popularArticlesList),
            viewModel.uiState.value
        )
    }

    @Test
    fun `when articles fetch success with empty data`() = runTest {
        coEvery {
            useCase.getPopularArticles()
        }.returns(MockResponse.emptyListFlow)
        viewModel = ArticlesViewModel(useCase)
        advanceUntilIdle()
        coVerify {
            useCase.getPopularArticles()
        }
        assertEquals(
            State.ArticlesSuccess(MockResponse.emptyPopularArticle),
            viewModel.uiState.value
        )
        assertTrue((viewModel.uiState.value as State.ArticlesSuccess).articles.isEmpty())
    }

    @Test
    fun `when articles fetch failed with exception and cause`() = runTest {
        coEvery {
            useCase.getPopularArticles()
        }.throws(Throwable(CUSTOM_ERROR_MESSAGE))
        viewModel = ArticlesViewModel(useCase)
        advanceUntilIdle()
        assertEquals(
            State.ArticlesError(CUSTOM_ERROR_MESSAGE),
            viewModel.uiState.value
        )
    }

    @Test
    fun `when articles fetch failed without exception cause`() = runTest {
        coEvery {
            useCase.getPopularArticles()
        }.throws(Throwable())
        viewModel = ArticlesViewModel(useCase)
        advanceUntilIdle()
        assertEquals(
            State.ArticlesError(DEFAULT_ERROR_MESSAGE),
            viewModel.uiState.value
        )
    }

    private companion object {
        const val CUSTOM_ERROR_MESSAGE = "Some custom error"
        const val DEFAULT_ERROR_MESSAGE = "Something went wrong"
    }
}