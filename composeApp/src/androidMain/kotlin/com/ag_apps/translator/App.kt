package com.ag_apps.translator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ag_apps.translator.presentation.translate.TranslateAction
import com.ag_apps.translator.presentation.translate.TranslateViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@Composable
@Preview
fun App(
    viewModel: TranslateViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsState()

    MaterialTheme {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                viewModel.onAction(TranslateAction.ChangeTranslationText("YERSSSS"))
            }) {
                Text("Click me!")
            }

            Text(state.fromText)
        }
    }
}