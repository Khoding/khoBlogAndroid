package xyz.khodok.domain.model


import com.google.gson.annotations.SerializedName

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
)
