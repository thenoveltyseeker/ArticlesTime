package dev.thenoveltyseeker.articlestime.domain.mapper

import dev.thenoveltyseeker.articlestime.data.datasource.MockResponse
import dev.thenoveltyseeker.articlestime.data.datasource.local.PopularArticlesToLocalDtoMapper
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ArticlesLocalDtoMapperTest {
    private lateinit var articlesLocalDtoMapper: ArticlesLocalDtoMapper

    @Before
    fun setUp() {
        articlesLocalDtoMapper = ArticlesLocalDtoMapper()
    }

    @Test
    fun `when map called on LocalDto return articles`() {
        val mockData = MockResponse.popularArticlesLocalDtoList
        val articles = articlesLocalDtoMapper.map(mockData)

        assertTrue(articles.size == mockData.size)
        with(articles.first()) {
            val expected = mockData.first()
            assertEquals(expected.id, id)
            assertEquals(expected.title, title)
            assertEquals(expected.details, details)
            assertEquals(expected.thumbnailUrl, thumbnailUrl)
            assertEquals(expected.imageUrl, imageUrl)
            assertEquals(expected.date, date)
            assertEquals(expected.author, author)
        }
    }
}