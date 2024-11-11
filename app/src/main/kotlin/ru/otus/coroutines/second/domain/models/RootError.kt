package ru.otus.coroutines.second.domain.models

interface RootError

enum class CoreError : RootError {
    TIMEOUT_ERROR,
    CONNECTION_ERROR,
}
