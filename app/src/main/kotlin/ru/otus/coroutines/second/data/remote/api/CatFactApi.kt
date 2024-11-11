package ru.otus.coroutines.second.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import ru.otus.coroutines.second.data.remote.models.CatFact

internal interface CatFactApi {
    @GET("fact")
    suspend fun getCatFact(): Response<CatFact>
}
