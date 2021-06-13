package xyz.khodok.domain.usecase.post

import io.reactivex.Single
import xyz.khodok.domain.model.Post

interface PostUseCaseContract {

    fun getPostBySlug(postSlug: String): Single<Post>

}
