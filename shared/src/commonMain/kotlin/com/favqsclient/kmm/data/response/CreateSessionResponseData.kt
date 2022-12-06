package com.favqsclient.kmm.data.response
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateSessionResponseData(
    @SerialName("User-Token")
    val token: String,
    val login: String,
    val email: String
) : ApiResponseData