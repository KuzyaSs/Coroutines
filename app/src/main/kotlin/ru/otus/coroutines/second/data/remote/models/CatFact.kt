package ru.otus.coroutines.second.data.remote.models

import com.google.gson.annotations.SerializedName

internal data class CatFact(
    @SerializedName("fact")
    val fact: String,
    @SerializedName("length")
    val length: Int,
)
