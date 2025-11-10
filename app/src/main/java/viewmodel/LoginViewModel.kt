package com.example.pipocando_oficial.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pipocando_oficial.core.Prefs
import com.example.pipocando_oficial.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(app: Application) : AndroidViewModel(app) {
    private val prefs = Prefs(app)
    private val repo = AuthRepository(app)

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun isLogged() = prefs.isLogged()
    fun logout() = prefs.clearSession()

    fun register(name: String, email: String, password: String, onSuccess: () -> Unit) {
        _error.value = null
        viewModelScope.launch {
            _loading.value = true
            try {
                val r = repo.register(name, email, password)
                r.onSuccess { u ->
                    prefs.saveUserSession(u.id, u.name, u.email)
                    onSuccess()
                }.onFailure { e -> _error.value = e.message }
            } catch (e: Exception) {
                _error.value = "Falha ao cadastrar"
            } finally {
                _loading.value = false
            }
        }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        _error.value = null
        viewModelScope.launch {
            _loading.value = true
            try {
                val r = repo.login(email, password)
                r.onSuccess { u ->
                    prefs.saveUserSession(u.id, u.name, u.email)
                    onSuccess()
                }.onFailure { e -> _error.value = e.message }
            } catch (e: Exception) {
                _error.value = "Falha ao entrar"
            } finally {
                _loading.value = false
            }
        }
    }
}
