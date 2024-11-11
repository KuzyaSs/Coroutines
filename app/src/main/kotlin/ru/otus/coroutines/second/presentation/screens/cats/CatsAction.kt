package ru.otus.coroutines.second.presentation.screens.cats

internal sealed interface CatsAction {
    data object OnNextClicked : CatsAction
    data object OnRetryClicked : CatsAction
    data object OnErrorShown : CatsAction
}
