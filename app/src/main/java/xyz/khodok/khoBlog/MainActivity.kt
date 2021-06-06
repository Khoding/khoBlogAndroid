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
import xyz.khodok.khoBlog.model.response.Post
import xyz.khodok.khoBlog.network.RetrofitClient
import xyz.khodok.khoBlog.network.RetrofitInteface

class MainActivity : AppCompatActivity() {
    private lateinit var postRecyclerView: RecyclerView
    private var adapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
    }

    private fun setupViews() {
        postRecyclerView = findViewById(R.id.post_recycler)

        //initiate the service
        val destinationService = RetrofitClient.buildService(RetrofitInteface::class.java)
        val requestCall = destinationService.getPostList()
        //make network call asynchronously
        requestCall.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    val postList = response.body()!!
                    Log.d("Response", "Postlist size : ${postList.size}")
                    postRecyclerView.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = MainAdapter(postList, this@MainActivity, itemListener)
                    }
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

    /**
     * Listener for clicks on tasks in the ListView.
     */
    internal var itemListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemClick(v: View, position: Int) {

            val post = adapter?.getItemAtPosition(position)

            val intent = Intent(this@MainActivity, PostDetailActivity::class.java)
            intent.putExtra("id", post?.id)
            startActivity(intent)

        }
    }

    interface RecyclerItemListener {
        fun onItemClick(v: View, position: Int)
    }
}
