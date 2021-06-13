package xyz.khodok.khoblog.model


import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import xyz.khodok.khoblog.model.response.Post
import xyz.khodok.khoblog.network.RetrofitClient

open class RemoteDataSource {
    open fun getPostAt(id: Int): Observable<Post> {
        return RetrofitClient.currentPostApi
            .getPost(id = id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}