package xyz.khodok.data.source.post.remote

import io.reactivex.Single
import xyz.khodok.data.scheduler.BaseSchedulerProvider
import xyz.khodok.data.service.PostService
import xyz.khodok.domain.model.Post
import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(
    private val postService: PostService,
    private val schedulerProvider: BaseSchedulerProvider
) : PostRemoteDataSourceContract {

    override fun getAllPosts(): Single<List<Post>> {
        return postService.getAllPosts()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }

    override fun getPostById(postId: Int): Single<Post> {
        return postService.getPostById(postId)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }

}
