package com.example.pipocando_oficial.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pipocando_oficial.viewmodel.SearchViewModel
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(imdbId: String) {
    val vm: SearchViewModel = viewModel()
    var title by remember { mutableStateOf("") }
    var year by remember { mutableStateOf<String?>(null) }
    var poster by remember { mutableStateOf<String?>(null) }
    var plot by remember { mutableStateOf<String?>(null) }
    var rating by remember { mutableStateOf(0f) }
    var reviewText by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("assistido") }
    val scope = rememberCoroutineScope()

    LaunchedEffect(imdbId) {
        vm.detail(imdbId)?.let {
            title = it.title
            year = it.year
            poster = it.poster
            plot = it.plot
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        AsyncImage(model = poster, contentDescription = title, modifier = Modifier.height(200.dp).fillMaxWidth())
        Spacer(Modifier.height(12.dp))
        Text("$title (${year ?: "-"})", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text(plot ?: "-")

        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value = reviewText, onValueChange = { reviewText = it }, label = { Text("Comentário (opcional)") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        Row {
            Text("Nota: ${rating}")
            Spacer(Modifier.width(12.dp))
            Slider(value = rating, onValueChange = { rating = it }, valueRange = 0f..5f, steps = 4, modifier = Modifier.weight(1f))
        }
        Spacer(Modifier.height(8.dp))
        Row {
            FilterChip(selected = status == "assistido", onClick = { status = "assistido" }, label = { Text("Assistido") })
            Spacer(Modifier.width(8.dp))
            FilterChip(selected = status == "quero_ver", onClick = { status = "quero_ver" }, label = { Text("Quero ver") })
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            scope.launch {
                vm.save(imdbId, title, year, poster, status, rating, reviewText)
            }
        }) { Text("Salvar") }
    }
}

// chip do material3
import androidx.compose.material3.FilterChip
