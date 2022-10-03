package dev.thenoveltyseeker.articlestime.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import dev.thenoveltyseeker.articlestime.R
import dev.thenoveltyseeker.articlestime.databinding.FragmentArticleDetailsBinding
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle

class ArticleDetailsFragment : Fragment() {
    private var binding: FragmentArticleDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val article = ArticleDetailsFragmentArgs.fromBundle(it).article
            bindArticleDetails(article)
        }
    }

    private fun bindArticleDetails(article: PopularArticle) {
        binding?.apply {
            ivArticleImage.load(article.imageUrl) {
                crossfade(true)
                error(R.drawable.broken_image)
            }
            tvArticleTitle.text = article.title
            tvArticleDetail.text = article.details
            tvArticleAuthor.text = article.author
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}