package dev.thenoveltyseeker.articlestime.domain.mapper

import dev.thenoveltyseeker.articlestime.data.model.ArticleResponse
import dev.thenoveltyseeker.articlestime.data.model.Media
import dev.thenoveltyseeker.articlestime.domain.NullableListDataMapper
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle

class ArticlesRemoteDtoMapper : NullableListDataMapper<ArticleResponse, PopularArticle> {

    override fun map(list: List<ArticleResponse>?): List<PopularArticle> {
        return list?.map {
            val (thumbnailUrl, imageUrl) = getMedia(it.media)
            PopularArticle(
                id = it.id,
                title = it.title,
                details = it.details,
                thumbnailUrl = thumbnailUrl,
                imageUrl = imageUrl,
                date = it.date,
                author = it.byline
            )
        } ?: emptyList()
    }

    private fun getMedia(media: List<Media>?): Pair<String?, String?> {
        var thumbnailUrl: String? = null
        var imageUrl: String? = null
        media?.firstOrNull()?.let { item ->
            if (item.type == TYPE_IMAGE) {
                item.metadata?.forEach {
                    if (it.format == FORMAT_THUMBNAIL) {
                        thumbnailUrl = it.mediaUrl
                    } else if (it.format == FORMAT_MEDIUM_IMAGE) {
                        imageUrl = it.mediaUrl
                    }
                }
            }
        }
        return Pair(thumbnailUrl, imageUrl)
    }

    private companion object {
        const val TYPE_IMAGE = "image"
        const val FORMAT_THUMBNAIL = "Standard Thumbnail"
        const val FORMAT_MEDIUM_IMAGE = "mediumThreeByTwo440"
    }
}