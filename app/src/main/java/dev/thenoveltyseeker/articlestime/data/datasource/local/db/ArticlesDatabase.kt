package dev.thenoveltyseeker.articlestime.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.thenoveltyseeker.articlestime.data.datasource.local.db.dto.ArticlesLocalDto

@Database(
    entities = [ArticlesLocalDto::class],
    version = 1,
    exportSchema = false
)
abstract class ArticlesDatabase : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao
}