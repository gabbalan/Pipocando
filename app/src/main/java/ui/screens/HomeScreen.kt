package com.example.pipocando_oficial.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(onGoSearch: () -> Unit, onGoProfile: () -> Unit) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onGoSearch,
                    icon = { Icon(Icons.Filled.Search, contentDescription = null) },
                    label = { Text("Buscar") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onGoProfile,
                    icon = { Icon(Icons.Filled.Person, contentDescription = null) },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Feed de Amigos. ", style = MaterialTheme.typography.titleLarge)
            Text("• Lucas avaliou 'Inception' com 5⭐")
            Text("• Vini marcou 'Oppenheimer' como Quero ver")
            Text("• João avaliou 'Matrix' com 4⭐")
            Text("• Eduardo avaliou 'Fast and Furious' com 5⭐")
            Text("• Luiz avaliou 'Maze Runner' com 4⭐")
            Text("• Gabriel avaliou 'Harry Potter' com 5⭐")
        }
    }
}
