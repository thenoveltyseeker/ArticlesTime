package dev.thenoveltyseeker.articlestime.presentation.populararticles.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.thenoveltyseeker.articlestime.R
import dev.thenoveltyseeker.articlestime.databinding.ListItemArticleBinding
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle

class ArticlesViewHolder(
    private val binding: ListItemArticleBinding,
    private val articleSelectListener: ArticleSelectListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: PopularArticle) {
        with(binding) {
            tvArticleTitle.text = article.title
            ivArticleImage.load(article.thumbnailUrl) {
                crossfade(true)
                error(R.drawable.broken_image)
            }
            if (!article.date.isNullOrEmpty()) {
                tvDate.text = article.date
            }
            itemView.setOnClickListener {
                articleSelectListener.onArticleSelected(article)
            }
        }
    }
}