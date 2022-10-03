package dev.thenoveltyseeker.articlestime.presentation.populararticles.adapter

import androidx.recyclerview.widget.DiffUtil
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle

class ArticleDiffCallback : DiffUtil.ItemCallback<PopularArticle>() {

    override fun areItemsTheSame(
        oldItem: PopularArticle,
        newItem: PopularArticle
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PopularArticle,
        newItem: PopularArticle
    ): Boolean {
        return oldItem == newItem
    }

}