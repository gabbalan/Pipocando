package com.example.pipocando_oficial.data.repository

import android.content.Context
import com.example.pipocando_oficial.data.local.AppDatabase
import com.example.pipocando_oficial.data.local.MovieEntity
import com.example.pipocando_oficial.data.network.OmdbService
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository(context: Context) {

    private val db = AppDatabase.get(context)
    private val dao = db.movieDao()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.omdbapi.com/") // barra final é obrigatória
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: OmdbService = retrofit.create(OmdbService::class.java)

    fun getAllSaved(): Flow<List<MovieEntity>> = dao.getAll()
    suspend fun getSavedById(id: String) = dao.getById(id)

    suspend fun saveMovie(
        id: String,
        title: String,
        year: String?,
        posterUrl: String?,
        status: String,
        rating: Float?,
        reviewText: String?
    ) {
        val entity = MovieEntity(
            id = id,
            title = title,
            year = year,
            posterUrl = posterUrl,
            status = status,
            rating = rating,
            reviewText = reviewText,
            createdAt = System.currentTimeMillis(),
            synced = false
        )
        dao.upsert(entity)
    }

    suspend fun deleteMovie(entity: MovieEntity) = dao.delete(entity)
}
