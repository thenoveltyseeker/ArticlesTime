package dev.thenoveltyseeker.articlestime.presentation.populararticles.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import dev.thenoveltyseeker.articlestime.databinding.ListItemArticleBinding
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle

class ArticlesAdapter(
    private val articleSelectListener: ArticleSelectListener
) : ListAdapter<PopularArticle, ArticlesViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val binding = ListItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticlesViewHolder(binding, articleSelectListener)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}