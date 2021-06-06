package xyz.khodok.khoBlog.network

import retrofit2.Call
import retrofit2.http.GET
import xyz.khodok.khoBlog.model.response.Post

interface RetrofitInteface {

    @GET("post")
    fun getPostList(): Call<List<Post>>
}
