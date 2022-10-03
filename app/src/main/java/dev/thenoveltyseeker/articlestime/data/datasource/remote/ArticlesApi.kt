package dev.thenoveltyseeker.articlestime.data.datasource.remote

import dev.thenoveltyseeker.articlestime.data.model.PopularArticlesResponse
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ArticlesApi {

    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=tlWTJxkXBajG0ApKAH0215A28EGl1OSG")
    suspend fun getPopularArticles(): PopularArticlesResponse
}