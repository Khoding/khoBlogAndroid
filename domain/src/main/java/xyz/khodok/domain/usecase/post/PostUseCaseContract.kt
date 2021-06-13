package xyz.khodok.domain.usecase.post

import io.reactivex.Single
import xyz.khodok.domain.model.Post

interface PostUseCaseContract {

    fun getPostById(postId: Int): Single<Post>

}
