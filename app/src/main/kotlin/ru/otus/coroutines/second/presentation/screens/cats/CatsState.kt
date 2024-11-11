package ru.otus.coroutines.second.presentation.screens.cats

import androidx.compose.runtime.Stable
import ru.otus.coroutines.second.domain.models.RootError

@Stable
internal data class CatsState(
    val catFact: String = "",
    val catImage: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error: RootError? = null,
)
