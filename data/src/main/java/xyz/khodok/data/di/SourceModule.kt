package xyz.khodok.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.khodok.data.scheduler.BaseSchedulerProvider
import xyz.khodok.data.service.PostService
import xyz.khodok.data.source.post.remote.PostRemoteDataSource
import xyz.khodok.data.source.post.remote.PostRemoteDataSourceContract
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SourceModule {

    @Provides
    @Singleton
    fun providesPostRemoteDataSource(
        postService: PostService,
        schedulerProvider: BaseSchedulerProvider
    )
            : PostRemoteDataSourceContract = PostRemoteDataSource(postService, schedulerProvider)
}
