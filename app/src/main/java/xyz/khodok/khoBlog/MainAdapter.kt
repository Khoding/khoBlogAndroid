package xyz.khodok.khoBlog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import xyz.khodok.khoBlog.model.RemoteDataSource
import xyz.khodok.khoBlog.model.response.Post

class MainAdapter(
    internal var postList: List<Post>,
    internal var context: Context,
    var listener: MainActivity.RecyclerItemListener
) : RecyclerView.Adapter<MainAdapter.PostHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false)

        val viewHolder = PostHolder(v)
        v.setOnClickListener { v -> listener.onItemClick(v, viewHolder.adapterPosition) }
        return viewHolder
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.locationNameTextView.text = postList[position].title
        holder.descriptionTextView.text = postList[position].description
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    fun getItemAtPosition(pos: Int): Post {
        return postList[pos]
    }

    inner class PostHolder(v: View) : RecyclerView.ViewHolder(v),
        PostViewHolderContract.ViewInterface {
        private lateinit var postViewViewHolderContract: PostViewHolderContract.PresenterInterface

        private fun setUpPresenter() {
            val dataSource = RemoteDataSource()
            postViewViewHolderContract = PostViewHolderPresenter(this, dataSource)
        }

        internal var locationNameTextView: TextView = v.findViewById(R.id.post_title)
        internal var descriptionTextView: TextView = v.findViewById(R.id.post_description)

        override fun displayResult(response: Post) {
            TODO("Not yet implemented")
        }

        override fun displayError(message: String) {
            TODO("Not yet implemented")
        }

    }
}
