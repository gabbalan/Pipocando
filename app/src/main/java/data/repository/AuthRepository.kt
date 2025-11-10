package com.example.pipocando_oficial.data.repository

import android.content.Context
import com.example.pipocando_oficial.core.Security
import com.example.pipocando_oficial.data.local.AppDatabase
import com.example.pipocando_oficial.data.local.UserEntity

class AuthRepository(context: Context) {
    private val db = AppDatabase.get(context)
    private val users = db.userDao()

    suspend fun register(name: String, email: String, password: String): Result<UserEntity> {
        if (name.isBlank() || email.isBlank() || password.length < 4) {
            return Result.failure(IllegalArgumentException("Dados inválidos"))
        }
        if (users.countByEmail(email.trim().lowercase()) > 0) {
            return Result.failure(IllegalStateException("E-mail já cadastrado"))
        }
        val u = UserEntity(
            name = name.trim(),
            email = email.trim().lowercase(),
            passwordHash = Security.sha256(password)
        )
        val id = users.insert(u)
        return Result.success(u.copy(id = id))
    }

    suspend fun login(email: String, password: String): Result<UserEntity> {
        val u = users.findByEmail(email.trim().lowercase())
            ?: return Result.failure(IllegalArgumentException("Usuário não encontrado"))
        val ok = u.passwordHash == Security.sha256(password)
        return if (ok) Result.success(u) else Result.failure(IllegalArgumentException("Senha inválida"))
    }
}
