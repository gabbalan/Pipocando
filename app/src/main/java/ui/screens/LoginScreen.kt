package com.example.pipocando_oficial.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pipocando_oficial.viewmodel.LoginViewModel

@Composable
fun LoginScreen(onLogged: () -> Unit) {
    val vm: LoginViewModel = viewModel()
    var name by remember { mutableStateOf("") }

    LaunchedEffect(Unit) { if (vm.isLogged()) onLogged() }

    Column(Modifier.fillMaxSize().padding(24.dp)) {
        Text("Pipocando", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Seu nome") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = { if (name.isNotBlank()) { vm.saveUser(name); onLogged() } },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Entrar") }
    }
}
