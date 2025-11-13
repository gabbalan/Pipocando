package com.example.pipocando_oficial.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pipocando_oficial.BuildConfig
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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val apiKey = BuildConfig.OMDB_API_KEY

    init {
        // busca inicial para popular a tela
        search("batman")
    }

    fun search(query: String) {
        _errorMessage.value = null
        if (apiKey.isBlank() || apiKey == "SUA_OMDB_KEY_AQUI") {
            _results.value = emptyList()
            _errorMessage.value = "Configure sua OMDb API key no build.gradle.kts (BuildConfig.OMDB_API_KEY)."
            return
        }
        if (query.isBlank()) {
            _results.value = emptyList()
            _errorMessage.value = "Digite um termo de busca."
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val r = repo.api.search(apiKey, query.trim())
                val ok = (r.response?.equals("True", ignoreCase = true) == true)
                if (ok && !r.items.isNullOrEmpty()) {
                    _results.value = r.items!!
                    _errorMessage.value = null
                } else {
                    _results.value = emptyList()
                    _errorMessage.value = r.error ?: "Sem resultados para \"$query\"."
                }
            } catch (_: Exception) {
                _results.value = emptyList()
                _errorMessage.value = "Falha ao buscar (verifique a Internet e a API key)."
            } finally {
                _isLoading.value = false
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
