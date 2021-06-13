package xyz.khodok.khoblog.presentation.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import xyz.khodok.domain.model.Post
import xyz.khodok.domain.usecase.post.PostUseCaseContract
import xyz.khodok.khoblog.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(private val postUseCase: PostUseCaseContract) :
    BaseViewModel() {

    val isError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    private val _post = MutableLiveData<Post>()
    val post: LiveData<Post>
        get() = _post

    fun fetchDetailPost(postId: Int) {
        isLoading.value = true
        disposable = postUseCase.getPostById(postId)
            .subscribeWith(object : DisposableSingleObserver<Post>() {
                override fun onSuccess(postDetail: Post) {
                    _post.value = postDetail
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
