package ru.otus.coroutines.first

sealed interface FirstResult {
    data object Success : FirstResult
    data class Error(val exception: Exception) : FirstResult
}
