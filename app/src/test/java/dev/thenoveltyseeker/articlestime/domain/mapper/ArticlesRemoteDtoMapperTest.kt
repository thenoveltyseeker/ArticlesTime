package dev.thenoveltyseeker.articlestime.domain.mapper

import dev.thenoveltyseeker.articlestime.data.datasource.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ArticlesRemoteDtoMapperTest {
    private lateinit var articlesRemoteDtoMapper: ArticlesRemoteDtoMapper

    @Before
    fun setUp() {
        articlesRemoteDtoMapper = ArticlesRemoteDtoMapper()
    }

    @Test
    fun `when map called on LocalDto return articles`() {
        val mockData = MockResponse.articlesResponse.articles
        val articles = articlesRemoteDtoMapper.map(mockData)

        assertTrue(articles.size == mockData?.size)
        with(articles.first()) {
            val expected = mockData?.first()
            if (expected != null) {
                assertEquals(expected.id, id)
                assertEquals(expected.title, title)
                assertEquals(expected.details, details)
                assertEquals(
                    expected.media?.get(0)?.metadata?.get(0)?.mediaUrl,
                    thumbnailUrl
                )
                assertEquals(
                    expected.media?.get(0)?.metadata?.get(1)?.mediaUrl,
                    imageUrl
                )
                assertEquals(expected.date, date)
                assertEquals(expected.byline, author)
            }
        }
    }
}