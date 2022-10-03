package dev.thenoveltyseeker.articlestime.data.datasource.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.thenoveltyseeker.articlestime.data.datasource.local.db.dto.ArticlesLocalDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
@Dao
interface ArticlesDao {

    @Query("SELECT * FROM ${DbConstants.POPULAR_ARTICLES_TABLE}")
    fun getPopularArticles(): Flow<List<ArticlesLocalDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticlesLocalDto>)

    @Query("SELECT COUNT(*) FROM ${DbConstants.POPULAR_ARTICLES_TABLE}")
    suspend fun articlesRowCount(): Int

    suspend fun isCacheAvailable() = articlesRowCount() > 0

    @Query("DELETE FROM ${DbConstants.POPULAR_ARTICLES_TABLE}")
    suspend fun deleteAll()
}