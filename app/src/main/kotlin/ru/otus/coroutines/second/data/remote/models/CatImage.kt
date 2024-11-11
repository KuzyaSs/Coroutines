package ru.otus.coroutines.second.data.remote.models

import com.google.gson.annotations.SerializedName

internal data class CatImage(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
)
