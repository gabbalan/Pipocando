package com.example.pipocando_oficial.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pipocando_oficial.data.network.OmdbShort

@Composable
fun MovieItem(item: OmdbShort, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(Modifier.padding(8.dp)) {
            AsyncImage(
                model = item.poster,
                contentDescription = item.title,
                modifier = Modifier.size(80.dp)
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.fillMaxWidth()) {
                Text(text = item.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = item.year ?: "-", maxLines = 1)
            }
        }
    }
}
