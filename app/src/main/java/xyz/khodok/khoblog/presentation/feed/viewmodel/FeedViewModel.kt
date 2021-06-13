package xyz.khodok.khoblog.presentation.feed.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import xyz.khodok.domain.model.Post
import xyz.khodok.domain.usecase.feed.FeedUseCaseContract
import xyz.khodok.khoblog.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val feedUseCase: FeedUseCaseContract) :
    BaseViewModel() {

    val isError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    private val _feed = MutableLiveData<List<Post>>()
    val feed: LiveData<List<Post>>
        get() = _feed

    fun fetchFeed() {
        isLoading.value = true
        disposable = feedUseCase.getAllPosts()
            .subscribeWith(object : DisposableSingleObserver<List<Post>>() {
                override fun onSuccess(feeds: List<Post>) {
                    _feed.value = feeds
                    isLoading.value = false
                    isError.value = false
                }

                override fun onError(e: Throwable) {
                    isLoading.value = false
                    isError.value = true
                }
            })
    }

}
