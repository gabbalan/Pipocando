package com.example.pipocando_oficial.core

import android.content.Context

class Prefs(context: Context) {
    private val sp = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    fun saveUserName(name: String) = sp.edit().putString("user_name", name).apply()
    fun getUserName(): String? = sp.getString("user_name", null)
    fun isLogged(): Boolean = getUserName()?.isNotEmpty() == true
    fun logout() = sp.edit().remove("user_name").apply()
}
