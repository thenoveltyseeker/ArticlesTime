package dev.thenoveltyseeker.articlestime.data.datasource.local

import dev.thenoveltyseeker.articlestime.MainDispatcherRule
import dev.thenoveltyseeker.articlestime.data.datasource.MockResponse
import dev.thenoveltyseeker.articlestime.data.datasource.local.db.ArticlesDao
import dev.thenoveltyseeker.articlestime.data.datasource.local.db.dto.ArticlesLocalDto
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LocalDataSourceTest {
    @get: Rule
    val dispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var articlesDao: ArticlesDao

    @MockK
    private lateinit var articlesToLocalDtoMapper: ArticlesToLocalDtoMapper

    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        localDataSource = LocalDataSource(articlesDao, articlesToLocalDtoMapper)
    }

    @Test
    fun `when fetchPopularArticles called return articles flow`() = runTest {
        coEvery {
            articlesDao.getPopularArticles()
        } returns MockResponse.emptyLocalDtoFlow
        localDataSource.fetchPopularArticles()
        coVerify {
            articlesDao.getPopularArticles()
        }
    }

    @Test
    fun `when isCacheAvailable called with cached data`() = runTest {
        coEvery { articlesDao.isCacheAvailable() } returns true
        val isCached = localDataSource.isCacheAvailable()
        assertTrue(isCached)
    }

    @Test
    fun `when isCacheAvailable called without cached data`() = runTest {
        coEvery { articlesDao.isCacheAvailable() } returns false
        val isCached = localDataSource.isCacheAvailable()
        assertFalse(isCached)
    }

    @Test
    fun `when updateCache called cache data`() = runTest {
        coEvery {
            articlesDao.insertArticles(any())
        } returns Unit
        coEvery {
            articlesToLocalDtoMapper.map(MockResponse.popularArticlesList)
        } returns MockResponse.popularArticlesLocalDtoList

        localDataSource.updateCache(MockResponse.popularArticlesList)
        val popularArticleSlot = slot<List<PopularArticle>>()
        val localDtoSlot = slot<List<ArticlesLocalDto>>()
        coVerify {
            articlesToLocalDtoMapper.map(capture(popularArticleSlot))
            articlesDao.insertArticles(capture(localDtoSlot))
        }
        assertEquals(MockResponse.popularArticlesList, popularArticleSlot.captured)
        assertEquals(
            MockResponse.popularArticlesLocalDtoList,
            localDtoSlot.captured
        )
    }

    @Test
    fun `when deleteCache called remove data`() = runTest {
        coEvery { articlesDao.deleteAll() } returns Unit
        localDataSource.deleteCachedItems()
        coVerify {
            articlesDao.deleteAll()
        }
    }
}