package xyz.khodok.khoBlog.detail


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import xyz.khodok.khoBlog.R

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
        cityName = intent.getStringExtra("id").toString()
        setupViews()
        appBar.title = cityName
    }

    private fun setupViews() {

    }

}