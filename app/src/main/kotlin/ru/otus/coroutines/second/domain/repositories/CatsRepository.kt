package ru.otus.coroutines.second.domain.repositories

import ru.otus.coroutines.second.domain.models.Result
import ru.otus.coroutines.second.domain.models.RootError

interface CatsRepository {
    suspend fun getCatFact(): Result<String, RootError>

    suspend fun getCatImage(): Result<String, RootError>
}
