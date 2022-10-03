package dev.thenoveltyseeker.articlestime.data.datasource.local

import dev.thenoveltyseeker.articlestime.data.datasource.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ArticlesToLocalDtoMapperTest {
    private lateinit var articlesToLocalDtoMapper: ArticlesToLocalDtoMapper

    @Before
    fun setUp() {
        articlesToLocalDtoMapper = ArticlesToLocalDtoMapper()
    }

    @Test
    fun `when map called on articles list return LocalDto list`() {
        val mockData = MockResponse.popularArticlesList
        val localDto = articlesToLocalDtoMapper.map(mockData)

        assertTrue(localDto.size == mockData.size)
        with(localDto.first()) {
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