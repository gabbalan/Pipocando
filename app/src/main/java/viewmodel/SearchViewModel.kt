package com.example.pipocando_oficial.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pipocando_oficial.data.network.OmdbDetail
import com.example.pipocando_oficial.data.network.OmdbShort
import com.example.pipocando_oficial.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = MovieRepository(app)

    private val _results = MutableStateFlow<List<OmdbShort>>(emptyList())
    val results: StateFlow<List<OmdbShort>> = _results

    private val apiKey = com.example.pipocando_oficial.BuildConfig.OMDB_API_KEY

    fun search(query: String) {
        viewModelScope.launch {
            try {
                val r = repo.api.search(apiKey, query)
                _results.value = r.items ?: emptyList()
            } catch (_: Exception) {
                _results.value = emptyList()
            }
        }
    }

    suspend fun detail(id: String): OmdbDetail? = try {
        repo.api.detail(apiKey, id)
    } catch (_: Exception) { null }

    suspend fun save(
        id: String, title: String, year: String?, poster: String?,
        status: String, rating: Float?, review: String?
    ) {
        repo.saveMovie(id, title, year, poster, status, rating, review)
    }
}
