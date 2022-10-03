package dev.thenoveltyseeker.articlestime.presentation.populararticles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.thenoveltyseeker.articlestime.databinding.FragmentPopularArticlesBinding
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle
import dev.thenoveltyseeker.articlestime.presentation.populararticles.adapter.ArticleSelectListener
import dev.thenoveltyseeker.articlestime.presentation.populararticles.adapter.ArticlesAdapter

class PopularArticlesFragment : Fragment(), ArticleSelectListener {
    private var binding: FragmentPopularArticlesBinding? = null
    private val articlesAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ArticlesAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularArticlesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.rvArticles?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = articlesAdapter
        }
        //ViewModel is scoped to activity context
        lifecycleScope.launchWhenStarted {
            val viewModel = ViewModelProvider(requireActivity())[ArticlesViewModel::class.java]
            viewModel.uiState.collect {
                updateState(it)
            }
        }
    }

    private fun updateState(state: State) {
        when (state) {
            is State.ArticlesSuccess -> {
                shouldShowProgress(isVisible = false)
                articlesAdapter.submitList(state.articles)
            }
            is State.ArticlesError -> {
                shouldShowProgress(isVisible = false)
                Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
            }
            State.Loading -> shouldShowProgress(isVisible = true)
        }
    }

    private fun shouldShowProgress(isVisible: Boolean) {
        binding?.progress?.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun onArticleSelected(article: PopularArticle) {
        val action = PopularArticlesFragmentDirections.actionToArticleDetailsFragment(
            article,
            if (article.date.isNullOrEmpty()) "Article Details" else article.date
        )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}