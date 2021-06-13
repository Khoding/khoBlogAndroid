package xyz.khodok.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import xyz.khodok.data.service.PostService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun providesPostService(retrofit: Retrofit)
            : PostService = retrofit.create(PostService::class.java)
}
