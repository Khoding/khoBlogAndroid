package xyz.khodok.domain.model


import com.google.gson.annotations.SerializedName
import java.util.*

data class Post(
    @SerializedName("author")
    val author: Int,
    @SerializedName("body")
    val body: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("published_date")
    val publishedDate: Date,
)
