package xyz.khodok.khoblog.network

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import xyz.khodok.khoblog.model.response.Post

interface RetrofitInterface {

    @GET("post")
    fun getPostList(): Call<List<Post>>
    fun getPost(id: Int): Observable<Post>
}
