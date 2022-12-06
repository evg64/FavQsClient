package com.favqsclient.kmm.data.request

import kotlinx.serialization.Serializable

@Serializable
data class ForgotPasswordRequest(
    val user: UserData
) : ApiRequest


@Serializable
data class UserData(
    val email: String
)