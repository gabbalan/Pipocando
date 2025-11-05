package com.example.pipocando_oficial.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pipocando_oficial.core.Prefs

class LoginViewModel(app: Application) : AndroidViewModel(app) {
    private val prefs = Prefs(app)
    fun isLogged() = prefs.isLogged()
    fun saveUser(name: String) = prefs.saveUserName(name)
    fun getUserName(): String? = prefs.getUserName()
    fun logout() = prefs.logout()
}
