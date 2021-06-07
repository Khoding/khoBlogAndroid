package xyz.khodok.khoBlog

import xyz.khodok.khoBlog.model.response.Post

class PostContract {

    interface PresenterInterface {
        fun getPost(id: Int)
        fun stop()
    }

    interface ViewInterface {
        fun displayResult(response: Post)
        fun displayError(message: String)
    }
}
