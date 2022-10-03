package dev.thenoveltyseeker.articlestime.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.thenoveltyseeker.articlestime.domain.ArticlesRepository
import dev.thenoveltyseeker.articlestime.domain.mapper.ArticlesLocalDtoMapper
import dev.thenoveltyseeker.articlestime.domain.mapper.ArticlesRemoteDtoMapper
import dev.thenoveltyseeker.articlestime.domain.usecase.PopularArticlesUseCase
import dev.thenoveltyseeker.articlestime.domain.usecase.PopularArticlesUseCaseImpl

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Provides
    fun providePopularArticleUseCase(
        repository: ArticlesRepository,
    ): PopularArticlesUseCase {
        return PopularArticlesUseCaseImpl(
            repository
        )
    }

    @Provides
    fun provideRemoteDtoMapper(): ArticlesRemoteDtoMapper {
        return ArticlesRemoteDtoMapper()
    }

    @Provides
    fun provideLocalDtoMapper(): ArticlesLocalDtoMapper {
        return ArticlesLocalDtoMapper()
    }
}