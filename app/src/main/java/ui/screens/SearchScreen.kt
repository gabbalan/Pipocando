package com.example.pipocando_oficial.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pipocando_oficial.data.network.OmdbShort
import com.example.pipocando_oficial.viewmodel.SearchViewModel

@Composable
fun SearchScreen(onOpenDetail: (String) -> Unit) {
    val vm: SearchViewModel = viewModel()

    val results: List<OmdbShort> by vm.results.collectAsState(initial = emptyList())
    val isLoading: Boolean by vm.isLoading.collectAsState(initial = false)
    val errorMessage: String? by vm.errorMessage.collectAsState(initial = null)

    var query by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                if (it.text.length > 2) {
                    vm.search(it.text)
                }
            },
            label = { Text("Buscar filme...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        when {
            isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            !errorMessage.isNullOrEmpty() -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = errorMessage ?: "", color = MaterialTheme.colorScheme.error)
                }
            }

            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(results) { movie ->
                        MovieListItem(
                            title = movie.title,
                            year = movie.year,
                            poster = movie.poster,
                            onClick = { onOpenDetail(movie.imdbID) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MovieListItem(title: String, year: String?, poster: String?, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = poster,
                contentDescription = title,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 12.dp)
            )
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(year ?: "-", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
