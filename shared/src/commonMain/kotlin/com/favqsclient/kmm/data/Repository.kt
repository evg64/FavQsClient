package com.favqsclient.kmm.data

import com.favqsclient.kmm.login.domain.entity.LoginResponse

interface Repository {
    fun createUser(login: String, email: String, password: String)
    fun login(login: String, password: String): LoginResponse

    suspend fun getTestPage(): String
}