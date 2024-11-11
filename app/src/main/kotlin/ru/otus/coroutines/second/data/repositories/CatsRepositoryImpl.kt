package ru.otus.coroutines.second.data.repositories

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.otus.coroutines.second.data.remote.data_sources.CatsRemoteDataSource
import ru.otus.coroutines.second.domain.models.Result
import ru.otus.coroutines.second.domain.models.RootError
import ru.otus.coroutines.second.domain.repositories.CatsRepository

internal class CatsRepositoryImpl(
    private val catsRemoteDataSource: CatsRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : CatsRepository {
    override suspend fun getCatFact(): Result<String, RootError> = withContext(ioDispatcher) {
        catsRemoteDataSource.getCatFact()
    }

    override suspend fun getCatImage(): Result<String, RootError> = withContext(ioDispatcher) {
        catsRemoteDataSource.getCatImage()
    }
}
