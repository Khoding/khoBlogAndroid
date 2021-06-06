package xyz.khodok.khoBlog.detail


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.khodok.khoBlog.MainAdapter
import xyz.khodok.khoBlog.R
import xyz.khodok.khoBlog.model.response.Post
import xyz.khodok.khoBlog.network.RetrofitClient
import xyz.khodok.khoBlog.network.RetrofitInterface

class PostDetailActivity : AppCompatActivity() {
    private lateinit var cityName: String

    //Views
    private lateinit var cityTextView: TextView
    private lateinit var currentDescriptionTextView: TextView
    private lateinit var appBar: androidx.appcompat.widget.Toolbar

    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_item)
        cityName = intent.getStringExtra("title").toString()
        setupViews()
        appBar.title = cityName
    }

    private fun setupViews() {

    }

}