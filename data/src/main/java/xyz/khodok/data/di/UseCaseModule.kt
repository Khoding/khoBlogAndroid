package xyz.khodok.data.di

import xyz.khodok.domain.repository.post.PostRepositoryContract
import xyz.khodok.domain.usecase.feed.FeedUseCase
import xyz.khodok.domain.usecase.feed.FeedUseCaseContract
import xyz.khodok.domain.usecase.post.PostUseCase
import xyz.khodok.domain.usecase.post.PostUseCaseContract
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun providesFeedUseCasePost(postRepository: PostRepositoryContract)
            : FeedUseCaseContract = FeedUseCase(postRepository)

    @Provides
    @Singleton
    fun providesPostUseCasePost(postRepository: PostRepositoryContract)
            : PostUseCaseContract = PostUseCase(postRepository)

}
