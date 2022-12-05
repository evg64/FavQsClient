package com.favqsclient.kmm.login.domain.entity

/**
 *
 *
 * @author Evgeny Chumak
 **/
data class LoginResponse(
    private val userToken: String,
    private val user: String,
    private val login: String,
)