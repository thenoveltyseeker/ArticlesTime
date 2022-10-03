package dev.thenoveltyseeker.articlestime.data.datasource

import dev.thenoveltyseeker.articlestime.data.datasource.local.db.dto.ArticlesLocalDto
import dev.thenoveltyseeker.articlestime.data.model.ArticleResponse
import dev.thenoveltyseeker.articlestime.data.model.Media
import dev.thenoveltyseeker.articlestime.data.model.MediaMetadata
import dev.thenoveltyseeker.articlestime.data.model.PopularArticlesResponse
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow

object MockResponse {
    private const val TYPE_IMAGE = "image"
    private const val FORMAT_THUMBNAIL = "Standard Thumbnail"
    private const val FORMAT_MEDIUM_IMAGE = "mediumThreeByTwo440"

    val emptyPopularArticle = emptyList<PopularArticle>()
    val emptyFlow = emptyFlow<List<PopularArticle>>()
    val emptyLocalDtoFlow = emptyFlow<List<ArticlesLocalDto>>()
    val emptyListFlow = flow { emit(emptyPopularArticle) }

    val popularArticlesList = listOf(
        PopularArticle(
            id = "1",
            title = "Article 1",
            details = "Article 1 abstract",
            thumbnailUrl = "http://thumbnail-1.png",
            imageUrl = "http://image-1.png",
            date = "02-10-22",
            author = "Ben"
        ),
        PopularArticle(
            id = "2",
            title = "Article 2",
            details = "Article 2 abstract",
            thumbnailUrl = "http://thumbnail-2.png",
            imageUrl = "http://image-2.png",
            date = "03-10-22",
            author = "John"
        )
    )

    val popularArticlesFlow = flow {
        emit(popularArticlesList)
    }

    val articlesResponse = PopularArticlesResponse(
        status = "OK",
        articles = listOf(
            ArticleResponse(
                id = "1",
                title = "Article 1",
                details = "Article 1 abstract",
                date = "02-10-22",
                byline = "Ben",
                media = listOf(
                    Media(
                        type = TYPE_IMAGE,
                        listOf(
                            MediaMetadata(
                                mediaUrl = "http://thumbnail-1.png",
                                format = FORMAT_THUMBNAIL
                            ),
                            MediaMetadata(
                                mediaUrl = "http://image-1.png",
                                format = FORMAT_MEDIUM_IMAGE
                            )
                        )
                    )
                )
            ),
            ArticleResponse(
                id = "2",
                title = "Article 2",
                details = "Article 2 abstract",
                date = "03-10-22",
                byline = "John",
                media = listOf(
                    Media(
                        type = TYPE_IMAGE,
                        listOf(
                            MediaMetadata(
                                mediaUrl = "http://thumbnail-2.png",
                                format = FORMAT_THUMBNAIL
                            ),
                            MediaMetadata(
                                mediaUrl = "http://image-2.png",
                                format = FORMAT_MEDIUM_IMAGE
                            )
                        )
                    )
                )
            )
        )
    )
    val popularArticlesLocalDtoList = listOf(
        ArticlesLocalDto(
            id = "1",
            title = "Article 1",
            details = "Article 1 abstract",
            thumbnailUrl = "http://thumbnail-1.png",
            imageUrl = "http://image-1.png",
            date = "02-10-22",
            author = "Ben"
        ),
        ArticlesLocalDto(
            id = "2",
            title = "Article 2",
            details = "Article 2 abstract",
            thumbnailUrl = "http://thumbnail-2.png",
            imageUrl = "http://image-2.png",
            date = "03-10-22",
            author = "John"
        )
    )

    val articlesFailedResponse = PopularArticlesResponse(
        status = "Failed",
        emptyList()
    )
}