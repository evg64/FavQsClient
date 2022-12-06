package com.favqsclient.kmm.data.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    val login: String,
    val email: String,
    val password: String
) : ApiRequest