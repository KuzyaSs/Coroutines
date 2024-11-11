package ru.otus.coroutines.second.presentation.screens.cats.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.otus.coroutines.second.presentation.theme.CoroutinesTheme

@Composable
internal fun LoadingCatsContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun LoadingCatsContentPreview() {
    CoroutinesTheme {
        Surface {
            LoadingCatsContent()
        }
    }
}
