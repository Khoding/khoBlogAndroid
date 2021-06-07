package xyz.khodok.khoBlog

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.khodok.khoBlog.detail.PostDetailActivity
import xyz.khodok.khoBlog.model.RemoteDataSource
import xyz.khodok.khoBlog.model.response.Post
import xyz.khodok.khoBlog.network.RetrofitClient
import xyz.khodok.khoBlog.network.RetrofitInterface
import java.util.*

class MainActivity : AppCompatActivity(), PostContract.ViewInterface {

    private lateinit var postRecyclerView: RecyclerView
    private var adapter: MainAdapter? = null

    private var destinationService = RetrofitClient.buildService(RetrofitInterface::class.java)
    private var requestCall = destinationService.getPostList()

    private lateinit var postPresenter: PostContract.PresenterInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPresenter()
        setupViews()
    }

    private fun setupViews() {
        postRecyclerView = findViewById(R.id.post_recycler)
        postRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        requestCall.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {

                if (response.isSuccessful) {
                   val postList = response.body()!!

                    postRecyclerView.setHasFixedSize(true)
                    postRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    postRecyclerView.adapter = MainAdapter(
                            postList,
                            this@MainActivity,
                            itemListener
                        )
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Something went wrong ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong $t", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun setUpPresenter() {
        val dataSource = RemoteDataSource()
        postPresenter = PostPresenter(this, dataSource)
    }

    override fun displayResult(response: Post) {
        adapter?.notifyDataSetChanged()
    }

    override fun displayError(message: String) {
        TODO("Not yet implemented")
    }

    /**
     * Listener for clicks on tasks in the ListView.
     */
    internal var itemListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemClick(v: View, position: Int) {

            val post = adapter?.getItemAtPosition(position)

            Log.d("PostId: ", post.toString())

            val intent = Intent(this@MainActivity, PostDetailActivity::class.java)
            intent.putExtra("postTitle", post?.title)
            startActivity(intent)

        }
    }

    interface RecyclerItemListener {
        fun onItemClick(v: View, position: Int)
    }
}
