package xyz.khodok.data.source.post.remote

import io.reactivex.Single
import xyz.khodok.domain.model.Post

interface PostRemoteDataSourceContract {

    fun getAllPosts(): Single<List<Post>>

    fun getPostBySlug(postSlug: String): Single<Post>

}
