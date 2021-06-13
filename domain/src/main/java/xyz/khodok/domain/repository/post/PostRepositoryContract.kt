package xyz.khodok.domain.repository.post

import io.reactivex.Single
import xyz.khodok.domain.model.Post

interface PostRepositoryContract {
    fun getAllPosts(): Single<List<Post>>

    fun getPostById(postId: Int): Single<Post>
}
