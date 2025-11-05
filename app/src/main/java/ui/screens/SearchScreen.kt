package com.example.pipocando_oficial.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pipocando_oficial.ui.components.MovieItem
import com.example.pipocando_oficial.viewmodel.SearchViewModel

@Composable
fun SearchScreen(onOpenDetail: (String) -> Unit) {
    val vm: SearchViewModel = viewModel()
    val results by vm.results.collectAsState()

    var query by remember { mutableStateOf("batman") }

    Column(Modifier.fillMaxSize().padding(8.dp)) {
        OutlinedTextField(
            value = query, onValueChange = { query = it },
            label = { Text("Buscar filmes") }, modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Button(onClick = { vm.search(query) }) { Text("Buscar") }
        Spacer(Modifier.height(8.dp))

        results.forEach { item ->
            MovieItem(item) { onOpenDetail(item.imdbID) }
        }
    }
}
