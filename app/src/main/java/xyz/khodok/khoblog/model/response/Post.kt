package xyz.khodok.khoblog.model.response


import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("author")
    val author: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("published_date")
    val publishedDate: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("clicks")
    val clicks: Int,
)
