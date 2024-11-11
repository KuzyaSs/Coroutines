package ru.otus.coroutines.second.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ru.otus.coroutines.second.presentation.screens.cats.CatsScreen
import ru.otus.coroutines.second.presentation.screens.cats.CatsViewModel
import ru.otus.coroutines.second.presentation.theme.CoroutinesTheme

class CoroutinesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoroutinesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val catsViewModel = koinViewModel<CatsViewModel>()
                    val catsState by catsViewModel.state.collectAsState()
                    CatsScreen(
                        state = catsState,
                        onAction = catsViewModel::onAction,
                        modifier = Modifier
                            .padding(paddingValues = innerPadding),
                    )
                }
            }
        }
    }
}
