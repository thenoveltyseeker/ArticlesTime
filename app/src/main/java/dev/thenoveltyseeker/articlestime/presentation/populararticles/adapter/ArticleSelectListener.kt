package dev.thenoveltyseeker.articlestime.presentation.populararticles.adapter

import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle

interface ArticleSelectListener {
    fun onArticleSelected(article: PopularArticle)
}