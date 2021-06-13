package xyz.khodok.data.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.khodok.domain.model.Post

interface PostService {

    @GET("post")
    fun getAllPosts(): Single<List<Post>>

    @GET("post")
    fun getPostsByUserId(@Query("userId") userId: Int): Single<List<Post>>

    @GET("post/{id}")
    fun getPostBySlug(@Path("id") postSlug: String): Single<Post>

}
