package com.favqsclient.kmm.data.entitty

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateSessionRequest(
    @SerialName("user")
    val user: CreateSessionUserData
) : ApiRequest {

    @Serializable
    data class CreateSessionUserData(
        @SerialName("login")
        val login: String,
        @SerialName("password")
        val password: String
    )
}