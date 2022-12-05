package com.favqsclient.kmm.data.entitty
import kotlinx.serialization.Serializable

@Serializable
data class CreateSessionResponseData(
    val token: String,
    val user: String,
    val email: String
) : ApiResponseData