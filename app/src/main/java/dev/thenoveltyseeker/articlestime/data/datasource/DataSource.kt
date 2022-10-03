package dev.thenoveltyseeker.articlestime.data.datasource

interface DataSource<T> {

    suspend fun fetchPopularArticles(): T
}