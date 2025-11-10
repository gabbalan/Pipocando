package com.example.pipocando_oficial.core

import android.content.Context

class Prefs(context: Context) {
    private val sp = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    fun saveUserSession(id: Long, name: String, email: String) {
        sp.edit()
            .putLong("user_id", id)
            .putString("user_name", name)
            .putString("user_email", email)
            .apply()
    }

    fun clearSession() = sp.edit()
        .remove("user_id")
        .remove("user_name")
        .remove("user_email")
        .apply()

    fun getUserId(): Long = sp.getLong("user_id", -1L)
    fun getUserName(): String? = sp.getString("user_name", null)
    fun getUserEmail(): String? = sp.getString("user_email", null)
    fun isLogged(): Boolean = getUserId() > 0 && !getUserName().isNullOrBlank()
}
