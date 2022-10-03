package dev.thenoveltyseeker.articlestime.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.thenoveltyseeker.articlestime.data.ArticlesRepositoryImpl
import dev.thenoveltyseeker.articlestime.data.datasource.local.LocalDataSource
import dev.thenoveltyseeker.articlestime.data.datasource.local.db.ArticlesDao
import dev.thenoveltyseeker.articlestime.data.datasource.local.db.ArticlesDatabase
import dev.thenoveltyseeker.articlestime.data.datasource.local.db.DbConstants
import dev.thenoveltyseeker.articlestime.data.datasource.remote.ArticlesApi
import dev.thenoveltyseeker.articlestime.data.datasource.remote.RemoteDataSource
import dev.thenoveltyseeker.articlestime.domain.ArticlesRepository
import dev.thenoveltyseeker.articlestime.domain.mapper.ArticlesLocalDtoMapper
import dev.thenoveltyseeker.articlestime.domain.mapper.ArticlesRemoteDtoMapper
import dev.thenoveltyseeker.articlestime.data.datasource.local.PopularArticlesToLocalDtoMapper
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    fun provideArticlesRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        dispatcher: CoroutineDispatcher,
        articlesRemoteDtoMapper: ArticlesRemoteDtoMapper,
        articlesLocalDtoMapper: ArticlesLocalDtoMapper
    ): ArticlesRepository {
        return ArticlesRepositoryImpl(
            remoteDataSource,
            localDataSource,
            dispatcher,
            articlesRemoteDtoMapper,
            articlesLocalDtoMapper
        )
    }

    @Provides
    fun provideRemoteDataSource(
        articlesApi: ArticlesApi
    ): RemoteDataSource {
        return RemoteDataSource(articlesApi = articlesApi)
    }

    @Provides
    fun provideLocalDataSource(
        articlesDao: ArticlesDao,
        articlesToLocalDtoMapper: PopularArticlesToLocalDtoMapper
    ): LocalDataSource {
        return LocalDataSource(
            articlesDao = articlesDao,
            articlesToLocalDtoMapper = articlesToLocalDtoMapper
        )
    }

    @Provides
    fun provideArticleToLocalDtoMapper(): PopularArticlesToLocalDtoMapper {
        return PopularArticlesToLocalDtoMapper()
    }

    @Provides
    @Singleton
    fun provideRoomDao(
        @ApplicationContext context: Context
    ): ArticlesDao {
        return Room.databaseBuilder(
            context.applicationContext,
            ArticlesDatabase::class.java,
            DbConstants.ARTICLES_DATABASE
        ).fallbackToDestructiveMigration()
            .build()
            .articlesDao()
    }

    @Provides
    @Singleton
    fun provideRemoteService(): ArticlesApi {
        return Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.nytimes.com/")
            .build()
            .create(ArticlesApi::class.java)
    }
}

