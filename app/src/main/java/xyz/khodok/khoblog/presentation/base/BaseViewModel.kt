package xyz.khodok.khoblog.presentation.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    var disposable: Disposable? = null

    override fun onCleared() {
        super.onCleared()

        this.disposeDisposables()
    }

    private fun disposeDisposables() {
        this.disposable?.isDisposed?.let {
            if (!it) {
                this.disposable?.dispose()
            }
        }
    }

}
