package com.example.pipocando_oficial.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pipocando_oficial.data.local.MovieEntity
import com.example.pipocando_oficial.viewmodel.MovieViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileScreen() {
    val vm: MovieViewModel = viewModel()
    val movies = remember { mutableStateListOf<MovieEntity>() }

    LaunchedEffect(Unit) {
        vm.savedMovies().collectLatest {
            movies.clear()
            movies.addAll(it)
        }
    }

    Scaffold { padding ->
        LazyColumn(contentPadding = padding) {
            items(movies) { m ->
                ListItem(
                    headlineContent = { Text("${m.title} (${m.year ?: "-"})") },
                    supportingContent = { Text("Status: ${m.status} | Nota: ${m.rating ?: "-"}") }
                )
                Divider()
            }
        }
    }
}
