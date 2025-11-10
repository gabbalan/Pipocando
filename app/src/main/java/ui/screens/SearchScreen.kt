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

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val apiKey = BuildConfig.OMDB_API_KEY

    init {
        // dispara uma busca inicial para já mostrar algo na tela
        search("batman")
    }

    fun search(query: String) {
        _error.value = null
        if (apiKey.isBlank() || apiKey == "SUA_OMDB_KEY_AQUI") {
            _results.value = emptyList()
            _error.value = "Configure sua OMDb API key no build.gradle.kts (BuildConfig.OMDB_API_KEY)."
            return
        }
        if (query.isBlank()) {
            _results.value = emptyList()
            _error.value = "Digite um termo de busca."
            return
        }

        viewModelScope.launch {
            _loading.value = true
            try {
                val r = repo.api.search(apiKey, query.trim())
                val ok = (r.response?.equals("True", ignoreCase = true) == true)
                if (ok && !r.items.isNullOrEmpty()) {
                    _results.value = r.items!!
                    _error.value = null
                } else {
                    _results.value = emptyList()
                    _error.value = r.error ?: "Sem resultados para \"$query\"."
                }
            } catch (e: Exception) {
                _results.value = emptyList()
                _error.value = "Falha ao buscar (verifique a Internet e a API key)."
            } finally {
                _loading.value = false
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
