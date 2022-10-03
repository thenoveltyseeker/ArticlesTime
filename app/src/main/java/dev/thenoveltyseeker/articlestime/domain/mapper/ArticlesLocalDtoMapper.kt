package dev.thenoveltyseeker.articlestime.domain.mapper

import dev.thenoveltyseeker.articlestime.data.datasource.local.db.dto.ArticlesLocalDto
import dev.thenoveltyseeker.articlestime.domain.ListDataMapper
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle

class ArticlesLocalDtoMapper : ListDataMapper<ArticlesLocalDto, PopularArticle> {

    override fun map(list: List<ArticlesLocalDto>): List<PopularArticle> {
        return list.map {
            PopularArticle(
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