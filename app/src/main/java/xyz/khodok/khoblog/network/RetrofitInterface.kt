package xyz.khodok.khoBlog.network

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import xyz.khodok.khoBlog.model.response.Post

interface RetrofitInterface {

    @GET("post")
    fun getPostList(): Call<List<Post>>
    fun getPost(id: Int): Observable<Post>
}
