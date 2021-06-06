package xyz.khodok.khoBlog.network

import retrofit2.Call
import retrofit2.http.GET
import xyz.khodok.khoBlog.model.response.Post

interface RetrofitInterface {

    @GET("post")
    fun getPostList(): Call<List<Post>>
    fun getPostWithId(id: Int): Post
    fun getPost(slug: String): Post
}
