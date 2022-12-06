package com.favqsclient.kmm.data.entitty

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    val login: String,
    val email: String,
    val password: String
) : ApiRequest