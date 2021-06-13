package xyz.khodok.domain.usecase.feed

import io.reactivex.Single
import xyz.khodok.domain.model.Post
import xyz.khodok.domain.repository.post.PostRepositoryContract
import javax.inject.Inject

class FeedUseCase @Inject
constructor(private val postRepository: PostRepositoryContract) : FeedUseCaseContract {

    override fun getAllPosts(): Single<List<Post>> {
        return postRepository.getAllPosts()
    }

}
