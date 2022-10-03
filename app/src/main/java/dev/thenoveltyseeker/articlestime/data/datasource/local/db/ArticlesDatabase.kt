package dev.thenoveltyseeker.articlestime.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.thenoveltyseeker.articlestime.data.datasource.local.db.dto.PopularArticleLocalDto
import dev.thenoveltyseeker.articlestime.domain.model.PopularArticle

@Database(
    entities = [PopularArticleLocalDto::class],
    version = 1,
    exportSchema = false
)
abstract class ArticlesDatabase : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao
}