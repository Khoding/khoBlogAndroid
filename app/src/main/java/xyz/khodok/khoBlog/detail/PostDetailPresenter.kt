package xyz.khodok.khoBlog.detail


import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import xyz.khodok.khoBlog.model.RemoteDataSource
import xyz.khodok.khoBlog.model.response.Post

class PostDetailPresenter(
    private val viewInterface: PostDetailContract.ViewInterface,
    private val dataSource: RemoteDataSource
) : PostDetailContract.PresenterInterface {

    private val compositeDisposable = CompositeDisposable()

    val getPostObservable: (Int) -> Observable<Post> =
        { query -> dataSource.getPostAt(query) }

    val postObservable: DisposableObserver<Post>
        get() = object : DisposableObserver<Post>() {
            override fun onNext(response: Post) {
                viewInterface.displayPost(response)
            }

            override fun onError(e: Throwable) {
                viewInterface.showError(e.message.toString())
            }

            override fun onComplete() {

            }
        }

    override fun getPost(id: Int) {
        val getPostDisposable = getPostObservable(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(postObservable)
        compositeDisposable.add(getPostDisposable)
    }

    override fun stop() {
        compositeDisposable.clear()
    }
}