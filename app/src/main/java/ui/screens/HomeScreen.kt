import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(onGoSearch: () -> Unit, onGoProfile: () -> Unit) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = true, onClick = { }, icon = { Icon(Icons.Default.Home, null) }, label = { Text("Home") })
                NavigationBarItem(selected = false, onClick = onGoSearch, icon = { Icon(Icons.Default.Search, null) }, label = { Text("Buscar") })
                NavigationBarItem(selected = false, onClick = onGoProfile, icon = { Icon(Icons.Default.Person, null) }, label = { Text("Perfil") })
            }
        }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(16.  dp)) {
            Text("Feed de Amigos (mock)", style = MaterialTheme.typography.titleLarge)
            Text("• Lucas avaliou 'Inception' com 5⭐")
            Text("• Ana marcou 'Oppenheimer' como Quero ver")
            Text("• João avaliou 'Matrix' com 4⭐")
        }
    }
}

// imports de ícones (deixe no final do arquivo)
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
