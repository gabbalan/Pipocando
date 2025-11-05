package com.example.pipocando_oficial.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: String,     // imdbID da OMDb
    val title: String,
    val year: String?,
    val posterUrl: String?,
    val status: String,             // "assistido" ou "quero_ver"
    val rating: Float?,             // 0..5 (null se não avaliou)
    val reviewText: String?,        // comentário opcional
    val createdAt: Long,            // System.currentTimeMillis()
    val synced: Boolean             // reservado para futura sync
)
