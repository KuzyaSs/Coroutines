package ru.otus.coroutines.second.data.remote.data_sources

import android.util.Log
import ru.otus.coroutines.second.data.remote.api.CatFactApi
import ru.otus.coroutines.second.data.remote.api.CatImageApi
import ru.otus.coroutines.second.domain.models.CoreError
import ru.otus.coroutines.second.domain.models.Result
import ru.otus.coroutines.second.domain.models.RootError
import java.net.SocketTimeoutException

internal class CatsRemoteDataSourceImpl(
    private val catFactApi: CatFactApi,
    private val catImageApi: CatImageApi,
) : CatsRemoteDataSource {
    override suspend fun getCatFact(): Result<String, RootError> {
        try {
            val response = catFactApi.getCatFact()
            if (response.isSuccessful) {
                response.body()?.let { catFact ->
                    return Result.Success(data = catFact.fact)
                }
            }
            Log.d(TAG, "getCatFact | Error: ${response.errorBody()?.source()}")
            return Result.Error(error = CoreError.CONNECTION_ERROR)
        } catch (exception: Exception) {
            Log.d(TAG, "getCatFact | Exception: ${exception.message}")
            return when (exception) {
                is SocketTimeoutException -> Result.Error(error = CoreError.TIMEOUT_ERROR)
                else -> Result.Error(error = CoreError.CONNECTION_ERROR)
            }
        }
    }

    override suspend fun getCatImage(): Result<String, RootError> {
        try {
            val response = catImageApi.getCatImage()
            if (response.isSuccessful) {
                response.body()?.firstOrNull()?.let { catImage ->
                    return Result.Success(data = catImage.url)
                }
            }
            Log.d(TAG, "getCatImage | Error: ${response.errorBody()?.source()}")
            return Result.Error(error = CoreError.CONNECTION_ERROR)
        } catch (exception: Exception) {
            Log.d(TAG, "getCatImage | Exception: ${exception.message}")
            return when (exception) {
                is SocketTimeoutException -> Result.Error(error = CoreError.TIMEOUT_ERROR)
                else -> Result.Error(error = CoreError.CONNECTION_ERROR)
            }
        }
    }

    companion object {
        private const val TAG = "CatsRemoteDataSourceImpl"
    }
}
