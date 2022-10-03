package dev.thenoveltyseeker.articlestime.presentation.populararticles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import dev.thenoveltyseeker.articlestime.R
import dev.thenoveltyseeker.articlestime.databinding.ListItemArticleBinding
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle

class ArticlesAdapter(
    private val articleSelectListener: ArticleSelectListener
) : ListAdapter<PopularArticle, ArticlesAdapter.ArticlesViewHolder>(ArticleDiffCallback()) {

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
                if(!article.date.isNullOrEmpty()) {
                    tvDate.text = article.date
                }
                itemView.setOnClickListener {
                    articleSelectListener.onArticleSelected(article)
                }
            }
        }
    }

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
            return false
        }

    }
}

interface ArticleSelectListener {
    fun onArticleSelected(article: PopularArticle)
}