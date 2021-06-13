package xyz.khodok.data.di

import xyz.khodok.data.repository.post.PostRepository
import xyz.khodok.data.source.post.remote.PostRemoteDataSourceContract
import xyz.khodok.domain.repository.post.PostRepositoryContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesPostRepository(postRemoteDataSource: PostRemoteDataSourceContract)
            : PostRepositoryContract = PostRepository(postRemoteDataSource)
}
