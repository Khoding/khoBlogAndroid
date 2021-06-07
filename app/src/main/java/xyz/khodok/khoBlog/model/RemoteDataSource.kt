package xyz.khodok.khoBlog.model


import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import xyz.khodok.khoBlog.model.response.Post
import xyz.khodok.khoBlog.network.RetrofitClient

open class RemoteDataSource {
    open fun getPostAt(id: Int): Observable<Post> {
        return RetrofitClient.currentPostApi
            .getPost(id = id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}