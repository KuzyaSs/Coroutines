package ru.otus.coroutines.second.data.remote.data_sources

import ru.otus.coroutines.second.domain.models.Result
import ru.otus.coroutines.second.domain.models.RootError

internal interface CatsRemoteDataSource {
    suspend fun getCatFact(): Result<String, RootError>

    suspend fun getCatImage(): Result<String, RootError>
}
