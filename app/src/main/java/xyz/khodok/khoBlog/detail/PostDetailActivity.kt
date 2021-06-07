package xyz.khodok.khoBlog.detail


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import xyz.khodok.khoBlog.R
import xyz.khodok.khoBlog.model.RemoteDataSource
import xyz.khodok.khoBlog.model.response.Post

class PostDetailActivity : AppCompatActivity(), PostDetailContract.ViewInterface {
    private lateinit var postTitle: String

    //Views
    private lateinit var postTitleTextView: TextView
    private lateinit var postDescriptionTextView: TextView

    //Presenter
    private lateinit var postDetailPresenter: PostDetailContract.PresenterInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_item)
        postTitle = intent.getStringExtra("title").toString()
        setupPresenter()
        setupViews()
    }

    private fun setupPresenter() {
        val dataSource = RemoteDataSource()
        postDetailPresenter = PostDetailPresenter(this, dataSource)
    }

    private fun setupViews() {
        postTitleTextView = findViewById(R.id.post_title)
        postDescriptionTextView = findViewById(R.id.post_description)
    }

    override fun displayPost(response: Post) {
        postTitleTextView.text = response.title
        postDescriptionTextView.text = response.description
    }

    override fun showError(message: String) {
        TODO("Not yet implemented")
    }
}
