package xyz.khodok.data.repository.post

import io.reactivex.Single
import xyz.khodok.data.source.post.remote.PostRemoteDataSourceContract
import xyz.khodok.domain.model.*
import xyz.khodok.domain.repository.post.PostRepositoryContract
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject
constructor(private val postRemoteDataSource: PostRemoteDataSourceContract) :
    PostRepositoryContract {

    override fun getAllPosts(): Single<List<Post>> {
        return postRemoteDataSource.getAllPosts()
            .toObservable()
            .flatMapIterable { it }
            .flatMap { post ->
                return@flatMap Single.just(
                    Post(
                        pk = post.pk,
                        title = post.title,
                        description = post.description,
                        slug = post.slug,
                        authorName = post.authorName,
                        createdDate = post.createdDate,
                        modifiedDate = post.modifiedDate,
                        publishedDate = post.publishedDate,
                        formattedMarkdown = post.formattedMarkdown
                    )
                ).toObservable()
            }.toList()
            .flatMap {
                return@flatMap Single.just(it)
            }
    }

    override fun getPostBySlug(postSlug: String): Single<Post> {
        return postRemoteDataSource.getPostBySlug(postSlug)
            .flatMap { post ->
                Single.just(
                    Post(
                        pk = post.pk,
                        title = post.title,
                        description = post.description,
                        slug = post.slug,
                        authorName = post.authorName,
                        createdDate = post.createdDate,
                        modifiedDate = post.modifiedDate,
                        publishedDate = post.publishedDate,
                        formattedMarkdown = post.formattedMarkdown
                    )
                )
            }
    }
}
