package dev.thenoveltyseeker.articlestime.presentation.populararticles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.thenoveltyseeker.articlestime.domain.ArticlesRepository
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle
import dev.thenoveltyseeker.articlestime.domain.usecase.PopularArticlesUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val articlesUseCase: PopularArticlesUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<State>(State.Loading)
    val uiState: StateFlow<State> get() = _uiState

    init {
        fetchPopularArticles()
    }

    private fun fetchPopularArticles() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _uiState.value = State.ArticlesError(throwable.message ?: SOMETHING_WENT_WRONG)
        }
        viewModelScope.launch(coroutineExceptionHandler) {
            articlesUseCase.getPopularArticles()
                .collect {
                    _uiState.emit(State.ArticlesSuccess(it))
                }
        }
    }

    private companion object {
        const val SOMETHING_WENT_WRONG = "Something went wrong"
    }
}

sealed interface State {
    data class ArticlesSuccess(val articles: List<PopularArticle>) : State
    data class ArticlesError(val errorMessage: String) : State
    object Loading : State
}