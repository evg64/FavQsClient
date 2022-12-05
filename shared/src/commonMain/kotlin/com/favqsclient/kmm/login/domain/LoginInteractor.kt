package com.favqsclient.kmm.login.domain

import com.favqsclient.kmm.data.Repository
import com.favqsclient.kmm.login.domain.entity.LoginResponse

/**
 *
 *
 * @author Evgeny Chumak
 **/
class LoginInteractor(
    private val repository: Repository,
) {

    suspend fun login(user: String, password: String): LoginResponse {
        val output = repository.login(user, password)
        return output
    }
}