package com.example.pipocando_oficial.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun PipocandoApp(content: @Composable () -> Unit) {
    MaterialTheme { Surface { content() } }
}
