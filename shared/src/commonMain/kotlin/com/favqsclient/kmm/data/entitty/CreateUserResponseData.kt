package com.favqsclient.kmm.data.entitty
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserResponseData(
    val token: String,
    val login: String
) : ApiResponseData