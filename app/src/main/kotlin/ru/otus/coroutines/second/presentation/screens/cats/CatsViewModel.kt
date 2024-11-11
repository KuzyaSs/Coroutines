package ru.otus.coroutines.second.presentation.screens.cats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.otus.coroutines.second.domain.repositories.CatsRepository
import ru.otus.coroutines.second.domain.models.Result

internal class CatsViewModel(
    private val catsRepository: CatsRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(CatsState())
    val state = _state.asStateFlow()

    init {
        populateCats()
    }

    fun onAction(action: CatsAction) {
        when (action) {
            CatsAction.OnNextClicked -> populateCats()
            CatsAction.OnRetryClicked -> populateCats()
            CatsAction.OnErrorShown -> dismissError()
        }
    }

    /**
     * Реализуйте функцию populateCats следующим образом:
     * 1. Реализуйте 2 параллельных http запроса на ендпоинты https://api.thecatapi.com/v1/images/search и https://catfact.ninja/fact
     * 2. Обработайте возможные исключения:
     *    а) если выпал SocketTimeoutException - повторите запрос, количество попыток = 3,покажите Toast с текстом "Something went wrong"
     *    b) если выпало другое исключение - покажите Toast с текстом "Something went wrong"
     * 3. Успешный результат необходим прокинуь дальше через LiveData/StateFlow и отрендерить полученные данные
     */
    private fun populateCats() {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    isLoading = true,
                    isError = false,
                )
            }

            val catFactDeferred = async { catsRepository.getCatFact() }
            val catImageDeferred = async { catsRepository.getCatImage() }
            val catFactResult = catFactDeferred.await()
            val catImageResult = catImageDeferred.await()

            when {
                catFactResult is Result.Success && catImageResult is Result.Success -> {
                    _state.update { state ->
                        state.copy(
                            catFact = catFactResult.data,
                            catImage = catImageResult.data,
                            isLoading = false,
                            error = null,
                        )
                    }
                }

                catFactResult is Result.Error -> {
                    _state.update { state ->
                        state.copy(
                            catFact = "",
                            catImage = "",
                            isLoading = false,
                            error = catFactResult.error,
                        )
                    }
                }

                catImageResult is Result.Error -> {
                    _state.update { state ->
                        state.copy(
                            catFact = "",
                            catImage = "",
                            isLoading = false,
                            isError = true,
                            error = catImageResult.error,
                        )
                    }
                }
            }
        }
    }

    private fun dismissError() {
        _state.update { state ->
            state.copy(
                error = null,
            )
        }
    }
}
