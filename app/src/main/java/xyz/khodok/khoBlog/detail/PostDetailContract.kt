package xyz.khodok.khoBlog.detail

import xyz.khodok.khoBlog.model.response.Post

class PostDetailContract {

    interface PresenterInterface {
        fun getPost(id: Int)
        fun stop()
    }

    interface ViewInterface {
        fun displayPost(response: Post)
        fun showError(message: String)
    }
}