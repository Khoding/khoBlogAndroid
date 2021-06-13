package xyz.khodok.domain.usecase.post

import xyz.khodok.domain.repository.post.PostRepositoryContract
import javax.inject.Inject

class PostUseCase @Inject constructor(private val postRepository: PostRepositoryContract) :
    PostUseCaseContract {

    override fun getPostBySlug(postSlug: String) = postRepository.getPostBySlug(postSlug)

}
