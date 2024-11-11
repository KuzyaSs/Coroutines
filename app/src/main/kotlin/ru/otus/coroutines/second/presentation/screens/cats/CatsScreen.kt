package ru.otus.coroutines.second.presentation.screens.cats

import android.widget.Toast
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.otus.coroutines.R
import ru.otus.coroutines.second.presentation.screens.cats.components.CatsContent
import ru.otus.coroutines.second.presentation.screens.cats.components.ErrorCatsContent
import ru.otus.coroutines.second.presentation.screens.cats.components.LoadingCatsContent
import ru.otus.coroutines.second.presentation.theme.CoroutinesTheme

@Composable
internal fun CatsScreen(
    state: CatsState,
    onAction: (action: CatsAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    when {
        state.isError -> ErrorCatsContent(
            onRetryClick = { onAction(CatsAction.OnRetryClicked) },
            modifier = modifier,
        )

        state.isLoading -> LoadingCatsContent(
            modifier = modifier,
        )

        else -> CatsContent(
            catFact = state.catFact,
            catImage = state.catImage,
            modifier = modifier,
            onNextClick = { onAction(CatsAction.OnNextClicked) },
        )
    }

    val context = LocalContext.current
    val errorMessage = stringResource(R.string.error_message)
    LaunchedEffect(state.error) {
        if (state.error != null) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            onAction(CatsAction.OnErrorShown)
        }
    }
}

@Preview
@Composable
private fun CatsScreenPreview() {
    CoroutinesTheme {
        Surface {
            CatsScreen(
                state = CatsState(
                    catFact = "Some fact",
                    catImage = "https://cdn2.thecatapi.com/images/8hi.jpg",
                ),
                onAction = {},
            )
        }
    }
}
