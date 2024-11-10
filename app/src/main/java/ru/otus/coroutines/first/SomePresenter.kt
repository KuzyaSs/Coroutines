package ru.otus.coroutines.first

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

internal class SomePresenter(
    private val blockingRepository: BlockingRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
) {
    private val _result = MutableStateFlow<Result?>(null)
    val result = _result.asStateFlow()

    private val scope = CoroutineScope(SupervisorJob() + mainDispatcher)

    /**
     * 1. Реализуйте получение данных из метода BlockingRepository#getHeavyData
     * 2. Если BlockingRepository#getHeavyData не ответит в течение 5 секунд, необходимо
     * заэмитить в LiveData/StateFlow объект Error
     * 3. При успешном сценарии пробросьте в в LiveData/StateFlow объект Success
     */
    fun populateHeavyData() = scope.launch {
        try {
            withTimeout(GET_HEAVY_DATA_TIMEOUT) {
                withContext(ioDispatcher) {
                    blockingRepository.getHeavyData()
                }
            }
            _result.value = Result.Success
        } catch (exception: Exception) {
            _result.value = Result.Error(exception = exception)
        }
    }

    companion object {
        @VisibleForTesting
        internal const val GET_HEAVY_DATA_TIMEOUT = 5000L
    }
}
