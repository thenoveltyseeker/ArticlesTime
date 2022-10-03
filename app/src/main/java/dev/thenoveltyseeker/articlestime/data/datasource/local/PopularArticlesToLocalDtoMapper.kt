package dev.thenoveltyseeker.articlestime.data.datasource.local

import dev.thenoveltyseeker.articlestime.data.datasource.local.db.dto.PopularArticleLocalDto
import dev.thenoveltyseeker.articlestime.domain.ListDataMapper
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle

class PopularArticlesToLocalDtoMapper : ListDataMapper<PopularArticle, PopularArticleLocalDto> {

    override fun map(list: List<PopularArticle>): List<PopularArticleLocalDto> {
        return list.map {
            PopularArticleLocalDto(
                id = it.id,
                title = it.title,
                details = it.details,
                thumbnailUrl = it.thumbnailUrl,
                imageUrl = it.imageUrl,
                date = it.date,
                author = it.author
            )
        }
    }
}