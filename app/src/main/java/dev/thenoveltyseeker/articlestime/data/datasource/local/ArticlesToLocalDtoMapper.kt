package dev.thenoveltyseeker.articlestime.data.datasource.local

import dev.thenoveltyseeker.articlestime.data.datasource.local.db.dto.ArticlesLocalDto
import dev.thenoveltyseeker.articlestime.domain.ListDataMapper
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle

class ArticlesToLocalDtoMapper : ListDataMapper<PopularArticle, ArticlesLocalDto> {

    override fun map(list: List<PopularArticle>): List<ArticlesLocalDto> {
        return list.map {
            ArticlesLocalDto(
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