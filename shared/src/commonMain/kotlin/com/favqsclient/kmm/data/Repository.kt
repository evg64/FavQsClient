package com.favqsclient.kmm.data

interface Repository {
    fun createUser(login: String, email: String, password: String)

    suspend fun getTestPage(): String
}