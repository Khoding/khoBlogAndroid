package xyz.khodok.khoblog.presentation.feed.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import okhttp3.internal.http.toHttpDateString
import xyz.khodok.domain.model.Post
import xyz.khodok.khoblog.databinding.ViewFeedItemBinding

class FeedAdapter(
    private var feed: MutableList<Post>,
    private val feedItemListener: OnFeedClickListener
) :
    ListAdapter<Post, RecyclerView.ViewHolder>(NewsResponseItemDiffCallback()) {

    override fun getItemCount() = feed.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ViewFeedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(feed[position])
    }

    inner class ViewHolder(private val binding: ViewFeedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(feed: Post) {
            with(binding) {
                titleTextView.text = feed.title
                descriptionTextView.text = feed.description
                val id = "Post #" + feed.id.toString()
                idTextView.text = id
                publishedDateTextView.text = feed.publishedDate.toHttpDateString()
                itemView.setOnClickListener {
                    feedItemListener.onFeedClicked(feed)
                }
            }
        }
    }

    class NewsResponseItemDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    fun addToList(feedList: MutableList<Post>) {
        this.feed.clear()
        this.feed.addAll(feedList)
        notifyDataSetChanged()
    }

    interface OnFeedClickListener {
        fun onFeedClicked(feed: Post)
    }
}
