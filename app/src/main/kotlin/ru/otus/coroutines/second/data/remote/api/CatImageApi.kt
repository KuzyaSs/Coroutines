package ru.otus.coroutines.second.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import ru.otus.coroutines.second.data.remote.models.CatImage

internal interface CatImageApi {
    @GET("images/search")
    suspend fun getCatImage(): Response<List<CatImage>>
}
