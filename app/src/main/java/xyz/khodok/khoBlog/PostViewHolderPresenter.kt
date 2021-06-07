package xyz.khodok.khoBlog

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import xyz.khodok.khoBlog.model.RemoteDataSource
import xyz.khodok.khoBlog.model.response.Post

class PostViewHolderPresenter(
    private val viewInterface: PostViewHolderContract.ViewInterface,
    private val dataSource: RemoteDataSource
) : PostViewHolderContract.PresenterInterface {


    private val compositeDisposable = CompositeDisposable()

    val getPostObservable: (Int) -> Observable<Post> =
        { query -> dataSource.getPostAt(query) }

    val observer: DisposableObserver<Post>
        get() = object : DisposableObserver<Post>() {
            override fun onNext(response: Post) {
                viewInterface.displayResult(response)
            }

            override fun onError(e: Throwable) {
                viewInterface.displayError(e.message.toString())
            }

            override fun onComplete() {

            }
        }

    override fun getPost(id: Int) {
        val getPostDisposable = getPostObservable(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer)
        compositeDisposable.add(getPostDisposable)
    }

    override fun stop() {
        compositeDisposable.clear()
    }
}