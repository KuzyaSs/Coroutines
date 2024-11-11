package ru.otus.coroutines.second.presentation.screens.cats.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.otus.coroutines.R
import ru.otus.coroutines.second.presentation.theme.CoroutinesTheme

@Composable
internal fun ErrorCatsContent(
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = onRetryClick,
        ) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Preview
@Composable
private fun LoadingCatsContentPreview() {
    CoroutinesTheme {
        Surface {
            ErrorCatsContent(
                onRetryClick = {},
            )
        }
    }
}
