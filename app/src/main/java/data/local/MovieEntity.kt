package com.example.pipocando_oficial.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: String,
    val title: String,
    val year: String?,
    val posterUrl: String?,
    val status: String,
    val rating: Float?,
    val reviewText: String?,
    val createdAt: Long,
    val synced: Boolean
)
