package com.favqsclient.kmm

interface Repository {
    fun createUser(login: String, email: String, password: String)
}