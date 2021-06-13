package xyz.khodok.domain.usecase.post

import xyz.khodok.domain.model.Post
import io.reactivex.Single

interface PostUseCaseContract {

    fun getPostById(postId: Int): Single<Post>

}
