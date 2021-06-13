package xyz.khodok.domain.usecase.feed

import io.reactivex.Single
import xyz.khodok.domain.model.Post

interface FeedUseCaseContract {

    fun getAllPosts(): Single<List<Post>>

}
