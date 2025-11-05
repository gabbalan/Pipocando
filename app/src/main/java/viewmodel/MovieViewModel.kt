package com.example.pipocando_oficial.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pipocando_oficial.data.local.MovieEntity
import com.example.pipocando_oficial.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = MovieRepository(app)
    fun savedMovies(): Flow<List<MovieEntity>> = repo.getAllSaved()
}
