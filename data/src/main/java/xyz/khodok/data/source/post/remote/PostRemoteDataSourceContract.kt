package xyz.khodok.data.source.post.remote

import xyz.khodok.domain.model.Post
import io.reactivex.Single

interface PostRemoteDataSourceContract {

    fun getAllPosts(): Single<List<Post>>

    fun getPostById(postId: Int): Single<Post>

}
