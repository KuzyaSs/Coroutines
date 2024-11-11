package ru.otus.coroutines.second.presentation.screens.cats.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.otus.coroutines.R
import ru.otus.coroutines.second.presentation.theme.CoroutinesTheme

@Composable
internal fun CatsContent(
    catFact: String,
    catImage: String,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = catImage,
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(R.dimen.image_size))
                .padding(bottom = 8.dp),
            contentScale = ContentScale.Crop,
        )

        Text(
            text = catFact,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 16.dp),
        )

        Button(
            onClick = onNextClick,
        ) {
            Text(text = stringResource(R.string.next))
        }
    }
}

@Preview
@Composable
private fun CatsContentPreview() {
    CoroutinesTheme {
        Surface {
            CatsContent(
                catFact = "Some fact",
                catImage = "https://cdn2.thecatapi.com/images/8hi.jpg",
                onNextClick = {},
            )
        }
    }
}
