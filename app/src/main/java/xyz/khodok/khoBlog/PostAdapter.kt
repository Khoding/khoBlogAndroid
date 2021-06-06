package xyz.khodok.khoBlog

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import xyz.khodok.khoBlog.model.response.Post


class PostAdapter(private val postList: List<Post>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count :${postList.size} ")

        return holder.bind(postList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postTitle = itemView.findViewById<TextView>(R.id.post_title)
        var postDescription = itemView.findViewById<TextView>(R.id.post_description)

        fun bind(post: Post) {
            postTitle.text = post.title
            postDescription.text = post.description
        }
    }
}
