package xyz.khodok.domain.usecase.feed

import xyz.khodok.domain.model.Post
import io.reactivex.Single

interface FeedUseCaseContract {

    fun getAllPosts() : Single<List<Post>>

}
