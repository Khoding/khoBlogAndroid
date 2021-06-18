package xyz.khodok.domain.model


import com.google.gson.annotations.SerializedName
import java.util.*

data class Post(
    @SerializedName("author_name")
    val authorName: String,
    @SerializedName("created_date")
    val createdDate: Date,
    @SerializedName("description")
    val description: String,
    @SerializedName("formatted_markdown")
    val formattedMarkdown: String?,
    @SerializedName("modified_date")
    val modifiedDate: Date,
    @SerializedName("pk")
    val pk: Int,
    @SerializedName("published_date")
    val publishedDate: Date,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("title")
    val title: String
)
