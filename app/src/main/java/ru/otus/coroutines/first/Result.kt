package ru.otus.coroutines.first

sealed interface Result {
    data object Success : Result
    data class Error(val exception: Exception) : Result
}
